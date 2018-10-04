package site.iway.javahelpers;

import java.io.File;
import java.io.Serializable;

public class ObjectStore {

    private static String mDirectory;
    private static boolean mNameToMD5;
    private static String mDESedeKey;

    public static void initialize(String directory, boolean nameToMD5, String desedekey) {
        if (!directory.endsWith(File.separator)) {
            directory += File.separator;
        }
        if (!FileSystemHelper.createDirectory(directory)) {
            throw new RuntimeException("Create store directory failed.");
        }
        mDirectory = directory;
        mNameToMD5 = nameToMD5;
        if (desedekey != null) {
            int keyLength = desedekey.length();
            if (keyLength == 24) {
                for (int i = 0; i < keyLength; i++) {
                    if (!CharHelper.isASCII(desedekey.charAt(i))) {
                        throw new RuntimeException("The key can only be ascii codes.");
                    }
                }
            } else {
                throw new RuntimeException("The key length must be 24 if provided.");
            }
        }
        mDESedeKey = desedekey;
    }

    public static void initialize(String directory, boolean nameToMD5) {
        initialize(directory, nameToMD5, null);
    }

    public static void initialize(String directory) {
        initialize(directory, false);
    }

    private static String getObjectFilePath(String name) {
        if (StringHelper.nullOrEmpty(name)) {
            throw new RuntimeException("The name can not be null or empty.");
        }
        if (mNameToMD5) {
            name = StringHelper.md5(name);
        }
        return mDirectory + name;
    }

    public static <T extends Serializable> T read(String name, Class<T> objectClass) {
        String objectFilePath = getObjectFilePath(name);
        Serializable serializable = SerializableRW.read(objectFilePath, mDESedeKey);
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
        String objectFilePath = getObjectFilePath(name);
        return (T) SerializableRW.read(objectFilePath, mDESedeKey);
    }

    public static boolean write(String name, Serializable object) {
        String objectFilePath = getObjectFilePath(name);
        return SerializableRW.write(objectFilePath, mDESedeKey, object);
    }

    public static boolean delete(String name) {
        String objectFilePath = getObjectFilePath(name);
        return new File(objectFilePath + name).delete();
    }

    public static boolean exists(String name) {
        String objectFilePath = getObjectFilePath(name);
        return new File(objectFilePath + name).exists();
    }

}
