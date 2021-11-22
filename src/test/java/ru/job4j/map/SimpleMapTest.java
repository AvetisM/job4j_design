package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

public class SimpleMapTest {

    @Test
    public void whenPutAndGet() {
        SimpleMap simpleMap = new SimpleMap<>();
        simpleMap.put("First", 1);
        Assert.assertEquals(1, simpleMap.get("First"));
        Assert.assertNull(simpleMap.get("Second"));
    }

    @Test
    public void whenRemove() {
        SimpleMap simpleMap = new SimpleMap<>();
        simpleMap.put("First", 1);
        Assert.assertTrue(simpleMap.remove("First"));
        Assert.assertFalse(simpleMap.remove("First"));
    }
}