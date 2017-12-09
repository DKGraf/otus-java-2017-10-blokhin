package ru.otus.l08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Объект для проведения бесчеловечных опытов по преобразованию.
 */

public class TestObject {
    private int i1 = 1;
    private String s1 = "String";
    private int[] arrayOfInt = new int[] {1, 2, 3, 4, 5};
    private String[] arrayOfString = new String[] {"aaa", "bbb", "ccc"};
    private ArrayList<Integer> list;
    private LinkedList<Integer> linList;
    private HashMap<Integer, String> map;

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

    public HashMap<Integer, String> getMap() {
        return map;
    }

    public LinkedList<Integer> getLinList() {
        return linList;
    }

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

    }
}
