package site.iway.javahelpers;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Base64.Encoder;

public abstract class HttpTextReader extends HttpDataGetter {

    private Charset mCharset;
    private String mUserInfo;

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

    public void setUserInfo(String username, String password) {
        mUserInfo = username + ":" + password;
    }

    @Override
    public void onStartConnect(HttpURLConnection connection) throws Exception {
        if (mUserInfo != null && mUserInfo.length() > 1) {
            Encoder encoder = Base64.getEncoder();
            String str = encoder.encodeToString(mUserInfo.getBytes("utf-8"));
            connection.addRequestProperty("Authorization", "Basic " + str);
        }
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
