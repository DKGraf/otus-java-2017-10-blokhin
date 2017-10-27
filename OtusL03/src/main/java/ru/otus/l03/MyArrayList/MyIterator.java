package ru.otus.l03.MyArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyIterator<E> implements Iterator<E>, ListIterator<E> {
    private int current = 0;
    private List<E> list;

    MyIterator(List<E> list) {
        this.list = list;
    }

    MyIterator(List<E> list, int current) {
        this.current = current;
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

    @Override
    public boolean hasPrevious() {
        return current > 0 && list.size() > 0;
    }

    @Override
    public E previous() {
        if (hasPrevious()) current--;
        return list.get(current);
    }

    @Override
    public int nextIndex() {
        if (hasNext()) return (current + 1);
        else return -1;
    }

    @Override
    public int previousIndex() {
        if (hasPrevious()) return (current - 1);
        else return -1;
    }

    @Override
    public void remove() {
        list.remove(current);
    }

    @Override
    public void set(E e) {
        list.set(current, e);
    }

    @Override
    public void add(E e) {
        list.set(current, e);
    }
}
