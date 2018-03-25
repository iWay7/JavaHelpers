package site.iway.javahelpers;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

public class StringHelper {

    public static boolean nullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean nullOrWhiteSpace(String string) {
        if (nullOrEmpty(string)) {
            return true;
        }
        int length = string.length();
        int start = 0;
        while ((start < length) && (string.charAt(start) == ' ')) {
            start++;
        }
        while ((start < length) && (string.charAt(length - 1) == ' ')) {
            length--;
        }
        return start == length;
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

    public static String merge(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static String padLeft(String string, int length) {
        StringBuilder builder = new StringBuilder(string);
        while (builder.length() < length) {
            builder.insert(0, ' ');
        }
        return builder.toString();
    }

    public static String padRight(String string, int length) {
        StringBuilder builder = new StringBuilder(string);
        while (builder.length() < length) {
            builder.append(' ');
        }
        return builder.toString();
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
        char[] chars = new char[data.length * 2];
        if (lowerCase) {
            for (int i = 0; i < data.length; i++) {
                chars[i * 2] = sHexCharsLowerCase[(data[i] & 0xF0) >> 4];
                chars[i * 2 + 1] = sHexCharsLowerCase[data[i] & 0x0F];
            }
        } else {
            for (int i = 0; i < data.length; i++) {
                chars[i * 2] = sHexCharsUpperCase[(data[i] & 0xF0) >> 4];
                chars[i * 2 + 1] = sHexCharsUpperCase[data[i] & 0x0F];
            }
        }
        return new String(chars);
    }

    public static String hex(byte[] data) {
        return hex(data, true);
    }

    private static int toInt(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        } else if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        } else {
            throw new RuntimeException("Invalid hex char.");
        }
    }

    private static byte toByte(String hex, int index) {
        return (byte) ((toInt(hex.charAt(index)) << 4) | toInt(hex.charAt(index + 1)));
    }

    public static byte[] binary(String hex) {
        int stringLength = hex.length();
        if (stringLength < 0 || stringLength % 2 != 0) {
            throw new RuntimeException("Invalid hex string.");
        }
        int length = hex.length() / 2;
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = toByte(hex, i * 2);
        }
        return data;
    }

    public static String md5(String string, Charset charset) {
        byte[] data = string.getBytes(charset);
        byte[] md5 = SecurityHelper.md5(data);
        return hex(md5);
    }

    public static String md5(String string) {
        byte[] data = string.getBytes();
        byte[] md5 = SecurityHelper.md5(data);
        return hex(md5);
    }

}
