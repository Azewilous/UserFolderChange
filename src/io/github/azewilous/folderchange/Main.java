package io.github.azewilous.folderchange;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        try {
            MainInterface selection = new MainInterface();
            selection.editUIManager();
            selection.createFrame();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
