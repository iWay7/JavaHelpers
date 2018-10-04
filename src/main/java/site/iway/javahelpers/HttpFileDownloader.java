package site.iway.javahelpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HttpFileDownloader extends HttpConnector {

    private String mFilePath;
    private File mFile;
    private String mTempFilePath;
    private File mTempFile;
    private FileOutputStream mFileOutputStream;
    private boolean mOverwriteIfExisted;

    public HttpFileDownloader(String url, String file) {
        super(url);
        mFilePath = file;
        mFile = new File(mFilePath);
        mTempFilePath = mFilePath + "-" + StringHelper.random(8);
        mTempFile = new File(mTempFilePath);
    }

    public boolean getOverwriteIfExisted() {
        return mOverwriteIfExisted;
    }

    public void setOverwriteIfExisted(boolean overwriteIfExisted) {
        mOverwriteIfExisted = overwriteIfExisted;
    }

    @Override
    public void onPrepare() throws Exception {
        if (!FileSystemHelper.createDirectory(mTempFile.getParentFile())) {
            throw new RuntimeException("Create store directory failed.");
        }
        mFileOutputStream = new FileOutputStream(mTempFilePath);
    }

    @Override
    public void onDataDownloaded(byte[] data, int startIndex, int count) throws Exception {
        mFileOutputStream.write(data, startIndex, count);
    }

    @Override
    public void onCanceled() throws Exception {
        throw new RuntimeException("Operation has been canceled.");
    }

    private void closeCurrentFileStream() {
        if (mFileOutputStream != null) {
            try {
                mFileOutputStream.close();
                mFileOutputStream = null;
            } catch (Exception ex) {
                // nothing
            }
        }
    }

    private void deleteTempFile() {
        if (mTempFile != null) {
            if (!mTempFile.delete()) {
                mTempFile.deleteOnExit();
            }
        }
    }

    private void renameTempFile() throws Exception {
        if (mFile.exists()) {
            if (mOverwriteIfExisted) {
                boolean result = mFile.delete();
                if (!result) {
                    throw new IOException("Delete " + mFilePath + " failed.");
                }
            } else {
                throw new IOException(mFilePath + " already existed, try setOverwriteIfExisted(true) ?");
            }
        }
        boolean result = mTempFile.renameTo(mFile);
        if (!result) {
            throw new IOException("Rename file " + mTempFile + " to " + mFilePath + " failed.");
        }
    }

    @Override
    public void onFinish() throws Exception {
        closeCurrentFileStream();
        renameTempFile();
    }

    @Override
    public void onError(Exception e) {
        closeCurrentFileStream();
        deleteTempFile();
    }

}
