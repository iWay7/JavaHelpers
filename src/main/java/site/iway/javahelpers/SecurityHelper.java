package site.iway.javahelpers;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.zip.CRC32;

/**
 * @author iWay
 */
public class SecurityHelper {

    private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static void setDefaultCharset(Charset defaultCharset) {
        if (defaultCharset == null)
            throw new NullPointerException("Param defaultCharset can not be null.");
        DEFAULT_CHARSET = defaultCharset;
    }

    public static byte[] md5(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return messageDigest.digest(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        byte[] data = content.getBytes(charset);
        byte[] md5 = md5(data);
        return hexEncode(md5);
    }

    public static String md5(String content) {
        return md5(content, DEFAULT_CHARSET);
    }

    public static byte[] sha1(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            return messageDigest.digest(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static String sha1(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        byte[] data = content.getBytes(charset);
        byte[] sha1 = sha1(data);
        return hexEncode(sha1);
    }

    public static String sha1(String content) {
        return sha1(content, DEFAULT_CHARSET);
    }

    public static long crc32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    public static String crc32(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        byte[] data = content.getBytes(charset);
        long crc32 = crc32(data);
        StringBuilder builder = new StringBuilder(Long.toHexString(crc32));
        while (builder.length() < 8) {
            builder.insert(0, '0');
        }
        return builder.toString();
    }

    public static String crc32(String content) {
        return crc32(content, DEFAULT_CHARSET);
    }

    public static String base64Encode(byte[] bytes) {
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    public static byte[] base64Decode(String string) {
        Decoder decoder = Base64.getDecoder();
        return decoder.decode(string);
    }

    public static String urlEncode(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        try {
            return URLEncoder.encode(content, charset.name());
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String urlEncode(String content) {
        return urlEncode(content, DEFAULT_CHARSET);
    }

    public static String urlDecode(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        try {
            return URLDecoder.decode(content, charset.name());
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String urlDecode(String content) {
        return urlDecode(content, DEFAULT_CHARSET);
    }

    private static final char[] HEX_CHARS_LOWER_CASE = "0123456789abcdef".toCharArray();
    private static final char[] HEX_CHARS_UPPER_CASE = "0123456789ABCDEF".toCharArray();

    public static String hexEncode(byte[] data, boolean lowerCase) {
        if (data == null) {
            return null;
        }
        char[] chars = new char[data.length * 2];
        if (lowerCase) {
            for (int i = 0; i < data.length; i++) {
                chars[i * 2] = HEX_CHARS_LOWER_CASE[(data[i] & 0xF0) >> 4];
                chars[i * 2 + 1] = HEX_CHARS_LOWER_CASE[data[i] & 0x0F];
            }
        } else {
            for (int i = 0; i < data.length; i++) {
                chars[i * 2] = HEX_CHARS_UPPER_CASE[(data[i] & 0xF0) >> 4];
                chars[i * 2 + 1] = HEX_CHARS_UPPER_CASE[data[i] & 0x0F];
            }
        }
        return new String(chars);
    }

    public static String hexEncode(byte[] data) {
        return hexEncode(data, true);
    }

    public static String hexEncodeFromString(String content, Charset charset) {
        return hexEncode(content.getBytes(charset), true);
    }

    public static String hexEncodeFromString(String content) {
        return hexEncodeFromString(content, DEFAULT_CHARSET);
    }

    private static int hexDecode(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        } else if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        } else {
            throw new RuntimeException("Invalid hex char.");
        }
    }

    public static byte[] hexDecode(String hex) {
        if (hex == null) {
            return null;
        }
        int stringLength = hex.length();
        if (stringLength % 2 != 0) {
            throw new RuntimeException("Invalid hex string.");
        }
        int length = hex.length() / 2;
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            int left = hexDecode(hex.charAt(i)) << 4;
            int right = hexDecode(hex.charAt(i + 1));
            data[i] = (byte) (left | right);
        }
        return data;
    }

    public static String hexDecodeToString(String hex, Charset charset) {
        if (hex == null) {
            return null;
        }
        return new String(hexDecode(hex), charset);
    }

    public static String hexDecodeToString(String hex) {
        return hexDecodeToString(hex, DEFAULT_CHARSET);
    }

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static byte[] aesEncrypt(byte[] data, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] aesDecrypt(byte[] data, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    private static final String TRIPLE_DES_ALGORITHM = "DESede";
    private static final String TRIPLE_DES_TRANSFORMATION = "DESede/ECB/PKCS5Padding";

    public static byte[] tripleDESEncrypt(byte[] data, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, TRIPLE_DES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] tripleDESDecrypt(byte[] data, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, TRIPLE_DES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    private static final String RSA_PUBLIC_KEY_START_TAG = "-----BEGIN PUBLIC KEY-----";
    private static final String RSA_PUBLIC_KEY_END_TAG = "-----END PUBLIC KEY-----";
    private static final String RSA_PRIVATE_KEY_START_TAG = "-----BEGIN PRIVATE KEY-----";
    private static final String RSA_PRIVATE_KEY_END_TAG = "-----END PRIVATE KEY-----";

    public static final int KEY_TYPE_X509 = 0;
    public static final int KEY_TYPE_PKCS8 = 1;

    private static KeySpec getKeySpec(byte[] key, int keyType) {
        switch (keyType) {
            case KEY_TYPE_X509:
                return new X509EncodedKeySpec(key);
            case KEY_TYPE_PKCS8:
                return new PKCS8EncodedKeySpec(key);
            default:
                return null;
        }
    }

    private static byte[] decodePublicKeyData(String publicKey) {
        publicKey = publicKey.replace("\n", "");
        publicKey = StringHelper.extract(publicKey, RSA_PUBLIC_KEY_START_TAG, RSA_PUBLIC_KEY_END_TAG);
        if (publicKey == null) {
            return null;
        }
        Decoder decoder = Base64.getDecoder();
        return decoder.decode(publicKey);
    }

    private static byte[] decodePrivateKeyData(String privateKey) {
        privateKey = privateKey.replace("\n", "");
        privateKey = StringHelper.extract(privateKey, RSA_PRIVATE_KEY_START_TAG, RSA_PRIVATE_KEY_END_TAG);
        if (privateKey == null) {
            return null;
        }
        Decoder decoder = Base64.getDecoder();
        return decoder.decode(privateKey);
    }

    public static byte[] rsaEncryptPublicKey(byte[] data, byte[] publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            KeySpec keySpec = new X509EncodedKeySpec(publicKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
            BigInteger modulus = rsaPublicKey.getModulus();
            int keyLength = modulus.bitLength();
            int dataBlockSize = keyLength / 8 - 11;
            for (int offset = 0; offset < data.length; offset += dataBlockSize) {
                int count = dataBlockSize;
                if (count + offset > data.length) {
                    count = data.length - offset;
                }
                byte[] dstPart = cipher.doFinal(data, offset, count);
                dstStream.write(dstPart);
            }
            return dstStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] rsaEncryptPublicKey(byte[] data, String publicKey) {
        return rsaEncryptPublicKey(data, decodePublicKeyData(publicKey));
    }

    public static byte[] rsaDecryptPrivateKey(byte[] data, byte[] privateKey, int keyType) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            KeySpec keySpec = getKeySpec(privateKey, keyType);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
            BigInteger modulus = rsaPrivateKey.getModulus();
            int keyLength = modulus.bitLength();
            int dataBlockSize = keyLength / 8;
            for (int offset = 0; offset < data.length; offset += dataBlockSize) {
                int count = dataBlockSize;
                if (count + offset > data.length) {
                    count = data.length - offset;
                }
                byte[] dstPart = cipher.doFinal(data, offset, count);
                dstStream.write(dstPart);
            }
            return dstStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] rsaDecryptPrivateKey(byte[] data, String privateKey, int keyType) {
        return rsaDecryptPrivateKey(data, decodePrivateKeyData(privateKey), keyType);
    }

    public static byte[] rsaEncryptPrivateKey(byte[] data, byte[] privateKey, int keyType) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            KeySpec keySpec = getKeySpec(privateKey, keyType);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
            BigInteger modulus = rsaPrivateKey.getModulus();
            int keyLength = modulus.bitLength();
            int dataBlockSize = keyLength / 8 - 11;
            for (int offset = 0; offset < data.length; offset += dataBlockSize) {
                int count = dataBlockSize;
                if (count + offset > data.length) {
                    count = data.length - offset;
                }
                byte[] dstPart = cipher.doFinal(data, offset, count);
                dstStream.write(dstPart);
            }
            return dstStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] rsaEncryptPrivateKey(byte[] data, String privateKey, int keyType) {
        return rsaEncryptPrivateKey(data, decodePrivateKeyData(privateKey), keyType);
    }

    public static byte[] rsaDecryptPublicKey(byte[] data, byte[] publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            KeySpec keySpec = new X509EncodedKeySpec(publicKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
            BigInteger modulus = rsaPublicKey.getModulus();
            int keyLength = modulus.bitLength();
            int dataBlockSize = keyLength / 8;
            for (int offset = 0; offset < data.length; offset += dataBlockSize) {
                int count = dataBlockSize;
                if (count + offset > data.length) {
                    count = data.length - offset;
                }
                byte[] dstPart = cipher.doFinal(data, offset, count);
                dstStream.write(dstPart);
            }
            return dstStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] rsaDecryptPublicKey(byte[] data, String publicKey) {
        return rsaDecryptPublicKey(data, decodePublicKeyData(publicKey));
    }

}
