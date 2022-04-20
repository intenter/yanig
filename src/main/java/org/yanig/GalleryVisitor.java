package org.yanig;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Deque;
import java.util.LinkedList;

import static java.nio.file.FileVisitResult.CONTINUE;

public class GalleryVisitor extends SimpleFileVisitor<Path> {

    private Deque<Folder> foldersStack = new LinkedList<>();
    private Folder currFolder;

    public Folder getCurrFolder() {
        return currFolder;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("Pre folder " + dir.toAbsolutePath());
        Folder newFolder = new Folder(dir.toAbsolutePath().toString());
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
        System.out.println(file + " is a regular file with size "
                + attrs.size());
        currFolder.entries.add(new ImageFile(file.toString()));
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException ioException) {
        System.out.println("Folder" + path + " visited.");
        currFolder = foldersStack.pop();
        return CONTINUE;
    }
}
