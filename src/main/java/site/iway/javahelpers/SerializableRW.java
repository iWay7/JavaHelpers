package site.iway.javahelpers;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.Charset;

public class SerializableRW {

    public static Serializable read(File file, String desedeKey) {
        FileInputStream fileInputStream = null;
        CipherInputStream cipherInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            if (StringHelper.nullOrEmpty(desedeKey)) {
                objectInputStream = new ObjectInputStream(fileInputStream);
            } else {
                byte[] key = desedeKey.getBytes(Charset.forName("US-ASCII"));
                String algorithm = "DESede";
                Cipher cipher = Cipher.getInstance(algorithm);
                SecretKey secretKey = new SecretKeySpec(key, algorithm);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                cipherInputStream = new CipherInputStream(fileInputStream, cipher);
                objectInputStream = new ObjectInputStream(cipherInputStream);
            }
            return (Serializable) objectInputStream.readObject();
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (Exception e) {
                // nothing
            }
        }
    }

    public static Serializable read(String filePath, String desedeKey) {
        return read(new File(filePath), desedeKey);
    }

    public static boolean write(File file, String desedeKey, Serializable object) {
        FileOutputStream fileOutputStream = null;
        CipherOutputStream cipherOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        boolean errorOccured = false;
        try {
            fileOutputStream = new FileOutputStream(file);
            if (StringHelper.nullOrEmpty(desedeKey)) {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } else {
                byte[] key = desedeKey.getBytes(Charset.forName("US-ASCII"));
                String algorithm = "DESede";
                Cipher cipher = Cipher.getInstance(algorithm);
                SecretKey secretKey = new SecretKeySpec(key, algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
                objectOutputStream = new ObjectOutputStream(cipherOutputStream);
            }
            objectOutputStream.writeObject(object);
            return true;
        } catch (Exception e) {
            errorOccured = true;
            return false;
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (file != null && errorOccured) {
                    file.delete();
                }
            } catch (Exception e) {
                // nothing
            }
        }
    }

    public static boolean write(String filePath, String desedeKey, Serializable object) {
        return write(new File(filePath), desedeKey, object);
    }

}
