package ru.otus.l05.tests;

import ru.otus.l05.annotations.Before;
import ru.otus.l05.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test
public class ValidTest {
    List<Integer> list = new ArrayList<>();

    @Before
    public void fill() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void test() throws Exception {
        if (list.size() != 4) {
            throw new Exception();
        }
        if (list.get(1) != 1) {
            throw new Exception();
        }
    }
}