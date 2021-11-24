package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        float loadFactor = (float) count / table.length;
        if (loadFactor >= LOAD_FACTOR) {
            expand();
        }
        if (table[index] == null) {
            table[index] = new MapEntry(key, value);
            result = true;
            modCount++;
            count++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        capacity = table.length * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> keySet : table) {
            if (keySet != null) {
                K currentKey = keySet.key;
                int currentHash = hash(currentKey.hashCode());
                int index = indexFor(currentHash);
                newTable[index] = keySet;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] != null) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        if (get(key) != null) {
            int hash = hash(key.hashCode());
            int index = indexFor(hash);
            table[index] = null;
            modCount++;
            count--;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {

            MapEntry<K, V> next;
            int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count > 0 && index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                do {
                    next = table[index++];
                } while (next == null);
                return next.key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
