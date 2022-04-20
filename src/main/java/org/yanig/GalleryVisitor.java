package org.yanig;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class GalleryVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("Pre folder " + dir.toAbsolutePath());
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file + " is a regular file with size "
                + attrs.size());
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException ioException) {
        System.out.println(path + " visited.");
        return CONTINUE;
    }
}
