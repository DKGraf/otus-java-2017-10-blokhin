package ru.otus.l08.victims;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Объект для проведения бесчеловечных опытов по преобразованию.
 */

public class TestObject {
    private int i1 = 1;
    private String s1 = "String";
    private int[] arrayOfInt = new int[]{1, 2, 3, 4, 5};
    private String[] arrayOfString = new String[]{"aaa", "bbb", "ccc"};
    private ArrayList<Integer> list;
    private LinkedList<Integer> linList;
    private HashMap<Integer, String> map;
    private HashSet<Integer> set;
    private TestClass testClass;

    public TestObject() {
        list = new ArrayList<>();
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);

        linList = new LinkedList<>();
        linList.add(20);
        linList.add(21);
        linList.add(22);
        linList.add(23);

        map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");

        set = new HashSet<>();
        set.add(100);
        set.add(101);
        set.add(102);
        set.add(103);

        testClass = new TestClass();
    }
}
