package org.yanig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class YanigMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Yanig version 0.01");
        YanigConfig config = new YanigConfig("src/main/resources/collection_root",
                "build/thumbnails", "build/static");
        new YanigMain().generate(config);
    }

    private void generate(YanigConfig config) throws IOException {
        System.out.println("Traversing collection");
        Folder collectionTree = traverse(config.collectionPath());
        System.out.println("Generating thumbnails");
        generateThumbnails(collectionTree, config);
        System.out.println("Generating static");
        generateStatic(collectionTree);
        System.out.println("done");
    }

    private void generateStatic(Folder collectionTree) {

    }

    private void generateThumbnails(Folder collectionTree, YanigConfig config) throws IOException {
        collectionTree.visit(new ThumbnailGeneratorVisitor(
                Paths.get(config.collectionPath()),
                Paths.get(config.thumbnailsPath())));
    }

    private Folder traverse(String collection_root) throws IOException {
        Path path = Paths.get(collection_root);
        System.out.println("path = " + path.toAbsolutePath());
        GalleryVisitor visitor = new GalleryVisitor(path);
        Files.walkFileTree(path, visitor);
        return visitor.getCurrFolder();
    }
}
