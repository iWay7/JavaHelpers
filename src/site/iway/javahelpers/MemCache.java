package site.iway.javahelpers;

import java.util.HashMap;
import java.util.Map;

public class MemCache {

    private static Object mSynchronizer = new Object();
    private static Map<String, Object> mMap = new HashMap<>();

    public static boolean getBoolean(String key, boolean defValue) {
        synchronized (mSynchronizer) {
            if (mMap.containsKey(key))
                return (Boolean) mMap.remove(key);
            return defValue;
        }
    }

    public static void putBoolean(String key, boolean value) {
        synchronized (mSynchronizer) {
            mMap.put(key, value);
        }
    }

    public static byte getByte(String key, byte defValue) {
        synchronized (mSynchronizer) {
            if (mMap.containsKey(key))
                return (Byte) mMap.remove(key);
            return defValue;
        }
    }

    public static void putByte(String key, byte value) {
        synchronized (mSynchronizer) {
            mMap.put(key, value);
        }
    }

    public static short getShort(String key, short defValue) {
        synchronized (mSynchronizer) {
            if (mMap.containsKey(key))
                return (Short) mMap.remove(key);
            return defValue;
        }
    }

    public static void putShort(String key, short value) {
        synchronized (mSynchronizer) {
            mMap.put(key, value);
        }
    }

    public static char getChar(String key, char defValue) {
        synchronized (mSynchronizer) {
            if (mMap.containsKey(key))
                return (Character) mMap.remove(key);
            return defValue;
        }
    }

    public static void putChar(String key, char value) {
        synchronized (mSynchronizer) {
            mMap.put(key, value);
        }
    }

    public static float getFloat(String key, float defValue) {
        synchronized (mSynchronizer) {
            if (mMap.containsKey(key))
                return (Float) mMap.remove(key);
            return defValue;
        }
    }

    public static void putFloat(String key, float value) {
        synchronized (mSynchronizer) {
            mMap.put(key, value);
        }
    }

    public static int getInt(String key, int defValue) {
        synchronized (mSynchronizer) {
            if (mMap.containsKey(key))
                return (Integer) mMap.remove(key);
            return defValue;
        }
    }

    public static void putInt(String key, int value) {
        synchronized (mSynchronizer) {
            mMap.put(key, value);
        }
    }

    public static double getDouble(String key, double defValue) {
        synchronized (mSynchronizer) {
            if (mMap.containsKey(key))
                return (Double) mMap.remove(key);
            return defValue;
        }
    }

    public static void putDouble(String key, double value) {
        synchronized (mSynchronizer) {
            mMap.put(key, value);
        }
    }

    public static long getLong(String key, long defValue) {
        synchronized (mSynchronizer) {
            if (mMap.containsKey(key))
                return (Long) mMap.remove(key);
            return defValue;
        }
    }

    public static void putLong(String key, long value) {
        synchronized (mSynchronizer) {
            mMap.put(key, value);
        }
    }

    public static <T> T getObject(String name) {
        synchronized (mSynchronizer) {
            return (T) mMap.remove(name);
        }
    }

    public static <T> void putObject(String key, T object) {
        synchronized (mSynchronizer) {
            mMap.put(key, object);
        }
    }

}
