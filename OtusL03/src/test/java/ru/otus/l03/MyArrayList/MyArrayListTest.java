package ru.otus.l03.MyArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyArrayListTest {
    @org.junit.jupiter.api.Test
    void size() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Assertions.assertEquals(10, list.size());
    }

    @org.junit.jupiter.api.Test
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
    void toArray() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

    }

    @Test
    void toArray1() {
    }

    @Test
    void add1() {
    }

    @Test
    void add2() {
    }

    @Test
    void addAll() {
    }

    @Test
    void addAll1() {
    }

    @Test
    void remove() {
    }

    @Test
    void remove1() {
    }

    @Test
    void containsAll() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void retainAll() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void subList() {
    }

    @Test
    void iterator() {
    }

    @Test
    void listIterator() {
    }

    @Test
    void listIterator1() {
    }

    @org.junit.jupiter.api.Test
    void add() {

    }


}