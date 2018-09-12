package site.iway.javahelpers;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ObjectRW {

    public static <T> T read(String filePath, String desedeKey) {
        File file = null;
        FileInputStream fileInputStream = null;
        CipherInputStream cipherInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            file = new File(filePath);
            fileInputStream = new FileInputStream(file);
            if (StringHelper.isEmpty(desedeKey)) {
                objectInputStream = new ObjectInputStream(fileInputStream);
            } else {
                byte[] key = desedeKey.getBytes(StandardCharsets.US_ASCII);
                String algorithm = "DESede";
                Cipher cipher = Cipher.getInstance(algorithm);
                SecretKey secretKey = new SecretKeySpec(key, algorithm);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                cipherInputStream = new CipherInputStream(fileInputStream, cipher);
                objectInputStream = new ObjectInputStream(cipherInputStream);
            }
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

    public static boolean write(String filePath, String desedeKey, Object object) {
        File file = null;
        FileOutputStream fileOutputStream = null;
        CipherOutputStream cipherOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        boolean errorOccured = false;
        try {
            file = new File(filePath);
            fileOutputStream = new FileOutputStream(file);
            if (StringHelper.isEmpty(desedeKey)) {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } else {
                byte[] key = desedeKey.getBytes(StandardCharsets.US_ASCII);
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

}
