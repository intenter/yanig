package org.yanig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Folder implements FileObject {
    String path;
    List<FileObject> entries = new ArrayList<>();

    public Folder(String path) {
        this.path = path;
    }

    public void visit(CollectionVisitor visitor) throws IOException {
        visitor.preFolder(this);
        for (FileObject entry : entries) {
            if (entry instanceof ImageFile imageFile) {
                visitor.image(imageFile);
            } else if (entry instanceof Folder folder) {
                folder.visit(visitor);
            }
        }
        visitor.postFolder(this);
    }

    @Override
    public String toString() {
        return "Folder{" +
                "path='" + path + '\'' +
                '}';
    }
}
