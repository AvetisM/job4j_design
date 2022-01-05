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

    boolean checkParameters(String[] args) {
        boolean rls = true;
        for (String parameter : args) {
            if (parameter.startsWith("-") && parameter.contains("=")) {
                continue;
            }
            rls = false;
            break;
        }
        return rls;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Parameters are null. Usage java -jar pack.jar 'parameters'.");
        }
        boolean matchPattern = checkParameters(args);
        if (!matchPattern) {
            throw new IllegalArgumentException("One or more parameters do not match the pattern.");
        }
        for (String parameter : args) {
            String[] argArray = parameter.split("=");
            if (argArray.length != 2) {
                throw new IllegalArgumentException("Pair 'key' = 'value' undefined");
            }
            values.put(argArray[0].substring(1), argArray[1]);
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