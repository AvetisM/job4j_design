package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Key not found");
        } else {
            return values.get(key);
        }
    }

    private void parse(String[] args) {
        for (String parameter : args) {
            String[] argArray = parameter.split("=");
            if (argArray.length == 2) {
                String key = argArray[0];
                if (key.startsWith("-")) {
                    key = key.substring(1);
                }
                values.put(key, argArray[1]);
            } else {
                throw new IllegalArgumentException("Pair 'key' = 'value' undefined");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}