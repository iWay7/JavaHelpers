package site.iway.javahelpers;

import java.io.File;
import java.text.DecimalFormat;

public class CacheCleaner {

    public interface CacheCleanerListener {
        public void onCacheCleanerStateChanged(CacheCleanerState newState);
    }

    public enum CacheCleanerState {
        Initialized,
        BeginComputeSize,
        ComputingSize,
        EndComputeSize,
        BeginClean,
        Cleaning,
        Finished
    }

    private String mCachePath;
    private CacheCleanerListener mListener;
    private volatile float mCacheSize;
    private volatile boolean mIsCanceled;

    public CacheCleaner(String cachePath, CacheCleanerListener listener) {
        mCachePath = cachePath;
        mListener = listener;
    }

    private Thread mSizeComputer = new Thread() {

        private void addFileSizes(String path) {
            File root = new File(path);
            File[] files = root.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                if (mIsCanceled) {
                    return;
                }
                if (file.isFile()) {
                    mCacheSize += file.length();
                } else if (file.isDirectory()) {
                    addFileSizes(file.getAbsolutePath());
                }
            }
        }

        public void run() {
            mCacheSize = 0;
            mListener.onCacheCleanerStateChanged(CacheCleanerState.Initialized);
            mListener.onCacheCleanerStateChanged(CacheCleanerState.BeginComputeSize);
            mListener.onCacheCleanerStateChanged(CacheCleanerState.ComputingSize);
            addFileSizes(mCachePath);
            mListener.onCacheCleanerStateChanged(CacheCleanerState.EndComputeSize);
            mSizeComputer = null;
        }

    };

    public void beginComputeSize() {
        mSizeComputer.start();
    }

    public float getCacheSize() {
        return mCacheSize;
    }

    public String getCacheSizeFormatedK() {
        return new DecimalFormat("0.0").format(mCacheSize / 1024);
    }

    public String getCacheSizeFormatedM() {
        return new DecimalFormat("0.0").format(mCacheSize / 1024 / 1024);
    }

    private Thread mCleaner = new Thread() {

        private void cleanDir(String path) {
            File root = new File(path);
            File[] files = root.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                if (mIsCanceled) {
                    return;
                } else if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    cleanDir(file.getAbsolutePath());
                }
            }
        }

        public void run() {
            mListener.onCacheCleanerStateChanged(CacheCleanerState.BeginClean);
            mListener.onCacheCleanerStateChanged(CacheCleanerState.Cleaning);
            cleanDir(mCachePath);
            mListener.onCacheCleanerStateChanged(CacheCleanerState.Finished);
            mCleaner = null;
        }

    };

    public void beginClean() {
        mCleaner.start();
    }

    public void cancel() {
        mIsCanceled = true;
    }

}
