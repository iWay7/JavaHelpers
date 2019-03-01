package site.iway.javahelpers;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HanziPinyinHelper {

    private static int HANZI_COUNT = 6727;
    private static int PINYIN_COUNT = 629;

    private static char[] mHanziArray;
    private static short[] mRefsArray;
    private static String[] mPinyinArray;

    public static void initialize() {
        try (InputStream isHanziRef = HanziPinyinHelper.class.getResourceAsStream("/hanzi_ref.dat");
             BufferedInputStream bisHanziRef = new BufferedInputStream(isHanziRef);
             InputStream isPinyin = HanziPinyinHelper.class.getResourceAsStream("/pinyin.dat");
             BufferedInputStream bisPinyin = new BufferedInputStream(isPinyin);
             InputStreamReader isrPinyin = new InputStreamReader(bisPinyin, StandardCharsets.UTF_8);
             BufferedReader brPinyin = new BufferedReader(isrPinyin, 16 * 1024)) {
            mHanziArray = new char[HANZI_COUNT];
            mRefsArray = new short[HANZI_COUNT];
            mPinyinArray = new String[PINYIN_COUNT];
            byte[] buffer = new byte[32 * 1024];
            int startIndex = 0;
            int readCount = bisHanziRef.read(buffer);
            while (readCount > -1) {
                startIndex += readCount;
                readCount = bisHanziRef.read(buffer, startIndex, buffer.length - startIndex);
            }
            for (int i = 0; i < HANZI_COUNT; i++) {
                mHanziArray[i] = BitConverter.getChar(buffer, i * 4);
                mRefsArray[i] = BitConverter.getShort(buffer, i * 4 + 2);
            }
            for (int i = 0; i < PINYIN_COUNT; i++) {
                mPinyinArray[i] = brPinyin.readLine();
            }
        } catch (IOException e) {
            // nothing
        }
    }

    private static int getRefIndex(char hanzi) {
        int lo = 0;
        int hi = HANZI_COUNT - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int midValCmp = mHanziArray[mid] - hanzi;
            if (midValCmp < 0) {
                lo = mid + 1;
            } else if (midValCmp > 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static String getPinyin(char c) {
        int refIndex = getRefIndex(c);
        if (refIndex > -1) {
            int pinyinIndex = mRefsArray[refIndex];
            String pinyin = mPinyinArray[pinyinIndex];
            int emptyIndex = pinyin.indexOf(' ');
            if (emptyIndex > -1) {
                pinyin = pinyin.substring(0, emptyIndex);
            }
            return pinyin;
        }
        return null;
    }

    public static String getAllPinyins(char c, char splitter) {
        int refIndex = getRefIndex(c);
        if (refIndex > -1) {
            int pinyinIndex = mRefsArray[refIndex];
            String pinyin = mPinyinArray[pinyinIndex];
            if (pinyin != null) {
                if (splitter == ' ') {
                    return pinyin;
                } else {
                    return pinyin.replace(' ', splitter);
                }
            }
        }
        return null;
    }

    public static String[] getAllPinyins(char c) {
        String pinyin = getPinyin(c);
        if (pinyin != null) {
            return pinyin.split(" ");
        }
        return null;
    }

    public static String getPinyin(String s) {
        StringBuilder builder = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            String pinyin = getPinyin(c);
            builder.append(pinyin == null ? c : pinyin);
        }
        return builder.toString();
    }

    public static boolean isHanzi(char c) {
        return getRefIndex(c) > -1;
    }

    public static void freeMemory() {
        mHanziArray = null;
        mRefsArray = null;
        mPinyinArray = null;
    }

}
