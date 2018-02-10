package ru.otus.l14;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Random;

class MTSorterTest {

    @org.junit.jupiter.api.Test
    void sort() throws InterruptedException {
        int size = 1000;
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        int[] sortedArray = MTSorter.sort(array);
        Arrays.sort(array);

        Assertions.assertArrayEquals(array, sortedArray);
    }
}