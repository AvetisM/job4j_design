package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        modCount++;
        size++;
    }

    @Override
    public T set(int index, T newValue) {

        Objects.checkIndex(index, container.length);

        T oldValue = container[index];
        container[index] = newValue;

        modCount++;

        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);

        T removeElement = container[index];

        System.arraycopy(container, index + 1,
                container, index,
                size - index - 1);

        container[size - 1] = null;

        modCount++;
        size--;

        return removeElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T value) {
        boolean rls = false;
        Iterator<Integer> it = this.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next(), value)) {
                rls = true;
                break;
            }
        }
        return rls;
    }

    @Override
    public Iterator iterator() {

        return new Iterator<T>() {

            int expectedModCount = modCount;
            int indexIt;

            @Override
            public boolean hasNext() {
                return indexIt < size;
            }

            @Override
            public T next() {

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[indexIt++];
            }

        };
    }

    private T[] grow() {
        container = Arrays.copyOf(container, size * 2);
        return container;
    }
}
