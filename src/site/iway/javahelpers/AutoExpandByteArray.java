package site.iway.javahelpers;

public class AutoExpandByteArray {

    private byte[] mArray;
    private byte[] mByte;
    private int mLength;

    public AutoExpandByteArray(int initSize) {
        mArray = new byte[initSize];
        mByte = new byte[1];
        mLength = 0;
    }

    public AutoExpandByteArray() {
        this(8 * 1024);
    }

    public void add(byte[] data, int startIndex, int count) {
        int targetLength = mLength + count;
        if (targetLength >= mArray.length) {
            byte[] newArray = new byte[targetLength * 2];
            System.arraycopy(mArray, 0, newArray, 0, mLength);
            mArray = newArray;
        }
        System.arraycopy(data, startIndex, mArray, mLength, count);
        mLength += count;
    }

    public void add(byte[] bs) {
        add(bs, 0, bs.length);
    }

    public void add(byte b) {
        mByte[0] = b;
        add(mByte);
    }

    public byte get(int index) {
        if (index > -1 && index < mLength) {
            return mArray[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void set(int index, byte b) {
        if (index > -1 && index < mLength) {
            mArray[index] = b;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean has(byte b) {
        for (int i = 0; i < mLength; i++) {
            if (mArray[i] == b) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        mLength = 0;
    }

    public int size() {
        return mLength;
    }

    public byte[] getOutArray() {
        byte[] array = new byte[mLength];
        System.arraycopy(mArray, 0, array, 0, mLength);
        return array;
    }

    public byte[] getRawArray() {
        return mArray;
    }

}
