package site.iway.javahelpers;

import java.io.*;

public class FileSystemHelper {

    public static boolean copyFile(File src, File dst, boolean overwrite) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(src);
            if (dst.exists() && !overwrite) {
                throw new IOException("File dst already existed but can not be overwritten.");
            }
            outputStream = new FileOutputStream(dst);
            byte[] buffer = new byte[8 * 1024];
            int readCount;
            while ((readCount = inputStream.read(buffer)) > -1) {
                outputStream.write(buffer, 0, readCount);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                // nothing
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                // nothing
            }

        }
    }

    public static boolean copyFile(File src, File dst) {
        return copyFile(src, dst, true);
    }

    public static boolean copyFile(String src, String dst, boolean overwrite) {
        return copyFile(new File(src), new File(dst), overwrite);
    }

    public static boolean copyFile(String src, String dst) {
        return copyFile(src, dst, true);
    }

    public static boolean createDirectory(File directory) {
        if (directory.exists())
            return directory.isDirectory();
        else
            return directory.mkdirs();
    }

    public static boolean createDirectory(String directory) {
        return createDirectory(new File(directory));
    }

    public static boolean deleteDirectory(File directory) {
        boolean allDeleted = true;
        if (directory.exists()) {
            File[] subFiles = directory.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    if (subFile.isDirectory()) {
                        allDeleted &= deleteDirectory(directory);
                    }
                    if (subFile.isFile()) {
                        allDeleted &= subFile.delete();
                    }
                }
            }
            allDeleted &= directory.delete();
        }
        return allDeleted;
    }

    public static boolean deleteDirectory(String directory) {
        return deleteDirectory(new File(directory));
    }

    public static boolean cleanDirectory(File directory) {
        return deleteDirectory(directory) && createDirectory(directory);
    }

    public static boolean cleanDirectory(String directory) {
        return cleanDirectory(new File(directory));
    }

}
