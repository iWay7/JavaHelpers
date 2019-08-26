package site.iway.javahelpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamReader {

    public static byte[] readAllBytes(InputStream stream, int bufferSize, int limit) throws IOException {
        AutoExpandByteArray autoExpandByteArray = new AutoExpandByteArray(bufferSize);
        byte[] buffer = new byte[bufferSize];
        int totalRead = 0;
        while (true) {
            int readCount = stream.read(buffer);
            if (readCount > -1) {
                totalRead += readCount;
                if (limit > 0 && totalRead > limit) {
                    throw new IOException("Content length exceeding limit.");
                }
                autoExpandByteArray.add(buffer, 0, readCount);
            } else {
                break;
            }
        }
        return autoExpandByteArray.getOutArray();
    }

    public static byte[] readAllBytes(InputStream stream, int bufferSize) throws IOException {
        return readAllBytes(stream, bufferSize, 0);
    }

    public static byte[] readAllBytes(InputStream stream) throws IOException {
        return readAllBytes(stream, 8 * 1024);
    }

    public static byte[] readBytesByCount(InputStream stream, int count) throws IOException {
        byte[] result = new byte[count];
        int offset = 0;
        while (offset != count) {
            offset += stream.read(result, offset, count - offset);
        }
        return result;
    }

    public static void readTo(InputStream inputStream, OutputStream outputStream) throws IOException {
        int n;
        while ((n = inputStream.read()) > -1) {
            outputStream.write(n);
        }
    }

}
