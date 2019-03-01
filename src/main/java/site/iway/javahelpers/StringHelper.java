package site.iway.javahelpers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StringHelper {

    private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static void setDefaultCharset(Charset defaultCharset) {
        if (defaultCharset == null)
            throw new NullPointerException("Param defaultCharset can not be null.");
        DEFAULT_CHARSET = defaultCharset;
    }

    public static boolean nullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean nullOrBlank(String string) {
        if (nullOrEmpty(string)) {
            return true;
        }
        int length = string.length();
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (!CharHelper.isASCIIInvisible(c)) {
                return false;
            }
        }
        return true;
    }

    public static String extract(String string, String prefix, String suffix) {
        int prefixIndex = string.indexOf(prefix);
        if (prefixIndex < 0)
            return null;
        int startIndex = prefixIndex + prefix.length();
        int suffixIndex = string.indexOf(suffix, startIndex);
        if (suffixIndex < 0)
            return null;
        int endIndex = suffixIndex;
        return string.substring(startIndex, endIndex);
    }

    public static String reverse(String string) {
        char[] array = string.toCharArray();
        int i = 0;
        int j = string.length() - 1;
        while (i < j) {
            char c = array[i];
            array[i] = array[j];
            array[j] = c;
            i++;
            j--;
        }
        return new String(array);
    }

    public static String trim(String string, boolean trimStart, boolean trimEnd, char... chars) {
        if (string == null)
            return null;
        int beginIndex = 0;
        while (trimStart && beginIndex < string.length()) {
            char c = string.charAt(beginIndex);
            if (ArrayHelper.indexOf(chars, c) < 0)
                break;
            else
                beginIndex++;
        }
        int endIndex = string.length();
        while (trimEnd && endIndex > beginIndex) {
            char c = string.charAt(endIndex - 1);
            if (ArrayHelper.indexOf(chars, c) < 0)
                break;
            else
                endIndex--;
        }
        return string.substring(beginIndex, endIndex);
    }

    public static String merge(List<String> strings, String splitter) {
        StringBuilder builder = new StringBuilder();
        if (nullOrEmpty(splitter)) {
            for (String string : strings) {
                builder.append(string);
                builder.append(splitter);
            }
            return builder.toString();
        } else {
            for (String string : strings) {
                builder.append(splitter);
                builder.append(string);
            }
            return builder.substring(splitter.length());
        }
    }

    public static String merge(List<String> strings) {
        return merge(strings, "");
    }

    public static String merge(String... strings) {
        return merge(Arrays.asList(strings));
    }

    public static String padLeft(String content, int length, char c) {
        StringBuilder builder = new StringBuilder(content);
        while (builder.length() < length) {
            builder.insert(0, c);
        }
        return builder.toString();
    }

    public static String padLeft(String content, int length) {
        return padLeft(content, length, ' ');
    }

    public static String padRight(String content, int length, char c) {
        StringBuilder builder = new StringBuilder(content);
        while (builder.length() < length) {
            builder.append(c);
        }
        return builder.toString();
    }

    public static String padRight(String content, int length) {
        return padRight(content, length, ' ');
    }

    private static final char[] sRandomChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static String random(int length) {
        Random random = new Random(System.nanoTime());
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = sRandomChars[random.nextInt(sRandomChars.length)];
        }
        return new String(array);
    }

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private static final char[] sHexCharsLowerCase = "0123456789abcdef".toCharArray();
    private static final char[] sHexCharsUpperCase = "0123456789ABCDEF".toCharArray();

    public static String hex(byte[] data, boolean lowerCase) {
        return SecurityHelper.hexEncode(data, lowerCase);
    }

    public static String hex(byte[] data) {
        return hex(data, true);
    }

    public static byte[] binary(String hex) {
        return SecurityHelper.hexDecode(hex);
    }

    public static String md5(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        byte[] data = content.getBytes(charset);
        byte[] md5 = SecurityHelper.md5(data);
        return hex(md5);
    }

    public static String md5(String content) {
        return md5(content, DEFAULT_CHARSET);
    }

    public static String sha1(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        byte[] data = content.getBytes(charset);
        byte[] sha1 = SecurityHelper.sha1(data);
        return hex(sha1);
    }

    public static String sha1(String content) {
        return sha1(content, DEFAULT_CHARSET);
    }

    public static String crc32(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        byte[] data = content.getBytes(charset);
        long crc32 = SecurityHelper.crc32(data);
        return padLeft(Long.toHexString(crc32), 8, '0');
    }

    public static String crc32(String content) {
        return crc32(content, DEFAULT_CHARSET);
    }

    public static String urlEncode(String content, Charset charset) {
        return SecurityHelper.urlEncode(content, charset);
    }

    public static String urlEncode(String content) {
        return urlEncode(content, DEFAULT_CHARSET);
    }

    public static String urlDecode(String content, Charset charset) {
        return SecurityHelper.urlEncode(content, charset);
    }

    public static String urlDecode(String content) {
        return urlDecode(content, DEFAULT_CHARSET);
    }

}
