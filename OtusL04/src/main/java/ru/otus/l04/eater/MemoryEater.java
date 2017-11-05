package ru.otus.l04.eater;

import java.util.ArrayList;
import java.util.List;

public class MemoryEater {
    private final int SIZE = 10_000_000;
    private List<Integer> list = new ArrayList<>();

    public void eatMemory() {
        long startTime = System.currentTimeMillis();
        long currentTime = 0;
        marker: while (true) {
            int[] array = new int[SIZE];
            for (int i = 0; i < array.length; i++) {
                array[i] = i + 1;
                if (i % 1_600 == 0) {
                    list.add(array[i]);
                }
                currentTime = System.currentTimeMillis() - startTime;
                if (currentTime > 305_000) break marker;
            }
        }
    }
}