package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {

    private static String getArgument(ArgsName argsName, String parameterName) {
        String parameter = argsName.get(parameterName);
        if (parameter == null) {
            throw new IllegalArgumentException(
                    String.format("The parameter %s is not found", parameterName));
        }
        return parameter;
    }

    public static void printResult(List<String> data) throws Exception {
        data.forEach(System.out::println);
    }

    public static void saveResult(List<String> data, String file) throws Exception {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            data.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        List<Integer> filterValues = new ArrayList<>();
        List<String> data = new ArrayList<>();
        String path = getArgument(argsName, "path");
        String delimiter = getArgument(argsName, "delimiter");
        String out = getArgument(argsName, "out");
        String filter = getArgument(argsName, "filter");

        try (var scanner = new Scanner(new File(path))) {
            if (scanner.hasNext()) {
                String[] values = scanner.nextLine().split(delimiter);
                for (int i = 0; i < values.length; i++) {
                    if (filter.contains(values[i])) {
                        filterValues.add(i);
                    }
                }
            }
        }
        try (var scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split(delimiter);
                StringJoiner tempLine = new StringJoiner(";");
                for (int i = 0; i < values.length; i++) {
                    if (filterValues.contains(i)) {
                        tempLine.add(values[i]);
                    }
                }
                data.add(tempLine.toString());
            }
        }
        if ("stdout".equals(out)) {
            printResult(data);
        } else {
            saveResult(data, out);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments are not defined.");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
