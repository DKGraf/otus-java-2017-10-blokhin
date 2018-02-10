package ru.otus.l14;

import java.util.Arrays;

public class SimpleSorter extends Thread {
    private int[] array;

    SimpleSorter(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        Arrays.sort(array);
    }
}