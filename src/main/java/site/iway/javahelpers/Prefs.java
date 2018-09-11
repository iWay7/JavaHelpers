package site.iway.javahelpers;

import java.io.Serializable;
import java.util.HashMap;

public class Prefs {

    private Object mSynchronizer;
    private String mPrefsFile;
    private String mKey;
    private boolean mItemsChanged;
    private HashMap<String, Object> mItems;

    public Prefs(String prefsFile, String key) {
        mSynchronizer = new Object();
        mPrefsFile = prefsFile;
        if (key != null && key.length() != 24) {
            throw new RuntimeException("The key length must be 24 if provided.");
        }
        mKey = key;
        mItemsChanged = false;
        mItems = ObjectIO.read(mPrefsFile, mKey);
        if (mItems == null) {
            mItems = new HashMap<>();
        }
    }

    public Prefs(String prefsFile) {
        this(prefsFile, null);
    }

    public boolean getBoolean(String key, boolean defValue) {
        synchronized (mSynchronizer) {
            if (mItems.containsKey(key))
                return (Boolean) mItems.get(key);
            return defValue;
        }
    }

    public void putBoolean(String key, boolean value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public byte getByte(String key, byte defValue) {
        synchronized (mSynchronizer) {
            if (mItems.containsKey(key))
                return (Byte) mItems.get(key);
            return defValue;
        }
    }

    public void putByte(String key, byte value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public short getShort(String key, short defValue) {
        synchronized (mSynchronizer) {
            if (mItems.containsKey(key))
                return (Short) mItems.get(key);
            return defValue;
        }
    }

    public void putShort(String key, short value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public char getChar(String key, char defValue) {
        synchronized (mSynchronizer) {
            if (mItems.containsKey(key))
                return (Character) mItems.get(key);
            return defValue;
        }
    }

    public void putChar(String key, char value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public float getFloat(String key, float defValue) {
        synchronized (mSynchronizer) {
            if (mItems.containsKey(key))
                return (Float) mItems.get(key);
            return defValue;
        }
    }

    public void putFloat(String key, float value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public int getInt(String key, int defValue) {
        synchronized (mSynchronizer) {
            if (mItems.containsKey(key))
                return (Integer) mItems.get(key);
            return defValue;
        }
    }

    public void putInt(String key, int value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public double getDouble(String key, double defValue) {
        synchronized (mSynchronizer) {
            if (mItems.containsKey(key))
                return (Double) mItems.get(key);
            return defValue;
        }
    }

    public void putDouble(String key, double value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public long getLong(String key, long defValue) {
        synchronized (mSynchronizer) {
            if (mItems.containsKey(key))
                return (Long) mItems.get(key);
            return defValue;
        }
    }

    public void putLong(String key, long value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public <T extends Serializable> T getObject(String key) {
        synchronized (mSynchronizer) {
            return (T) mItems.get(key);
        }
    }

    public <T extends Serializable> void putObject(String key, T value) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.put(key, value);
        }
    }

    public boolean contains(String key) {
        synchronized (mSynchronizer) {
            return mItems.get(key) != null;
        }
    }

    public void remove(String key) {
        synchronized (mSynchronizer) {
            mItemsChanged = true;
            mItems.remove(key);
        }
    }

    public boolean commit() {
        synchronized (mSynchronizer) {
            if (mItemsChanged) {
                if (ObjectIO.write(mPrefsFile, mKey, mItems)) {
                    mItemsChanged = false;
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

}
