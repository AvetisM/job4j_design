package ru.job4j.collection;

public interface List<T> extends Iterable {
    void add(T value);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();

    boolean contains(T value);
}
