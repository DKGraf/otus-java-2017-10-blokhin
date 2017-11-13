package ru.otus.l05;

import ru.otus.l05.annotations.Before;

import java.util.ArrayList;
import java.util.List;

public class ForTest {
    List<Integer> list = new ArrayList<>();

    @Before
    public void fill() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
    }


}