package ru.otus.l14;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Random;

class MTSorterTest {

    @org.junit.jupiter.api.Test
    void sort() throws InterruptedException {
        int arraySize = 10000;
        int numberThreads = 4;

        int[] array = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        int[] sortedArray = MTSorter.sort(array, numberThreads);
        Arrays.sort(array);

        Assertions.assertArrayEquals(array, sortedArray);
    }
}