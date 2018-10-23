package site.iway.javahelpers;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * Created by iWay on 2018/2/1.
 */
public class SSLHelper {

    private static TrustManager[] combine(TrustManager[]... arrays) {
        int totalLength = 0;
        for (TrustManager[] objects : arrays) {
            totalLength += objects.length;
        }
        TrustManager[] newArray = new TrustManager[totalLength];
        int pointer = 0;
        for (TrustManager[] objects : arrays) {
            System.arraycopy(objects, 0, newArray, pointer, objects.length);
            pointer += objects.length;
        }
        return newArray;
    }

    public static SSLContext generateSSLContext(String... certificateStrings) {
        try {
            TrustManager[] trustManagers = new TrustManager[0];
            for (String certificateString : certificateStrings) {
                byte[] sslCertificateData = certificateString.getBytes();
                ByteArrayInputStream sslCertificateStream = new ByteArrayInputStream(sslCertificateData);
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                Certificate certificate = certificateFactory.generateCertificate(sslCertificateStream);
                String keyStoreType = KeyStore.getDefaultType();
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", certificate);
                String trustManagerAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerAlgorithm);
                trustManagerFactory.init(keyStore);
                trustManagers = combine(trustManagers, trustManagerFactory.getTrustManagers());
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, null);
            return sslContext;
        } catch (Exception e) {
            return null;
        }
    }

    public static SSLSocketFactory generateSSLSocketFactory(String... certificateStrings) {
        SSLContext sslContext = generateSSLContext(certificateStrings);
        return sslContext == null ? null : sslContext.getSocketFactory();
    }

}
