package site.iway.javahelpers;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnector extends Thread {

    private String mUrlString;
    private int mBufferSize;
    private volatile long mContentLength = 0;
    private volatile long mDownloadedLength = 0;
    private volatile boolean mIsCanceled = false;

    public HttpConnector(String url, int bufferSize) {
        if (url == null) {
            throw new NullPointerException("Parameter url can not be null.");
        }
        if (bufferSize <= 0) {
            throw new RuntimeException("Parameter bufferSize is not valid.");
        }
        mUrlString = url;
        mBufferSize = bufferSize;
    }

    public HttpConnector(String url) {
        this(url, 8 * 1024);
    }

    public long getContentLength() {
        return mContentLength;
    }

    public long getDownloadedLength() {
        return mDownloadedLength;
    }

    public void onPrepare() throws Exception {
    }

    public void onStartConnect(HttpURLConnection connection) throws Exception {
    }

    public void onConnected(HttpURLConnection connection) throws Exception {
    }

    public void onDataDownloaded(byte[] buffer, int startIndex, int count) throws Exception {
    }

    public void onCanceled() throws Exception {
    }

    public void onFinish() throws Exception {
    }

    public void onError(Exception e) {
    }

    public void onFinally() {
    }

    public void cancel() {
        mIsCanceled = true;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            onPrepare();
            URL url = new URL(mUrlString);
            connection = (HttpURLConnection) url.openConnection();
            onStartConnect(connection);
            connection.connect();
            onConnected(connection);
            mContentLength = connection.getContentLength();
            mDownloadedLength = 0;
            inputStream = connection.getInputStream();
            byte[] buffer = new byte[mBufferSize];
            while (true) {
                int readCount = inputStream.read(buffer);
                if (mIsCanceled) {
                    onCanceled();
                    break;
                } else if (readCount > 0) {
                    onDataDownloaded(buffer, 0, readCount);
                    mDownloadedLength += readCount;
                } else if (readCount == 0) {
                    sleep(10);
                } else {
                    break;
                }
            }
            onFinish();
        } catch (Exception e) {
            onError(e);
        } finally {
            onFinally();
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                // nothing
            }

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
