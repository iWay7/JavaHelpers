package site.iway.javahelpers;

public class HttpDataGetter extends HttpConnector {

    private AutoExpandByteArray mByteArray;

    public HttpDataGetter(String url) {
        super(url);
    }

    @Override
    public void onPrepare() throws Exception {
        mByteArray = new AutoExpandByteArray();
    }

    @Override
    public void onDataDownloaded(byte[] buffer, int startIndex, int count) throws Exception {
        mByteArray.add(buffer, startIndex, count);
    }

    @Override
    public void onFinish() throws Exception {
        onGetData(mByteArray);
    }

    public void onGetData(AutoExpandByteArray array) throws Exception {
    }

}
