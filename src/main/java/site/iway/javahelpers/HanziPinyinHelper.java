package site.iway.javahelpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HanziPinyinHelper {

    private static int HANZI_COUNT = 6727;
    private static int PINYIN_COUNT = 629;

    private static char[] mHanziArray;
    private static short[] mRefsArray;
    private static String[] mPinyinArray;

    private static void initialize(InputStream isHanziRef, InputStream isPinyin) throws IOException {
        mHanziArray = new char[HANZI_COUNT];
        mRefsArray = new short[HANZI_COUNT];
        mPinyinArray = new String[PINYIN_COUNT];

        byte[] buffer = new byte[16 * 1024];
        int readCount = isHanziRef.read(buffer);
        int index = 0;
        while (readCount > -1) {
            for (int i = 0; i < readCount; i += 4) {
                mHanziArray[index] = BitConverter.getChar(buffer, i);
                mRefsArray[index] = BitConverter.getShort(buffer, i + 2);
                index++;
            }

            readCount = isHanziRef.read(buffer);
        }
        isHanziRef.close();

        InputStreamReader pinyinReader = new InputStreamReader(isPinyin, "utf-8");
        BufferedReader pinyinBufferedReader = new BufferedReader(pinyinReader, 16 * 1024);
        for (int i = 0; i < PINYIN_COUNT; i++) {
            mPinyinArray[i] = pinyinBufferedReader.readLine();
        }
        pinyinBufferedReader.close();
    }

    public static void initialize() {
        try {
            InputStream isHanziRef = HanziPinyinHelper.class.getResourceAsStream("/hanzi_ref.dat");
            InputStream isPinyin = HanziPinyinHelper.class.getResourceAsStream("/pinyin.dat");
            initialize(isHanziRef, isPinyin);
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

    public static boolean isHanzi(char c) {
        return getRefIndex(c) > -1;
    }

    public static void freeMemory() {
        mHanziArray = null;
        mRefsArray = null;
        mPinyinArray = null;
    }

}
