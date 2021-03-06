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

    private static final char[] RANDOM_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final char[] RANDOM_LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] RANDOM_NUMBERS = "0123456789".toCharArray();

    public static String random(int length) {
        Random random = new Random(System.nanoTime());
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = RANDOM_CHARS[random.nextInt(RANDOM_CHARS.length)];
        }
        return new String(array);
    }

    public static String randomOnlyLetters(int length) {
        Random random = new Random(System.nanoTime());
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = RANDOM_LETTERS[random.nextInt(RANDOM_LETTERS.length)];
        }
        return new String(array);
    }

    public static String randomOnlyNumbers(int length) {
        Random random = new Random(System.nanoTime());
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = RANDOM_NUMBERS[random.nextInt(RANDOM_NUMBERS.length)];
        }
        return new String(array);
    }

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
