package org.yanig;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.MessageFormat;
import java.util.Deque;
import java.util.LinkedList;

import static java.nio.file.FileVisitResult.CONTINUE;

public class GalleryVisitor extends SimpleFileVisitor<Path> {

    private final Path basePath;
    private final Deque<Folder> foldersStack = new LinkedList<>();
    private Folder currFolder;
    private final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:(?i).*\\.jpg$");

    public GalleryVisitor(Path basePath) {
        this.basePath = basePath;
    }

    public Folder getCurrFolder() {
        return currFolder;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("Pre folder " + dir.toAbsolutePath());
        Path relativePath = basePath.relativize(dir);
        Folder newFolder = new Folder(relativePath);
        if (currFolder != null) {
            currFolder.entries.add(newFolder);
            foldersStack.push(currFolder);
        } else {
            foldersStack.push(newFolder);
        }
        currFolder = newFolder;
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(MessageFormat.format("{0} is a regular file with size {1}", file, attrs.size()));
        if (isFileSupported(file)) {
            Path relativePath = basePath.relativize(file);
            currFolder.entries.add(new ImageFile(relativePath));
        } else {
            System.out.println("Skipping unsupported file");
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException ioException) {
        System.out.println("Folder" + path + " visited.");
        currFolder = foldersStack.pop();
        return CONTINUE;
    }

    private boolean isFileSupported(Path file) {
        return matcher.matches(file);
    }
}
