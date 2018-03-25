package site.iway.javahelpers;

import java.util.Random;

public class ArrayHelper {

    public static boolean[] combine(boolean[]... arrays) {
        int totalLength = 0;
        for (boolean[] booleans : arrays) {
            totalLength += booleans.length;
        }
        boolean[] newArray = new boolean[totalLength];
        int pointer = 0;
        for (boolean[] booleans : arrays) {
            System.arraycopy(booleans, 0, newArray, pointer, booleans.length);
            pointer += booleans.length;
        }
        return newArray;
    }

    public static byte[] combine(byte[]... arrays) {
        int totalLength = 0;
        for (byte[] bytes : arrays) {
            totalLength += bytes.length;
        }
        byte[] newArray = new byte[totalLength];
        int pointer = 0;
        for (byte[] bytes : arrays) {
            System.arraycopy(bytes, 0, newArray, pointer, bytes.length);
            pointer += bytes.length;
        }
        return newArray;
    }

    public static char[] combine(char[]... arrays) {
        int totalLength = 0;
        for (char[] chars : arrays) {
            totalLength += chars.length;
        }
        char[] newArray = new char[totalLength];
        int pointer = 0;
        for (char[] chars : arrays) {
            System.arraycopy(chars, 0, newArray, pointer, chars.length);
            pointer += chars.length;
        }
        return newArray;
    }

    public static double[] combine(double[]... arrays) {
        int totalLength = 0;
        for (double[] doubles : arrays) {
            totalLength += doubles.length;
        }
        double[] newArray = new double[totalLength];
        int pointer = 0;
        for (double[] doubles : arrays) {
            System.arraycopy(doubles, 0, newArray, pointer, doubles.length);
            pointer += doubles.length;
        }
        return newArray;
    }

    public static float[] combine(float[]... arrays) {
        int totalLength = 0;
        for (float[] floats : arrays) {
            totalLength += floats.length;
        }
        float[] newArray = new float[totalLength];
        int pointer = 0;
        for (float[] floats : arrays) {
            System.arraycopy(floats, 0, newArray, pointer, floats.length);
            pointer += floats.length;
        }
        return newArray;
    }

    public static int[] combine(int[]... arrays) {
        int totalLength = 0;
        for (int[] ints : arrays) {
            totalLength += ints.length;
        }
        int[] newArray = new int[totalLength];
        int pointer = 0;
        for (int[] ints : arrays) {
            System.arraycopy(ints, 0, newArray, pointer, ints.length);
            pointer += ints.length;
        }
        return newArray;
    }

