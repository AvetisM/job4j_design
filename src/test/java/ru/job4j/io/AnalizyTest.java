package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.io.IOException;
import java.util.StringJoiner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenUnavailable2Periods() throws IOException {
        File source = folder.newFile("unavailable.csv");
        File target = folder.newFile("target.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner rls = new StringJoiner(" ");
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rls::add);
        }
        assertThat(rls.toString(), is("10:57:01; 10:59:01 11:01:02; 11:02:02"));
    }
}