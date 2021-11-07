package ru.job4j.set;

public interface Set<T> extends Iterable {
    boolean add(T value);

    boolean contains(T value);

    int size();
}
