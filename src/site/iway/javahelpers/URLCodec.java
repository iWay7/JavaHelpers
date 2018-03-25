package site.iway.javahelpers;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLCodec {

    public static String encode(String s) {
        if (s == null)
            return null;
        return URLEncoder.encode(s);
    }

    public static String encode(String s, String charset) {
        try {
            if (s == null)
                return null;
            return URLEncoder.encode(charset, charset);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decode(String s) {
        if (s == null)
            return null;
        return URLDecoder.decode(s);
    }

    public static String decode(String s, String charset) {
        try {
            if (s == null)
                return null;
            return URLDecoder.decode(charset, charset);
        } catch (Exception e) {
            return null;
        }
    }

}
