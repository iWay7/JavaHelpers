package site.iway.javahelpers;

import java.io.File;

public class FileSystemHelper {

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
