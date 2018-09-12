package site.iway.javahelpers;

import java.io.File;

public class ObjectSaver {

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

    private static void checkName(String name) {
        if (StringHelper.nullOrEmpty(name)) {
            throw new RuntimeException("The name can not be null or empty.");
        }
    }

    public static <T> T read(String name) {
        checkName(name);
        if (mNameToMD5) {
            name = StringHelper.md5(name);
        }
        String filePath = mCachePath + name;
        return (T) ObjectRW.read(filePath, mKey);
    }

    public static boolean save(String name, Object obj) {
        checkName(name);
        if (mNameToMD5) {
            name = StringHelper.md5(name);
        }
        String filePath = mCachePath + name;
        return ObjectRW.write(filePath, mKey, obj);
    }

    public static boolean delete(String name) {
        checkName(name);
        if (mNameToMD5) {
            name = StringHelper.md5(name);
        }
        return new File(mCachePath + name).delete();
    }

    public static boolean exists(String name) {
        checkName(name);
        if (mNameToMD5) {
            name = StringHelper.md5(name);
        }
        return new File(mCachePath + name).exists();
    }

}
