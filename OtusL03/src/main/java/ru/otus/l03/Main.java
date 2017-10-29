package ru.otus.l03;

import ru.otus.l03.MyArrayList.MyArrayList;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = new MyArrayList<>();
        for (int i = 10; i > 0; i--) {
            list1.add(i);
        }
        List<Integer> list2 = new MyArrayList<>();
        for (int i = 90; i > 77; i--) {
            list2.add(i);
        }

        Collections.sort(list1);
        list1.forEach(System.out::println);

        Collections.addAll(list1, 20, 30, 40);
        list1.forEach(System.out::println);

        Collections.copy(list2, list1);
        list2.forEach(System.out::println);
    }
}