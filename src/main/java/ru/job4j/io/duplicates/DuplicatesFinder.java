package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        for (List<Path> duplicateList : duplicatesVisitor.duplicates.values()) {
            if (duplicateList.size() > 1) {
                duplicateList.forEach(System.out::println);
            }
        }
    }
}