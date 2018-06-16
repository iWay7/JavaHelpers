package site.iway.javahelpers;

import java.io.IOException;
import java.io.OutputStream;

public class ProcessHelper {

    public static Process exec(String name, String input, String charset) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(name);
        if (input != null && !input.isEmpty()) {
            OutputStream outputStream = process.getOutputStream();
            byte[] inputData = input.getBytes(charset);
            outputStream.write(inputData);
            outputStream.flush();
            outputStream.close();
        }
        return process;
    }

    public static Process exec(String name) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(name);
    }

}
