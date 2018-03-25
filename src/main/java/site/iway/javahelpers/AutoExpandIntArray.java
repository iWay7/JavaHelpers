package site.iway.javahelpers;

public class AutoExpandIntArray {

    private int[] mArray;
    private int[] mInt;
    private int mLength;

    public AutoExpandIntArray(int initSize) {
        mArray = new int[initSize];
        mInt = new int[1];
        mLength = 0;
    }

    public AutoExpandIntArray() {
        this(2 * 1024);
    }

    public void add(int[] data, int startIndex, int count) {
        int targetLength = mLength + count;
        if (targetLength >= mArray.length) {
            int[] newArray = new int[targetLength * 2];
            System.arraycopy(mArray, 0, newArray, 0, mLength);
            mArray = newArray;
        }
        System.arraycopy(data, startIndex, mArray, mLength, count);
        mLength += count;
    }

    public void add(int[] is) {
        add(is, 0, is.length);
    }

    public void add(int i) {
        mInt[0] = i;
        add(mInt);
    }

    public int get(int index) {
        if (index > -1 && index < mLength) {
            return mArray[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void set(int index, int i) {
        if (index > -1 && index < mLength) {
            mArray[index] = i;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean has(int i) {
        for (int j = 0; j < mLength; j++) {
            if (mArray[j] == i) {
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

    public int[] getOutArray() {
        int[] array = new int[mLength];
        System.arraycopy(mArray, 0, array, 0, mLength);
        return array;
    }

    public int[] getRawArray() {
        return mArray;
    }

}
