package site.iway.javahelpers;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Prefs {

    private final File mPrefsFile;
    private final File mPrefsFileTemp;
    private final String mKey;
    private final ConcurrentHashMap<String, Object> mItems;

    private final AtomicBoolean mWillCommit;
    private final Object mCommitWaiter;
    private final Thread mCommitter;

    public Prefs(String prefsFile, String key) {
        mPrefsFile = new File(prefsFile);
        mPrefsFileTemp = new File(prefsFile + ".tmp");
        if (key != null) {
            int keyLength = key.length();
            if (keyLength == 24) {
                for (int i = 0; i < keyLength; i++) {
                    if (!CharHelper.isASCII(key.charAt(i))) {
                        throw new RuntimeException("The key can only be ascii codes.");
                    }
                }
            } else {
                throw new RuntimeException("The key length must be 24 if provided.");
            }
        }
        mKey = key;
        Serializable serializable = null;
        if (mPrefsFileTemp.exists()) {
            serializable = SerializableRW.read(mPrefsFileTemp, mKey);
        }
        if (serializable == null && mPrefsFile.exists()) {
            serializable = SerializableRW.read(mPrefsFile, mKey);
        }
        if (serializable instanceof PrefsMap) {
            mItems = (PrefsMap) serializable;
        } else {
            mItems = new PrefsMap();
        }
        mWillCommit = new AtomicBoolean();
        mCommitWaiter = new Object();
        mCommitter = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (mWillCommit.compareAndSet(true, false)) {
                        do {
                            try {
                                sleep(200);
                            } catch (InterruptedException e) {
                                // nothing
                            }
                        } while (mWillCommit.compareAndSet(true, false));
                        PrefsMap copiedItems = new PrefsMap(mItems);
                        if (SerializableRW.write(mPrefsFileTemp, mKey, copiedItems)) {
                            if (FileSystemHelper.copyFile(mPrefsFileTemp, mPrefsFile)) {
                                if (!mPrefsFileTemp.delete()) {
                                    mPrefsFileTemp.deleteOnExit();
                                }
                            }
                        }
                    } else {
                        try {
                            synchronized (mCommitWaiter) {
                                mCommitWaiter.wait();
                            }
                        } catch (InterruptedException e) {
                            // nothing
                        }
                    }
                }
            }
        };
        mCommitter.start();
    }

    public Prefs(String prefsFile) {
        this(prefsFile, null);
    }

    private String buildRealKey(String key, Class valueClass) {
        String className = valueClass.getSimpleName();
        return className + ":" + key;
    }

    private <T extends Serializable> T getObject(String key, Class<T> valueClass, T defValue) {
        String realKey = buildRealKey(key, valueClass);
        Object value = mItems.get(realKey);
        return value == null ? defValue : (T) value;
    }

    private void notifyItemChanged() {
        mWillCommit.set(true);
        synchronized (mCommitWaiter) {
            mCommitWaiter.notify();
        }
    }

    private <T extends Serializable> void putObject(String key, T value) {
        if (key == null) {
            throw new NullPointerException("The specified key or value is null.");
        }
        if (value == null) {
            remove(key);
        } else {
            Class valueClass = value.getClass();
            String realKey = buildRealKey(key, valueClass);
            Object oldValue = mItems.put(realKey, value);
            if (oldValue == null || !oldValue.equals(value)) {
                notifyItemChanged();
            }
        }
    }

    public boolean contains(String key) {
        return mItems.containsKey(key);
    }

    public void remove(String key) {
        Object oldValue = mItems.remove(key);
        if (oldValue != null) {
            notifyItemChanged();
        }
    }

    public boolean getBoolean(String key, boolean defValue) {
        return getObject(key, Boolean.class, defValue);
    }

    public void putBoolean(String key, boolean value) {
        putObject(key, value);
    }

    public boolean[] getBooleanArray(String key, boolean[] defValue) {
        return getObject(key, boolean[].class, defValue);
    }

    public void putBooleanArray(String key, boolean[] value) {
        putObject(key, value);
    }

    public byte getByte(String key, byte defValue) {
        return getObject(key, Byte.class, defValue);
    }

    public void putByte(String key, byte value) {
        putObject(key, value);
    }

    public byte[] getByteArray(String key, byte[] defValue) {
        return getObject(key, byte[].class, defValue);
    }

    public void putByteArray(String key, byte[] value) {
        putObject(key, value);
    }

    public short getShort(String key, short defValue) {
        return getObject(key, Short.class, defValue);
    }

    public void putShort(String key, short value) {
        putObject(key, value);
    }

    public short[] getShortArray(String key, short[] defValue) {
        return getObject(key, short[].class, defValue);
    }

    public void putShortArray(String key, short[] value) {
        putObject(key, value);
    }

    public char getChar(String key, char defValue) {
        return getObject(key, Character.class, defValue);
    }

    public void putChar(String key, char value) {
        putObject(key, value);
    }

    public char[] getCharArray(String key, char[] defValue) {
        return getObject(key, char[].class, defValue);
    }

    public void putCharArray(String key, char[] value) {
        putObject(key, value);
    }

    public float getFloat(String key, float defValue) {
        return getObject(key, Float.class, defValue);
    }

    public void putFloat(String key, float value) {
        putObject(key, value);
    }

    public float[] getFloatArray(String key, float[] defValue) {
        return getObject(key, float[].class, defValue);
    }

    public void putFloatArray(String key, float[] value) {
        putObject(key, value);
    }

    public int getInt(String key, int defValue) {
        return getObject(key, Integer.class, defValue);
    }

    public void putInt(String key, int value) {
        putObject(key, value);
    }

    public int[] getIntArray(String key, int[] defValue) {
        return getObject(key, int[].class, defValue);
    }

    public void putIntArray(String key, int[] value) {
        putObject(key, value);
    }

    public double getDouble(String key, double defValue) {
        return getObject(key, Double.class, defValue);
    }

    public void putDouble(String key, double value) {
        putObject(key, value);
    }

    public double[] getDoubleArray(String key, double[] defValue) {
        return getObject(key, double[].class, defValue);
    }

    public void putDoubleArray(String key, double[] value) {
        putObject(key, value);
    }

    public long getLong(String key, long defValue) {
        return getObject(key, Long.class, defValue);
    }

    public void putLong(String key, long value) {
        putObject(key, value);
    }

    public long[] getLongArray(String key, long[] defValue) {
        return getObject(key, long[].class, defValue);
    }

    public void putLongArray(String key, long[] value) {
        putObject(key, value);
    }

    public String getString(String key, String defValue) {
        return getObject(key, String.class, defValue);
    }

    public void putString(String key, String value) {
        putObject(key, value);
    }

    public String[] getStringArray(String key, String[] defValue) {
        return getObject(key, String[].class, defValue);
    }

    public void putStringArray(String key, String[] value) {
        putObject(key, value);
    }

}
