package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(5);

    @Override
    public boolean add(T value) {
        boolean rls = false;
        if (!set.contains(value)) {
            set.add(value);
            rls = true;
        }
        return rls;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean contains(T value) {
        return set.contains(value);
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
