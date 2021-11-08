package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(5);

    @Override
    public boolean add(T value) {
        boolean rls = false;
        if (!contains(value)) {
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
        boolean rls = false;
        for (Object currentValue : set) {
            if (Objects.equals(currentValue, value)) {
                rls = true;
                break;
            }
        }
        return rls;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
