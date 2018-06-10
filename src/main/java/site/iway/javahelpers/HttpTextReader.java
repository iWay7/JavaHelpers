package site.iway.javahelpers;

import java.nio.charset.Charset;

public abstract class HttpTextReader extends HttpDataGetter {

    private Charset mCharset;

    public HttpTextReader(String url, Charset charset) {
        super(url);
        mCharset = charset;
    }

    public HttpTextReader(String url, String charsetName) {
        this(url, Charset.forName(charsetName));
    }

    public HttpTextReader(String url) {
        this(url, Charset.defaultCharset());
    }

    @Override
    public void onGetData(AutoExpandByteArray array) throws Exception {
        byte[] data = array.getRawArray();
        int length = array.size();
        String string = new String(data, 0, length, mCharset);
        onGetText(string);
    }

    public abstract void onGetText(String text) throws Exception;

}
