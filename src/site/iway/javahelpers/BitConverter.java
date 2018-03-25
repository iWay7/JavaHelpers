package site.iway.javahelpers;

public class BitConverter {

    public static boolean getBoolean(byte[] byteArray, int offset) {
        return byteArray[offset] != 0;
    }

    public static char getChar(byte[] byteArray, int offset) {
        return (char) ((byteArray[offset + 1] & 255) + (byteArray[offset] << 8));
    }

    public static short getShort(byte[] byteArray, int offset) {
        return (short) ((byteArray[offset + 1] & 255) + (byteArray[offset] << 8));
    }

    public static int getInt(byte[] byteArray, int offset) {
        return (byteArray[offset + 3] & 255) +
                ((byteArray[offset + 2] & 255) << 8) +
                ((byteArray[offset + 1] & 255) << 16) +
                (byteArray[offset] << 24);
    }

    public static long getLong(byte[] byteArray, int offset) {
        return ((long) byteArray[offset + 7] & 255L) +
                (((long) byteArray[offset + 6] & 255L) << 8) +
                (((long) byteArray[offset + 5] & 255L) << 16) +
                (((long) byteArray[offset + 4] & 255L) << 24) +
                (((long) byteArray[offset + 3] & 255L) << 32) +
                (((long) byteArray[offset + 2] & 255L) << 40) +
                (((long) byteArray[offset + 1] & 255L) << 48) +
                ((long) byteArray[offset] << 56);
    }

    public static float getFloat(byte[] byteArray, int offset) {
        return Float.intBitsToFloat(getInt(byteArray, offset));
    }

    public static double getDouble(byte[] byteArray, int offset) {
        return Double.longBitsToDouble(getLong(byteArray, offset));
    }

    public static void putBoolean(byte[] byteArray, int offset, boolean value) {
        byteArray[offset] = (byte) (value ? 1 : 0);
    }

    public static void putChar(byte[] byteArray, int offset, char value) {
        byteArray[offset + 1] = (byte) value;
        byteArray[offset] = (byte) (value >>> 8);
    }

    public static void putShort(byte[] byteArray, int offset, short value) {
        byteArray[offset + 1] = (byte) value;
        byteArray[offset] = (byte) (value >>> 8);
    }

    public static void putInt(byte[] byteArray, int offset, int value) {
        byteArray[offset + 3] = (byte) value;
        byteArray[offset + 2] = (byte) (value >>> 8);
        byteArray[offset + 1] = (byte) (value >>> 16);
        byteArray[offset] = (byte) (value >>> 24);
    }

    public static void putLong(byte[] byteArray, int offset, long value) {
        byteArray[offset + 7] = (byte) (int) value;
        byteArray[offset + 6] = (byte) (int) (value >>> 8);
        byteArray[offset + 5] = (byte) (int) (value >>> 16);
        byteArray[offset + 4] = (byte) (int) (value >>> 24);
        byteArray[offset + 3] = (byte) (int) (value >>> 32);
        byteArray[offset + 2] = (byte) (int) (value >>> 40);
        byteArray[offset + 1] = (byte) (int) (value >>> 48);
        byteArray[offset] = (byte) (int) (value >>> 56);
    }

    public static void putFloat(byte[] byteArray, int offset, float value) {
        putInt(byteArray, offset, Float.floatToIntBits(value));
    }

    public static void putDouble(byte[] byteArray, int offset, double value) {
        putLong(byteArray, offset, Double.doubleToLongBits(value));
    }

}