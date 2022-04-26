package org.yanig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ThumbnailGeneratorVisitor implements CollectionVisitor {
    Path basePath;
    boolean firstFolder = true;

    public ThumbnailGeneratorVisitor(Path basePath) {
        this.basePath = basePath;
    }

    @Override
    public void preFolder(Folder folder) throws IOException {
        if (firstFolder) {
            Files.createDirectories(basePath);
            firstFolder = false;
        }
    }

    @Override
    public void postFolder(Folder folder) {
        System.out.printf("Done processing %s%n", folder.path);
    }

    @Override
    public void image(ImageFile imageFile) {
        System.out.println("About to create a thumbnail " + basePath.resolve(imageFile.path()).toAbsolutePath());
    }
}
