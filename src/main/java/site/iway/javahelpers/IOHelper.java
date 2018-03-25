package site.iway.javahelpers;

import java.io.File;

public class IOHelper {

    public static boolean deleteFile(String path) {
        return new File(path).delete();
    }

    public static boolean deleteDirectory(String path) {
        File file = new File(path);
        File[] subFiles = file.listFiles();
        boolean allDeleted = true;
        if (subFiles != null) {
            for (File subFile : subFiles) {
                if (subFile.isDirectory()) {
                    String absolutePath = subFile.getAbsolutePath();
                    allDeleted &= deleteDirectory(absolutePath);
                }
                if (subFile.isFile()) {
                    allDeleted &= subFile.delete();
                }
            }
        }
        allDeleted &= file.delete();
        return allDeleted;
    }

    public static boolean deleteFilesInDirectory(String path) {
        File[] subFiles = new File(path).listFiles();
        boolean allDeleted = true;
        if (subFiles != null) {
            for (File subFile : subFiles) {
                if (subFile.isFile()) {
                    allDeleted &= subFile.delete();
                }
            }
        }
        return allDeleted;
    }

    public static boolean deleteDirectoriesInDirectory(String path) {
        File[] subFiles = new File(path).listFiles();
        boolean allDeleted = true;
        if (subFiles != null) {
            for (File subFile : subFiles) {
                if (subFile.isDirectory()) {
                    allDeleted &= deleteDirectory(subFile.getAbsolutePath());
                }
            }
        }
        return allDeleted;
    }

    public static boolean emptyDirectory(String path) {
        return deleteFilesInDirectory(path) && deleteDirectoriesInDirectory(path);
    }

}
