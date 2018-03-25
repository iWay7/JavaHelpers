package site.iway.javahelpers;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TextRW {

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
        return readAllText(inputStream, Charset.defaultCharset());
    }

    public static void writeAllText(OutputStream outputStream, Charset charset, String content) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
        outputStreamWriter.write(content);
        outputStreamWriter.flush();
    }

    public static void writeAllText(OutputStream outputStream, String content) throws IOException {
        writeAllText(outputStream, Charset.defaultCharset(), content);
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
        return readAllLines(inputStream, Charset.defaultCharset());
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
        writeAllLines(outputStream, Charset.defaultCharset(), content);
    }

}
