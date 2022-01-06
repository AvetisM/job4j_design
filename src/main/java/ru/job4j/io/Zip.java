package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        for (Path path : sources) {
            packSingleFile(path.toFile(), target);
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parameterExist(ArgsName argsName, String parameterName) {
        String parameter = argsName.get(parameterName);
        if (parameter == null) {
            throw new IllegalArgumentException(
                    String.format("The parameter %s is not found", parameterName));
        }
        return parameter;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Arguments are not defined.");
        }
        ArgsName argsName = ArgsName.of(args);
        String directory = parameterExist(argsName, "d");
        String exclude = parameterExist(argsName, "e");
        String output = parameterExist(argsName, "o");
        File fileDirectory = new File(directory);
        if (!fileDirectory.exists()) {
            throw new IllegalArgumentException(
                    String.format("The path %s specified in the parameter 'directory' does not exist.", directory));
        }
        if (!fileDirectory.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("The path %s specified in the parameter 'directory' is not a directory", directory));
        }
        File outputDirectory = new File(output);
        List<Path> sources = Search.search(Path.of(directory), p -> !p.toFile().getName().endsWith(exclude));
        packFiles(sources, outputDirectory);

    }
}
