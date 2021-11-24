package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class SimpleMapTest {

    SimpleMap simpleMap;

    @Before
    public void initData() {
        simpleMap = new SimpleMap<>();
        simpleMap.put("1", 1);
        simpleMap.put("2", 2);
        simpleMap.put("3", 3);
    }

    @Test
    public void whenPutAndGet() {
        simpleMap.put("4", 4);
        Assert.assertEquals(4, simpleMap.get("4"));
        Assert.assertNull(simpleMap.get("5"));
    }

    @Test
    public void whenRemove() {
        Assert.assertTrue(simpleMap.remove("1"));
        Assert.assertFalse(simpleMap.remove("1"));
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = simpleMap.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("1", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("2", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("3", iterator.next());
    }
}