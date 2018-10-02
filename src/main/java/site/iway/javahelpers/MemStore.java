package site.iway.javahelpers;

import java.util.concurrent.ConcurrentHashMap;

public class MemStore {

    private static final ConcurrentHashMap<String, Object> MAP;

    static {
        MAP = new ConcurrentHashMap<>();
    }

    public static <T> void put(String key, T value) {
        if (key == null) {
            throw new NullPointerException("The specified key or value is null.");
        }
        if (value == null)
            remove(key);
        else
            MAP.put(key, value);
    }

    public static <T> T get(String key, T defValue, Class<T> objectClass) {
        Object value = MAP.get(key);
        if (value == null) {
            return defValue;
        }
        Class valueClass = value.getClass();
        if (objectClass.isAssignableFrom(valueClass)) {
            return (T) value;
        } else {
            return defValue;
        }
    }

    public static boolean contains(String key) {
        return MAP.containsKey(key);
    }

    public static void remove(String key) {
        MAP.remove(key);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return get(key, defValue, Boolean.class);
    }

    public static byte getByte(String key, byte defValue) {
        return get(key, defValue, Byte.class);
    }

    public static short getShort(String key, short defValue) {
        return get(key, defValue, Short.class);
    }

    public static char getChar(String key, char defValue) {
        return get(key, defValue, Character.class);
    }

    public static float getFloat(String key, float defValue) {
        return get(key, defValue, Float.class);
    }

    public static int getInt(String key, int defValue) {
        return get(key, defValue, Integer.class);
    }

    public static double getDouble(String key, double defValue) {
        return get(key, defValue, Double.class);
    }

    public static long getLong(String key, long defValue) {
        return get(key, defValue, Long.class);
    }

    public static String getString(String key, String defValue) {
        return get(key, defValue, String.class);
    }

}
