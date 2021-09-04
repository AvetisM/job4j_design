package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        if (data[index] % 2 != 0 && index + 1 < data.length) {
            index++;
        }

        return index < data.length && data[index] % 2 == 0;

    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return data[index++];

    }

    @Override
    public void remove() {

        throw new UnsupportedOperationException();

    }
}