package site.iway.javahelpers;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnector extends Thread {

    private String mUrlString;
    private String mUrlStringRedirect;
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

    protected void onPrepare() throws Exception {
    }

    protected HttpURLConnection createConnection(boolean isRetry) throws Exception {
        URL url = new URL(isRetry ? mUrlStringRedirect : mUrlString);
        return (HttpURLConnection) url.openConnection();
    }

    protected void onStartConnect(HttpURLConnection connection) throws Exception {
        connection.setConnectTimeout(20 * 1000);
        connection.setReadTimeout(20 * 1000);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestProperty("Accept-Encoding", "identity");
    }

    protected void onConnected(HttpURLConnection connection) throws Exception {
    }

    protected boolean willRetryConnect(HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        boolean willRedirect = responseCode == 301 || responseCode == 302;
        if (willRedirect) {
            mUrlStringRedirect = connection.getHeaderField("Location");
        }
        return willRedirect;
    }

    protected void onDataDownloaded(byte[] buffer, int startIndex, int count) throws Exception {
    }

    protected void onCanceled() throws Exception {
    }

    protected void onFinish() throws Exception {
    }

    protected void onError(Exception e) {
    }

    protected void onFinally() {
    }

    public void cancel() {
        mIsCanceled = true;
    }

    @Override
    public void run() {
        HttpURLConnection connection = null;
        try {
            onPrepare();
            connection = createConnection(false);
            onStartConnect(connection);
            connection.connect();
            onConnected(connection);
            while (willRetryConnect(connection)) {
                connection.disconnect();
                connection = createConnection(true);
                onStartConnect(connection);
                connection.connect();
                onConnected(connection);
            }
            if (connection.getDoInput()) {
                mContentLength = connection.getContentLength();
                mDownloadedLength = 0;
                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[mBufferSize];
                long totalReadCount = 0;
                while (true) {
                    int readCount = inputStream.read(buffer);
                    if (mIsCanceled) {
                        onCanceled();
                        break;
                    } else if (readCount > 0) {
                        onDataDownloaded(buffer, 0, readCount);
                        totalReadCount += readCount;
                        mDownloadedLength = totalReadCount;
                    } else if (readCount == 0) {
                        sleep(100);
                    } else {
                        break;
                    }
                }
            }
            onFinish();
        } catch (Exception e) {
            onError(e);
        } finally {
            onFinally();
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
