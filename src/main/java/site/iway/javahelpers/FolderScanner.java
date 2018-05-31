package site.iway.javahelpers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FolderScanner extends Thread {

    public FolderScanner() {
        mExtensions = new ArrayList<>();
        mFolders = new ArrayList<>();
    }

    private ArrayList<String> mExtensions;

    public void addExtensions(String... extensions) {
        mExtensions.addAll(Arrays.asList(extensions));
    }

    public void addExtensions(List<String> extensions) {
        for (String extension : extensions)
            mExtensions.add(extension);
    }

    private ArrayList<File> mFolders;

    public void addFolders(File... folders) {
        mFolders.addAll(Arrays.asList(folders));
    }

    public void addFolder(List<File> folders) {
        mFolders.addAll(folders);
    }

    protected void onStarted(File folder) {
        // nothing
    }

    protected void onEnterFolder(File folder) {
        // nothing
    }

    protected void onDetectFile(File file) {
        // nothing
    }

    protected void onSkipFile(File file) {
        // nothing
    }

    protected void onCompleted(File folder) {
        // nothing
    }

    protected void onAllCompleted() {
        // nothing
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

    private void scan(File root) {
        if (mIsCanceled) {
            return;
        }
        onEnterFolder(root);
        File[] contents = root.listFiles();
        if (contents == null)
            return;
        for (File file : contents) {
            if (mIsCanceled) {
                return;
            }
            if (file.isDirectory()) {
                scan(file);
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
            onStarted(folder);
            scan(folder);
            onCompleted(folder);
        }
        onAllCompleted();
    }

    private boolean mIsCanceled;

    public void cancel() {
        mIsCanceled = true;
    }

}
