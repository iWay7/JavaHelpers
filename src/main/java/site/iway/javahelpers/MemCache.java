package site.iway.javahelpers;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemCache {

    private static final Map<String, SoftReference<Object>> MAP;

    static {
        MAP = new ConcurrentHashMap<>();
    }

    public static <T> void put(String key, T object) {
        MAP.put(key, new SoftReference<Object>(object));
    }

    public static <T> T get(String key) {
        SoftReference<Object> result = MAP.remove(key);
        if (result == null)
            return null;
        Object object = result.get();
        if (object == null)
            return null;
        return (T) object;
    }

    public static boolean getBoolean(String key, boolean defValue) {
        Boolean result = get(key);
        return result == null ? defValue : result;
    }

    public static byte getByte(String key, byte defValue) {
        Byte result = get(key);
        return result == null ? defValue : result;
    }

    public static short getShort(String key, short defValue) {
        Short result = get(key);
        return result == null ? defValue : result;
    }

    public static char getChar(String key, char defValue) {
        Character result = get(key);
        return result == null ? defValue : result;
    }

    public static float getFloat(String key, float defValue) {
        Float result = get(key);
        return result == null ? defValue : result;
    }

    public static int getInt(String key, int defValue) {
        Integer result = get(key);
        return result == null ? defValue : result;
    }

    public static double getDouble(String key, double defValue) {
        Double result = get(key);
        return result == null ? defValue : result;
    }

    public static long getLong(String key, long defValue) {
        Long result = get(key);
        return result == null ? defValue : result;
    }

}
