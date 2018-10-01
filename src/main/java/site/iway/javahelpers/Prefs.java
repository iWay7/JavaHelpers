package site.iway.javahelpers;

import java.io.Serializable;

public class Prefs {

    private final String mPrefsFile;
    private final String mKey;
    private final PrefsMap mItems;

    private volatile boolean mWillCommit;
    private final Object mCommitWaiter;
    private final Thread mCommitter;

    public Prefs(String prefsFile, String key) {
        mPrefsFile = prefsFile;
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
        Serializable serializable = SerializableRW.read(mPrefsFile, mKey);
        if (serializable instanceof PrefsMap) {
            mItems = (PrefsMap) serializable;
        } else {
            mItems = new PrefsMap();
        }
        mCommitWaiter = new Object();
        mCommitter = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (mWillCommit) {
                        mWillCommit = false;
                        PrefsMap copiedItems = new PrefsMap(mItems);
                        SerializableRW.write(mPrefsFile, mKey, copiedItems);
                    } else {
                        try {
                            synchronized (mCommitWaiter) {
                                mCommitWaiter.wait();
                            }
                            sleep(200);
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

    public <T extends Serializable> T getObject(String key, T defValue, Class<T> objectClass) {
        Object value = mItems.get(key);
        if (value == null) {
            return defValue;
        }
        Class valueClass = value.getClass();
        if (objectClass.isAssignableFrom(valueClass)) {
            return (T) value;
        } else {
            return defValue;
        }
    }

    private void notifyItemChanged() {
        mWillCommit = true;
        synchronized (mCommitWaiter) {
            mCommitWaiter.notify();
        }
    }

    public <T extends Serializable> void putObject(String key, T value) {
        if (key == null) {
            throw new NullPointerException("The specified key or value is null.");
        }
        if (value == null) {
            remove(key);
        } else {
            Object oldValue = mItems.put(key, value);
            if (oldValue == null || !oldValue.equals(value)) {
                notifyItemChanged();
            }
        }
    }

    public boolean getBoolean(String key, boolean defValue) {
        return getObject(key, defValue, Boolean.class);
    }

    public void putBoolean(String key, boolean value) {
        putObject(key, value);
    }

    public byte getByte(String key, byte defValue) {
        return getObject(key, defValue, Byte.class);
    }

    public void putByte(String key, byte value) {
        putObject(key, value);
    }

    public short getShort(String key, short defValue) {
        return getObject(key, defValue, Short.class);
    }

    public void putShort(String key, short value) {
        putObject(key, value);
    }

    public char getChar(String key, char defValue) {
        return getObject(key, defValue, Character.class);
    }

    public void putChar(String key, char value) {
        putObject(key, value);
    }

    public float getFloat(String key, float defValue) {
        return getObject(key, defValue, Float.class);
    }

    public void putFloat(String key, float value) {
        putObject(key, value);
    }

    public int getInt(String key, int defValue) {
        return getObject(key, defValue, Integer.class);
    }

    public void putInt(String key, int value) {
        putObject(key, value);
    }

    public double getDouble(String key, double defValue) {
        return getObject(key, defValue, Double.class);
    }

    public void putDouble(String key, double value) {
        putObject(key, value);
    }

    public long getLong(String key, long defValue) {
        return getObject(key, defValue, Long.class);
    }

    public void putLong(String key, long value) {
        putObject(key, value);
    }

    public boolean contains(String key) {
        return mItems.contains(key);
    }

    public void remove(String key) {
        Object oldValue = mItems.remove(key);
        if (oldValue != null) {
            notifyItemChanged();
        }
    }

}
