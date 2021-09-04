package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        int tempRow = row;

        for (int i = tempRow; i < data.length; i++) {
            if (data[i].length == 0) {
                row++;
                column = 0;
            } else {
                break;
            }
        }

        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int item = data[row][column++];

        if (row < data.length && column == data[row].length) {
            column = 0;
            row++;
        }

        return item;

    }
}