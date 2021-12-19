package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Analizy {
    public static void unavailable(String source, String target) {
        String dateBegin = "";
        int status;
        Map<String, String> result = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] splitLines = line.split(" ");
                if (splitLines.length == 2) {
                    status = Integer.parseInt(splitLines[0].trim());
                    if (dateBegin.isEmpty() && status == 400 || status == 500) {
                        dateBegin = splitLines[1].trim();
                    } else if (!dateBegin.isEmpty() && status != 400 && status != 500) {
                        result.put(dateBegin, splitLines[1].trim());
                        dateBegin = "";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (Map.Entry<String, String> entry : result.entrySet()) {
                out.printf("%s; %s", entry.getKey(), entry.getValue()
                        + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/unavailable.csv", "./data/target.csv");
    }
}
