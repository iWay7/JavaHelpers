package site.iway.javahelpers;

public class PrefsStatic {

    private static Prefs sPrefs;

    public static void initialize(String fileName, String key) {
        if (sPrefs != null) {
            throw new RuntimeException("Can not initialize for multiple times.");
        }
        sPrefs = new Prefs(fileName, key);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sPrefs.getBoolean(key, defValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static void putBoolean(String key, boolean value) {
        sPrefs.putBoolean(key, value);
    }

    public static boolean[] getBooleanArray(String key, boolean[] defValue) {
        return sPrefs.getBooleanArray(key, defValue);
    }

    public static boolean[] getBooleanArray(String key) {
        return getBooleanArray(key, null);
    }

    public static void putBooleanArray(String key, boolean[] value) {
        sPrefs.putBooleanArray(key, value);
    }

    public static byte getByte(String key, byte defValue) {
        return sPrefs.getByte(key, defValue);
    }

    public static byte getByte(String key) {
        return getByte(key, (byte) 0);
    }

    public static void putByte(String key, byte value) {
        sPrefs.putByte(key, value);
    }

    public static byte[] getByteArray(String key, byte[] defValue) {
        return sPrefs.getByteArray(key, defValue);
    }

    public static byte[] getByteArray(String key) {
        return getByteArray(key, null);
    }

    public static void putByteArray(String key, byte[] value) {
        sPrefs.putByteArray(key, value);
    }

    public static short getShort(String key, short defValue) {
        return sPrefs.getShort(key, defValue);
    }

    public static short getShort(String key) {
        return getShort(key, (short) 0);
    }

    public static void putShort(String key, short value) {
        sPrefs.putShort(key, value);
    }

    public static short[] getShortArray(String key, short[] defValue) {
        return sPrefs.getShortArray(key, defValue);
    }

    public static short[] getShortArray(String key) {
        return getShortArray(key, null);
    }

    public static void putShortArray(String key, short[] value) {
        sPrefs.putShortArray(key, value);
    }

    public static char getChar(String key, char defValue) {
        return sPrefs.getChar(key, defValue);
    }

    public static char getChar(String key) {
        return getChar(key, (char) 0);
    }

    public static void putChar(String key, char value) {
        sPrefs.putChar(key, value);
    }

    public static char[] getCharArray(String key, char[] defValue) {
        return sPrefs.getCharArray(key, defValue);
    }

    public static char[] getCharArray(String key) {
        return getCharArray(key, null);
    }

    public static void putCharArray(String key, char[] value) {
        sPrefs.putCharArray(key, value);
    }

    public static float getFloat(String key, float defValue) {
        return sPrefs.getFloat(key, defValue);
    }

    public static float getFloat(String key) {
        return getFloat(key, 0);
    }

    public static void putFloat(String key, float value) {
        sPrefs.putFloat(key, value);
    }

    public static float[] getFloatArray(String key, float[] defValue) {
        return sPrefs.getFloatArray(key, defValue);
    }

    public static float[] getFloatArray(String key) {
        return getFloatArray(key, null);
    }

    public static void putFloatArray(String key, float[] value) {
        sPrefs.putFloatArray(key, value);
    }

    public static int getInt(String key, int defValue) {
        return sPrefs.getInt(key, defValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static void putInt(String key, int value) {
        sPrefs.putInt(key, value);
    }

    public static int[] getIntArray(String key, int[] defValue) {
        return sPrefs.getIntArray(key, defValue);
    }

    public static int[] getIntArray(String key) {
        return getIntArray(key, null);
    }

    public static void putIntArray(String key, int[] value) {
        sPrefs.putIntArray(key, value);
    }

    public static double getDouble(String key, double defValue) {
        return sPrefs.getDouble(key, defValue);
    }

    public static double getDouble(String key) {
        return getDouble(key, 0);
    }

    public static void putDouble(String key, double value) {
        sPrefs.putDouble(key, value);
    }

    public static double[] getDoubleArray(String key, double[] defValue) {
        return sPrefs.getDoubleArray(key, defValue);
    }

    public static double[] getDoubleArray(String key) {
        return getDoubleArray(key, null);
    }

    public static void putDoubleArray(String key, double[] value) {
        sPrefs.putDoubleArray(key, value);
    }

    public static long getLong(String key, long defValue) {
        return sPrefs.getLong(key, defValue);
    }

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static void putLong(String key, long value) {
        sPrefs.putLong(key, value);
    }

    public static long[] getLongArray(String key, long[] defValue) {
        return sPrefs.getLongArray(key, defValue);
    }

    public static long[] getLongArray(String key) {
        return getLongArray(key, null);
    }

    public static void putLongArray(String key, long[] value) {
        sPrefs.putLongArray(key, value);
    }

    public static String getString(String key, String defValue) {
        return sPrefs.getString(key, defValue);
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static void putString(String key, String value) {
        sPrefs.putString(key, value);
    }

    public static String[] getStringArray(String key, String[] defValue) {
        return sPrefs.getStringArray(key, defValue);
    }

    public static String[] getStringArray(String key) {
        return getStringArray(key, null);
    }

    public static void putString(String key, String[] value) {
        sPrefs.putStringArray(key, value);
    }

    public static boolean contains(String key) {
        return sPrefs.contains(key);
    }

    public static void remove(String key) {
        sPrefs.remove(key);
    }

}
