package site.iway.javahelpers;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.zip.CRC32;

public class SecurityHelper {

    public static byte[] md5(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return messageDigest.digest(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] sha1(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            return messageDigest.digest(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static long crc32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    public static byte[] tripleDESEncrypt(byte[] data, byte[] key) {
        try {
            String algorithm = "DESede";
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] tripleDESDecrypt(byte[] data, byte[] key) {
        try {
            String algorithm = "DESede";
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] rsaEncryptPublicKey(byte[] data, byte[] publicKey) {
        try {
            String algorithm = "RSA";
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] rsaDecryptPrivateKey(byte[] data, byte[] privateKey) {
        try {
            String algorithm = "RSA";
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(privateKey);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] rsaEncryptPrivateKey(byte[] data, byte[] privateKey) {
        try {
            String algorithm = "RSA";
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(privateKey);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] rsaDecryptPublicKey(byte[] data, byte[] publicKey) {
        try {
            String algorithm = "RSA";
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePrivate(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

}
