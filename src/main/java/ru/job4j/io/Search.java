package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Arguments are not defined.");
        }
        File newFile = new File(args[0]);
        if (!newFile.exists()) {
            throw new IllegalArgumentException(
                    String.format("The path %s specified in the first parameter does not exist.", args[0]));
        }
        if (!newFile.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("The path %s specified in the first parameter is not a directory", args[0]));
        }
        Path start = Path.of(args[0]);
        String currentPredicate = args[1];
        search(start, p -> p.toFile().getName().endsWith(currentPredicate)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
