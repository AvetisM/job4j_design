package ru.job4j.filesearch;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileSearch {

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments are not defined.");
        }
        ArgsName argsName = ArgsName.of(args);

        FileSearch fileSearch = new FileSearch();

        String directory = fileSearch.checkParameter(argsName, "d");
        String condition = fileSearch.checkParameter(argsName, "n");
        String conditionType = fileSearch.checkParameter(argsName, "t");
        String logPath = fileSearch.checkParameter(argsName, "o");

        fileSearch.checkDirectory(directory);
        List<Path> sources = fileSearch.getFiles(directory, condition, conditionType);
        fileSearch.saveResult(sources, logPath, condition, conditionType);

    }

    private List<Path> getFiles(String directory, String condition, String conditionType) throws IOException {

        List<Path> sources = new ArrayList<>();

        switch (conditionType) {
            case "mask":
                String mask = condition
                        .replace(".", "\\.")
                        .replace("*", "")
                        .replace("?", ".");
                Pattern namePattern = Pattern.compile(mask);
                sources = Search.search(Path.of(directory), p -> namePattern.matcher(p.toFile().getName()).find());
                break;
            case "name":
                sources = Search.search(Path.of(directory), p -> condition.equals(p.toFile().getName()));
                break;
            case "regex":
                Pattern pattern = Pattern.compile(condition);
                sources = Search.search(Path.of(directory), p -> pattern.matcher(p.toFile().getName()).find());
                break;
            default:
                break;
        }

        return sources;
    }

    private void saveResult(List<Path> sources, String logPath, String condition, String conditionType) {

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(logPath)))) {

            out.printf("Критерии поиска: тип - %s; значение - %s", conditionType, condition
                    + System.lineSeparator());

            if (sources.size() == 0) {
                out.print("Файлы не найдены");
            } else {
                out.print("Список найденных файлов:"
                        + System.lineSeparator());
                sources.forEach(out::println);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String checkParameter(ArgsName argsName, String parameterName) {
        String parameter = argsName.get(parameterName);
        if (parameter == null) {
            throw new IllegalArgumentException(
                    String.format("The parameter %s is not found", parameterName));
        }
        return parameter;
    }

    private void checkDirectory(String directory) {
        File fileDirectory = new File(directory);
        if (!fileDirectory.exists()) {
            throw new IllegalArgumentException(
                    String.format("The path %s specified in the parameter 'directory' does not exist.", directory));
        }
        if (!fileDirectory.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("The path %s specified in the parameter 'directory' is not a directory", directory));
        }
    }
}
