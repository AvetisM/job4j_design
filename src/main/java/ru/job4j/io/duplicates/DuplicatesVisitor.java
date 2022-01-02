package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    public Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(attrs.size(), file.getFileName().toString());
        if (duplicates.containsKey(newFile)) {
            List<Path> value = duplicates.get(newFile);
            value.add(file.toAbsolutePath());
        } else {
            List<Path> value = new ArrayList<>();
            value.add(file.toAbsolutePath());
            duplicates.put(newFile, value);
        }
        return super.visitFile(file, attrs);
    }
}
