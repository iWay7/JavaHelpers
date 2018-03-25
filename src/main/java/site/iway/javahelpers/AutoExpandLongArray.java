package site.iway.javahelpers;

public class AutoExpandLongArray {

    private long[] mArray;
    private long[] mLong;
    private int mLength;

    public AutoExpandLongArray(int initSize) {
        mArray = new long[initSize];
        mLong = new long[1];
        mLength = 0;
    }

    public AutoExpandLongArray() {
        this(1 * 1024);
    }

    public void add(long[] data, int startIndex, int count) {
        int targetLength = mLength + count;
        if (targetLength >= mArray.length) {
            long[] newArray = new long[targetLength * 2];
            System.arraycopy(mArray, 0, newArray, 0, mLength);
            mArray = newArray;
        }
        System.arraycopy(data, startIndex, mArray, mLength, count);
        mLength += count;
    }

    public void add(long[] ls) {
        add(ls, 0, ls.length);
    }

    public void add(long l) {
        mLong[0] = l;
        add(mLong);
    }

    public long get(int index) {
        if (index > -1 && index < mLength) {
            return mArray[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void set(int index, long l) {
        if (index > -1 && index < mLength) {
            mArray[index] = l;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean has(long l) {
        for (int i = 0; i < mLength; i++) {
            if (mArray[i] == l) {
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

    public long[] getOutArray() {
        long[] array = new long[mLength];
        System.arraycopy(mArray, 0, array, 0, mLength);
        return array;
    }

    public long[] getRawArray() {
        return mArray;
    }

}