    public static long[] combine(long[]... arrays) {
        int totalLength = 0;
        for (long[] longs : arrays) {
            totalLength += longs.length;
        }
        long[] newArray = new long[totalLength];
        int pointer = 0;
        for (long[] longs : arrays) {
            System.arraycopy(longs, 0, newArray, pointer, longs.length);
            pointer += longs.length;
        }
        return newArray;
    }

    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static int countOf(boolean[] array, boolean item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                count++;
            }
        }
        return count;
    }

    public static int countOf(byte[] array, byte item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                count++;
            }
        }
        return count;
    }

    public static int countOf(char[] array, char item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                count++;
            }
        }
        return count;
    }

    public static int countOf(double[] array, double item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                count++;
            }
        }
        return count;
    }

    public static int countOf(float[] array, float item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                count++;
            }
        }
        return count;
    }

    public static int countOf(int[] array, int item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                count++;
            }
        }
        return count;
    }

    public static int countOf(long[] array, long item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                count++;
            }
        }
        return count;
    }

    public static <T> int countOf(T[] array, T item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                count++;
            }
        }
        return count;
    }

    public static int countOf(short[] array, short item) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                count++;
            }
        }
        return count;
    }

    public static boolean contains(boolean[] array, boolean item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(byte[] array, byte item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(char[] array, char item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(double[] array, double item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(float[] array, float item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(int[] array, int item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(long[] array, long item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(T[] array, T item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(short[] array, short item) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(boolean[] array, boolean item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(byte[] array, byte item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(char[] array, char item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(double[] array, double item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(float[] array, float item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(int[] array, int item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(long[] array, long item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int indexOf(T[] array, T item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(short[] array, short item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(boolean[] array, boolean item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(byte[] array, byte item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(char[] array, char item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(double[] array, double item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(float[] array, float item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(int[] array, int item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(long[] array, long item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int lastIndexOf(T[] array, T item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(short[] array, short item) {
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] reverse(boolean[] array) {
        boolean[] newArray = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }

    public static byte[] reverse(byte[] array) {
        byte[] newArray = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }

    public static char[] reverse(char[] array) {
        char[] newArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }

    public static double[] reverse(double[] array) {
        double[] newArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }

    public static float[] reverse(float[] array) {
        float[] newArray = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }

    public static int[] reverse(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }

    public static long[] reverse(long[] array) {
        long[] newArray = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }

    public static <T> T[] reverse(T[] array) {
        Object[] newArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return (T[]) newArray;
    }

    public static short[] reverse(short[] array) {
        short[] newArray = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }

    public static void checkParams(boolean[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static void checkParams(byte[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static void checkParams(char[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static void checkParams(double[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static void checkParams(float[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static void checkParams(int[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static void checkParams(long[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static <T> void checkParams(T[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static void checkParams(short[] array, int start, int end) {
        if (array == null || start > end || start < 0 || end > array.length) {
            throw new RuntimeException("Params check failed.");
        }
    }

    public static void swapItems(boolean[] array, int index1, int index2) {
        boolean temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void swapItems(byte[] array, int index1, int index2) {
        byte temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void swapItems(char[] array, int index1, int index2) {
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void swapItems(double[] array, int index1, int index2) {
        double temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void swapItems(float[] array, int index1, int index2) {
        float temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void swapItems(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void swapItems(long[] array, int index1, int index2) {
        long temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static <T> void swapItems(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void swapItems(short[] array, int index1, int index2) {
        short temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void shuffle(boolean[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static void shuffle(byte[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static void shuffle(char[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static void shuffle(double[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static void shuffle(float[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static void shuffle(int[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static void shuffle(long[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static <T> void shuffle(T[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static void shuffle(short[] array, int start, int end) {
        checkParams(array, start, end);
        Random random = new Random(System.nanoTime());
        int range = end - start;
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(range);
            swapItems(array, i, index);
        }
    }

    public static boolean[] classToStruct(Boolean[] array) {
        if (array == null)
            return null;
        boolean[] newArray = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static byte[] classToStruct(Byte[] array) {
        if (array == null)
            return null;
        byte[] newArray = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static char[] classToStruct(Character[] array) {
        if (array == null)
            return null;
        char[] newArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static double[] classToStruct(Double[] array) {
        if (array == null)
            return null;
        double[] newArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static float[] classToStruct(Float[] array) {
        if (array == null)
            return null;
        float[] newArray = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static int[] classToStruct(Integer[] array) {
        if (array == null)
            return null;
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static long[] classToStruct(Long[] array) {
        if (array == null)
            return null;
        long[] newArray = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static short[] classToStruct(Short[] array) {
        if (array == null)
            return null;
        short[] newArray = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Boolean[] structToClass(boolean[] array) {
        if (array == null)
            return null;
        Boolean[] newArray = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Byte[] structToClass(byte[] array) {
        if (array == null)
            return null;
        Byte[] newArray = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Character[] structToClass(char[] array) {
        if (array == null)
            return null;
        Character[] newArray = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Double[] structToClass(double[] array) {
        if (array == null)
            return null;
        Double[] newArray = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Float[] structToClass(float[] array) {
        if (array == null)
            return null;
        Float[] newArray = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Integer[] structToClass(int[] array) {
        if (array == null)
            return null;
        Integer[] newArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Long[] structToClass(long[] array) {
        if (array == null)
            return null;
        Long[] newArray = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Short[] structToClass(short[] array) {
        if (array == null)
            return null;
        Short[] newArray = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

}
