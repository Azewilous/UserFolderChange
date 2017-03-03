package io.github.azewilous.folderchange;

import java.io.File;

/**
 * Created on 3/2/2017.
 */
public class FolderHandler {

    static boolean running = false;

    public static void handleFolderNameChange(String path, String name) {
        running = true;
        String oldPath = path;
        if (path.contains("\\")) {
            String separator = "\\";
            int index = path.lastIndexOf(separator);
            String folder = path.substring(index);
            path = path.replace(folder, "\\" + name);

            File file = new File(oldPath);
            if (file.exists()) {
                boolean result = file.renameTo(new File(path));
                if (result) {
                    System.out.println("Folder successfully renamed");
                } else {
                    System.out.println("Error... folder was not renamed.");
                }
            } else {
                System.out.println("The specified path does not exist");
            }

            System.out.println("Path changed from -> "  + oldPath + " to -> " + path);
        } else {
            path = name;
            System.out.println("Path changed from -> " + oldPath + " to -> " + path);
        }
        running = false;
    }

    public static boolean isRunning() {
        return running;
    }

}
