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

        Node<E> currentNode = first;
        Node<E> newNode = new Node<>(currentNode, value, null);
        if (currentNode == null) {
            first = newNode;
        } else {
            currentNode.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
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
                E currentValue;
                if (currentNode == null) {
                    currentNode = first;
                    currentValue = (E) first.item;
                } else {
                    currentNode = currentNode.next;
                    currentValue = currentNode.item;
                }
                return currentValue;
            }
        };
    }
}