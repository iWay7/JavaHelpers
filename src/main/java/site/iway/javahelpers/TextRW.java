package site.iway.javahelpers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TextRW {

    private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static void setDefaultCharset(Charset defaultCharset) {
        if (defaultCharset == null)
            throw new NullPointerException("Param defaultCharset can not be null.");
        DEFAULT_CHARSET = defaultCharset;
    }

    public static String readAllText(InputStream inputStream, Charset charset) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int readCount;
        while ((readCount = bufferedReader.read(buffer)) > -1) {
            stringBuilder.append(buffer, 0, readCount);
        }
        return stringBuilder.toString();
    }

    public static String readAllText(InputStream inputStream) throws IOException {
        return readAllText(inputStream, DEFAULT_CHARSET);
    }

    public static String readAllText(File file, Charset charset) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            return readAllText(fileInputStream, charset);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    // nothing
                }
            }
        }
    }

    public static String readAllText(File file) throws IOException {
        return readAllText(file, DEFAULT_CHARSET);
    }

    public static void writeAllText(OutputStream outputStream, Charset charset, String content) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
        outputStreamWriter.write(content);
        outputStreamWriter.flush();
    }

    public static void writeAllText(OutputStream outputStream, String content) throws IOException {
        writeAllText(outputStream, DEFAULT_CHARSET, content);
    }

    public static void writeAllText(File file, Charset charset, String content) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            writeAllText(fileOutputStream, charset, content);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    // nothing
                }
            }
        }
    }

    public static void writeAllText(File file, String content) throws IOException {
        writeAllText(file, DEFAULT_CHARSET, content);
    }

    public static List<String> readAllLines(InputStream inputStream, Charset charset) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ArrayList<String> lines = new ArrayList<>();
        String tempString;
        while ((tempString = bufferedReader.readLine()) != null) {
            lines.add(tempString);
        }
        return lines;
    }

    public static List<String> readAllLines(InputStream inputStream) throws IOException {
        return readAllLines(inputStream, DEFAULT_CHARSET);
    }

    public static List<String> readAllLines(File file, Charset charset) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            return readAllLines(fileInputStream, charset);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    // nothing
                }
            }
        }
    }

    public static List<String> readAllLines(File file) throws IOException {
        return readAllLines(file, DEFAULT_CHARSET);
    }

    public static void writeAllLines(OutputStream outputStream, Charset charset, List<String> content) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
        for (String line : content) {
            outputStreamWriter.write(line);
            outputStreamWriter.write('\n');
        }
        outputStreamWriter.flush();
    }

    public static void writeAllLines(OutputStream outputStream, List<String> content) throws IOException {
        writeAllLines(outputStream, DEFAULT_CHARSET, content);
    }

    public static void writeAllLines(File file, Charset charset, List<String> content) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            writeAllLines(fileOutputStream, charset, content);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    // nothing
                }
            }
        }
    }

    public static void writeAllLines(File file, List<String> content) throws IOException {
        writeAllLines(file, DEFAULT_CHARSET, content);
    }

}
