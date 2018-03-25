package site.iway.javahelpers;

public class AutoExpandShortArray {

    private short[] mArray;
    private short[] mShort;
    private int mLength;

    public AutoExpandShortArray(int initSize) {
        mArray = new short[initSize];
        mShort = new short[1];
        mLength = 0;
    }

    public AutoExpandShortArray() {
        this(4 * 1024);
    }

    public void add(short[] data, int startIndex, int count) {
        int targetLength = mLength + count;
        if (targetLength >= mArray.length) {
            short[] newArray = new short[targetLength * 2];
            System.arraycopy(mArray, 0, newArray, 0, mLength);
            mArray = newArray;
        }
        System.arraycopy(data, startIndex, mArray, mLength, count);
        mLength += count;
    }

    public void add(short[] ss) {
        add(ss, 0, ss.length);
    }

    public void add(short s) {
        mShort[0] = s;
        add(mShort);
    }

    public short get(int index) {
        if (index > -1 && index < mLength) {
            return mArray[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void set(int index, short s) {
        if (index > -1 && index < mLength) {
            mArray[index] = s;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean has(short s) {
        for (int i = 0; i < mLength; i++) {
            if (mArray[i] == s) {
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

    public short[] getOutArray() {
        short[] array = new short[mLength];
        System.arraycopy(mArray, 0, array, 0, mLength);
        return array;
    }

    public short[] getRawArray() {
        return mArray;
    }

}
