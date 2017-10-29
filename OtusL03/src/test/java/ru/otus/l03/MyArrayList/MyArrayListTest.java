package ru.otus.l03.MyArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyArrayListTest {
    @Test
    void add1() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        int i = list.get(0);
        Assertions.assertEquals(1, i);
    }

    @Test
    void add2() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.add(5, 15);
        int i = list.get(5);
        Assertions.assertEquals(15, i);
    }

    @Test
    void size() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Assertions.assertEquals(10, list.size());
    }

    @Test
    void isEmpty() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Assertions.assertEquals(false, list.isEmpty());
        MyArrayList<Integer> list2 = new MyArrayList<>();
        Assertions.assertEquals(true, list2.isEmpty());
    }

    @Test
    void clear() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.clear();
        Assertions.assertEquals(true, list.isEmpty());
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void get() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        int i = list.get(5);
        Assertions.assertEquals(5, i);
    }

    @Test
    void set() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.set(5, 155);
        int i = list.get(5);
        Assertions.assertEquals(155, i);
    }

    @Test
    void contains() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        boolean b = list.contains(5);
        Assertions.assertEquals(true, b);
    }

    @Test
    void remove1() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(Integer.valueOf(5));
        int size = list.size();
        Assertions.assertEquals(9, size);
    }

    @Test
    void remove2() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(9);
        int size = list.size();
        Assertions.assertEquals(9, size);
    }

    @Test
    void indexOf() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        int i = list.indexOf(5);
        Assertions.assertEquals(5, i);
    }

    @Test
    void lastIndexOf() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(3);
        }
        list.add(8);
        list.add(9);
        int i = list.lastIndexOf(3);
        Assertions.assertEquals(4, i);
    }

    @Test
    void removeAll() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        MyArrayList<Integer> list2 = new MyArrayList<>();
        list2.add(3);
        list2.add(7);
        list2.add(8);
        list.removeAll(list2);
        int i = list.size();
        Assertions.assertEquals(7, i);
    }
}