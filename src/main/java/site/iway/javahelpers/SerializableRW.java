package site.iway.javahelpers;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class SerializableRW {

    public static Serializable read(File file, String desedeKey) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            if (StringHelper.nullOrEmpty(desedeKey)) {
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
                return (Serializable) objectInputStream.readObject();
            } else {
                byte[] key = desedeKey.getBytes(StandardCharsets.US_ASCII);
                String algorithm = "DESede";
                Cipher cipher = Cipher.getInstance(algorithm);
                SecretKey secretKey = new SecretKeySpec(key, algorithm);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                CipherInputStream cipherInputStream = new CipherInputStream(bufferedInputStream, cipher);
                ObjectInputStream objectInputStream = new ObjectInputStream(cipherInputStream);
                return (Serializable) objectInputStream.readObject();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Serializable read(String filePath, String desedeKey) {
        return read(new File(filePath), desedeKey);
    }

    public static boolean write(File file, String desedeKey, Serializable object) {
        boolean errorOccured = false;
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            if (StringHelper.nullOrEmpty(desedeKey)) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
                objectOutputStream.writeObject(object);
            } else {
                byte[] key = desedeKey.getBytes(StandardCharsets.US_ASCII);
                String algorithm = "DESede";
                Cipher cipher = Cipher.getInstance(algorithm);
                SecretKey secretKey = new SecretKeySpec(key, algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                CipherOutputStream cipherOutputStream = new CipherOutputStream(bufferedOutputStream, cipher);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(cipherOutputStream);
                objectOutputStream.writeObject(object);
            }
            return true;
        } catch (Exception e) {
            errorOccured = true;
            return false;
        } finally {
            try {
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
