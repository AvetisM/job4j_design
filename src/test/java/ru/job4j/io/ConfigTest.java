package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname"), is("mkhitaryants"));
        assertThat(config.value("name"), is("avetis"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname"), is("mkhitaryants"));
        assertThat(config.value("name"), is("avetis"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPair() {
        String path = "./data/pair_wrong_pair.properties";
        Config config = new Config(path);
    }
}