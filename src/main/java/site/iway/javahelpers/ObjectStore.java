package site.iway.javahelpers;

import java.io.File;
import java.io.Serializable;

public class ObjectStore {

    private static String mCachePath;
    private static boolean mNameToMD5;
    private static String mKey;

    public static void initialize(String cachePath, boolean nameToMD5, String key) {
        if (!cachePath.endsWith(File.separator)) {
            cachePath += File.separator;
        }
        File file = new File(cachePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("Create cache directory failed.");
            }
        } else {
            if (file.isFile()) {
                throw new RuntimeException("Cache path is not a directory.");
            }
        }
        mCachePath = cachePath;
        mNameToMD5 = nameToMD5;
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
    }

    public static void initialize(String cachePath, boolean nameToMD5) {
        initialize(cachePath, nameToMD5, null);
    }

    public static void initialize(String cachePath) {
        initialize(cachePath, false);
    }

    private static String getObjectFilePath(String name) {
        if (StringHelper.nullOrEmpty(name)) {
            throw new RuntimeException("The name can not be null or empty.");
        }
        if (mNameToMD5) {
            name = StringHelper.md5(name);
        }
        return mCachePath + name;
    }

    public static <T extends Serializable> T read(String name, Class<T> objectClass) {
        String cacheFilePath = getObjectFilePath(name);
        Serializable serializable = SerializableRW.read(cacheFilePath, mKey);
        if (serializable == null) {
            return null;
        }
        Class serializableClass = serializable.getClass();
        if (objectClass.isAssignableFrom(serializableClass)) {
            return (T) serializable;
        } else {
            return null;
        }
    }

    @Deprecated
    public static <T extends Serializable> T read(String name) {
        String cacheFilePath = getObjectFilePath(name);
        return (T) SerializableRW.read(cacheFilePath, mKey);
    }

    public static boolean write(String name, Serializable object) {
        String cacheFilePath = getObjectFilePath(name);
        return SerializableRW.write(cacheFilePath, mKey, object);
    }

    public static boolean delete(String name) {
        String cacheFilePath = getObjectFilePath(name);
        return new File(cacheFilePath + name).delete();
    }

    public static boolean exists(String name) {
        String cacheFilePath = getObjectFilePath(name);
        return new File(cacheFilePath + name).exists();
    }

}
