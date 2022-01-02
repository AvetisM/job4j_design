package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not defined.");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("Second parameter not defined.");
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
