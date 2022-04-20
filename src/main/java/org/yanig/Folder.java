package org.yanig;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileObject {
    String path;
    List<FileObject> entries = new ArrayList<>();

    public Folder(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "path='" + path + '\'' +
                '}';
    }
}
