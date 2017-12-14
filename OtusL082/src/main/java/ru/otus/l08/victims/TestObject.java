package ru.otus.l08.victims;

import java.util.*;

/**
 * Объект для проведения бесчеловечных опытов по преобразованию.
 */

public class TestObject {
    private int i1;
    private String s1;
    private int[] arrayOfInt;
    private String[] arrayOfString;
    private ArrayList<Integer> list;
    private LinkedList<Integer> linList;
    private HashMap<Integer, String> map;
    private HashSet<Integer> set;
    private TestClass testClass;
    private String field;

    public TestObject() {
        i1 = 1;
        s1 = "String";
        arrayOfInt = new int[]{1, 2, 3, 4, 5};
        arrayOfString = new String[]{"aaa", "bbb", "ccc"};
        field = null;

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

    public int getI1() {
        return i1;
    }

    public String getS1() {
        return s1;
    }

    public int[] getArrayOfInt() {
        return arrayOfInt;
    }

    public String[] getArrayOfString() {
        return arrayOfString;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public LinkedList<Integer> getLinList() {
        return linList;
    }

    public HashMap<Integer, String> getMap() {
        return map;
    }

    public HashSet<Integer> getSet() {
        return set;
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public String getField() {
        return field;
    }
}
