package site.iway.javahelpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class FolderScanner extends Thread {

    public FolderScanner() {
        mExtensions = new ArrayList<>();
        mFolders = new ArrayList<>();
    }

    private ArrayList<String> mExtensions;

    public void addExtension(String extension) {
        mExtensions.add(extension);
    }

    public void addExtension(String[] extensions) {
        for (String extension : extensions)
            mExtensions.add(extension);
    }

    public void addExtension(List<String> extensions) {
        for (String extension : extensions)
            mExtensions.add(extension);
    }

    private ArrayList<File> mFolders;

    public void addFolder(File folder) {
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        } else {
            for (int i = 0; i < mFolders.size(); i++) {
                File file = mFolders.get(i);
                String filePath = file.getAbsolutePath();
                String folderPath = folder.getAbsolutePath();
                if (folderPath.startsWith(filePath)) {
                    return;
                }
            }
            mFolders.add(folder);
        }
    }

    public void addFolder(File[] folders) {
        for (File folder : folders)
            mFolders.add(folder);
    }

    public void addFolder(List<File> folders) {
        for (File folder : folders)
            mFolders.add(folder);
    }

    protected void onStarted() {
    }

    protected abstract void onEnterFolder(File folder);

    protected abstract void onDetectFile(File file);

    protected abstract void onSkipFile(File file);

    protected void onCompleted() {
    }

    private boolean isFileValid(File file) {
        String name = file.getAbsolutePath();
        for (String ext : mExtensions) {
            if (name.endsWith(ext)) {
                return true;
            }
        }
        return mExtensions.isEmpty();
    }

    private void addFiles(File root) {
        if (mIsCanceled) {
            return;
        }
        onEnterFolder(root);
        File[] contents = root.listFiles();
        if (contents == null)
            return;
        for (File file : contents) {
            if (mIsCanceled) {
                break;
            }
            if (file.isDirectory()) {
                addFiles(file);
            } else {
                if (isFileValid(file)) {
                    onDetectFile(file);
                } else {
                    onSkipFile(file);
                }
            }
        }
    }

    @Override
    public void run() {
        for (File folder : mFolders) {
            if (mIsCanceled) {
                break;
            }
            onStarted();
            addFiles(folder);
            onCompleted();
        }
    }

    private boolean mIsCanceled;

    public void cancel() {
        mIsCanceled = true;
    }

}
