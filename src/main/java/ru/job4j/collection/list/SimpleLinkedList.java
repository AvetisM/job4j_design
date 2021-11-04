package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node first;
    private int size = 0;

    private int modCount;

    @Override
    public void add(E value) {

        Node<E> f = first;
        Node<E> newNode = new Node<>(f, value, null);
        if (f == null) {
            first = newNode;
        } else {
            f.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int expectedModCount = modCount;
            int indexIt;
            Node<E> currentNode = null;

            @Override
            public boolean hasNext() {
                return indexIt < size;
            }

            @Override
            public E next() {

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                indexIt++;

                if (currentNode == null) {
                    currentNode = first;
                    return (E) first.item;
                } else {
                    currentNode = currentNode.next;
                    return currentNode.item;
                }
            }
        };
    }
}