package ru.otus.l03.MyArrayList;

import java.util.Iterator;
import java.util.List;

public class MyIterator<E> implements Iterator<E> {
    private int current = 0;
    private List<E> list;

    MyIterator(List<E> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return current < list.size() - 1;
    }

    @Override
    public E next() {
        if (hasNext()) current++;
        return list.get(current);
    }
}
