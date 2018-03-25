package site.iway.javahelpers;

import java.io.*;

public class ObjectSaver {

    private static String mCachePath;
    private static boolean mNameToMD5;

    public static void initialize(String cachePath, boolean nameToMD5) {
        mCachePath = cachePath;
        if (mCachePath.endsWith(File.separator) == false) {
            mCachePath += File.separator;
        }
        File file = new File(mCachePath);
        if (file.exists() == false) {
            file.mkdirs();
        }
        mNameToMD5 = nameToMD5;
    }

    public static void initialize(String cachePath) {
        initialize(cachePath, false);
    }

    public static <T> T read(String name) {
        if (mNameToMD5) {
            byte[] data = name.getBytes();
            byte[] md5 = SecurityHelper.md5(data);
            name = StringHelper.hex(md5);
        }
        File file = new File(mCachePath + name);
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            return null;
        } finally {
            try {
                objectInputStream.close();
            } catch (Exception e) {
                // nothing
            }
        }
    }

    public static boolean save(String name, Object obj) {
        if (mNameToMD5) {
            byte[] data = name.getBytes();
            byte[] md5 = SecurityHelper.md5(data);
            name = StringHelper.hex(md5);
        }
        File file = new File(mCachePath + name);
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (Exception e) {
            try {
                objectOutputStream.close();
            } catch (Exception exception) {
                // nothing
            }
            file.delete();
        }
        return false;
    }

    public static boolean delete(String name) {
        return new File(mCachePath + name).delete();
    }

    public static boolean exists(String name) {
        return new File(mCachePath + name).exists();
    }

}
