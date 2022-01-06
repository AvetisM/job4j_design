package ru.job4j.io.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    @SuppressWarnings("checkstyle:RightCurly")
    public void run() {
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        String inUser;
        String stopUser = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Привет. Я чат-бот. Спроси меня о чем-нибудь.");
        do {
            inUser = scanner.nextLine();
            if (inUser.equals(STOP) || inUser.equals(OUT)) {
                stopUser = STOP;
            } else if (inUser.equals(CONTINUE)) {
                stopUser = "";
            }
            log.add(inUser);
            if (!stopUser.equals(STOP)) {
                int index = (int) (Math.random() * answers.size());
                String answer = answers.get(index);
                log.add(answer);
                System.out.println(answer);
            }
        } while (!inUser.equals(OUT));
        saveLog(log);
    }

    private List<String> readPhrases() {
        UsageEncoding encoding = new UsageEncoding();
        String strings = encoding.readFile(botAnswers);
        return List.of(strings.split(";"));
    }

    private void saveLog(List<String> log) {
        UsageEncoding encoding = new UsageEncoding();
        encoding.writeDataInFile(path, log);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/log.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
