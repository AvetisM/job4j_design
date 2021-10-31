package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;

    private int size;

    private int modCount;

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        System.arraycopy(container, index,
                container, index + 1,
                size - index);

        container[index] = newValue;
        size++;
        return container[index];
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

        };
    }

    private T[] grow() {
        container = Arrays.copyOf(container, size * 2);
        return container;
    }
}
