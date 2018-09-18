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

    public static void putBoolean(String key, boolean value) {
        sPrefs.putBoolean(key, value);
    }

    public static boolean[] getBooleanArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putBooleanArray(String key, boolean[] value) {
        sPrefs.putObject(key, value);
    }

    public static byte getByte(String key, byte defValue) {
        return sPrefs.getByte(key, defValue);
    }

    public static void putByte(String key, byte value) {
        sPrefs.putByte(key, value);
    }

    public byte[] getByteArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putByteArray(String key, byte[] value) {
        sPrefs.putObject(key, value);
    }

    public static short getShort(String key, short defValue) {
        return sPrefs.getShort(key, defValue);
    }

    public static void putShort(String key, short value) {
        sPrefs.putShort(key, value);
    }

    public static short[] getShortArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putShortArray(String key, short[] value) {
        sPrefs.putObject(key, value);
    }

    public static char getChar(String key, char defValue) {
        return sPrefs.getChar(key, defValue);
    }

    public static void putChar(String key, char value) {
        sPrefs.putChar(key, value);
    }

    public static char[] getCharArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putCharArray(String key, char[] value) {
        sPrefs.putObject(key, value);
    }

    public static float getFloat(String key, float defValue) {
        return sPrefs.getFloat(key, defValue);
    }

    public static void putFloat(String key, float value) {
        sPrefs.putFloat(key, value);
    }

    public static float[] getFloatArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putFloatArray(String key, float[] value) {
        sPrefs.putObject(key, value);
    }

    public static int getInt(String key, int defValue) {
        return sPrefs.getInt(key, defValue);
    }

    public static void putInt(String key, int value) {
        sPrefs.putInt(key, value);
    }

    public static int[] getIntArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putIntArray(String key, int[] value) {
        sPrefs.putObject(key, value);
    }

    public static double getDouble(String key, double defValue) {
        return sPrefs.getDouble(key, defValue);
    }

    public static void putDouble(String key, double value) {
        sPrefs.putDouble(key, value);
    }

    public static double[] getDoubleArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putDoubleArray(String key, double[] value) {
        sPrefs.putObject(key, value);
    }

    public static long getLong(String key, long defValue) {
        return sPrefs.getLong(key, defValue);
    }

    public static void putLong(String key, long value) {
        sPrefs.putLong(key, value);
    }

    public static long[] getLongArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putLongArray(String key, long value[]) {
        sPrefs.putObject(key, value);
    }

    public static String getString(String key) {
        return sPrefs.getObject(key);
    }

    public static void putString(String key, String value) {
        sPrefs.putObject(key, value);
    }

    public static String[] getStringArray(String key) {
        return sPrefs.getObject(key);
    }

    public static void putStringArray(String key, String[] value) {
        sPrefs.putObject(key, value);
    }

    public static boolean contains(String key) {
        return sPrefs.contains(key);
    }

    public static void remove(String key) {
        sPrefs.remove(key);
    }

    public static boolean commit() {
        return sPrefs.commit();
    }

}
