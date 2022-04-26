package org.yanig;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ThumbnailGeneratorVisitor implements CollectionVisitor {
    private final Path collectionPath;
    Path targetPath;
    boolean firstFolder = true;

    public ThumbnailGeneratorVisitor(Path collectionPath, Path targetPath) {
        this.collectionPath = collectionPath;
        this.targetPath = targetPath;
    }

    @Override
    public void preFolder(Folder folder) throws IOException {
        if (firstFolder) {
            Files.createDirectories(targetPath);
            firstFolder = false;
        }
        Files.createDirectories(targetPath.resolve(folder.path));
    }

    @Override
    public void postFolder(Folder folder) {
        System.out.printf("Done processing %s%n", folder.path);
    }

    @Override
    public void image(ImageFile imageFile) throws IOException {
        Path targetPath = this.targetPath.resolve(imageFile.path()).toAbsolutePath();
        Path sourcePath = collectionPath.resolve(imageFile.path()).toAbsolutePath();
        System.out.println("About to create a thumbnail " + targetPath);
        Thumbnails.of(sourcePath.toString())
                .size(300, 300)
                .toFile(targetPath.toString());
    }
}
