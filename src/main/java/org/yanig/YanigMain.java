package org.yanig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class YanigMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Yanig version 0.01");
        new YanigMain().generate();
    }

    private void generate() throws IOException {
        Folder everything = traverse("src/main/resources/collection_root");
        System.out.println("done");
    }

    private Folder traverse(String collection_root) throws IOException {
        Path path = Paths.get(collection_root);
        System.out.println("path = " + path.toAbsolutePath());
        GalleryVisitor visitor = new GalleryVisitor();
        Files.walkFileTree(path, visitor);
        return visitor.getCurrFolder();
    }
}
