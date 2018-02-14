package ru.otus.l14;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Random;

class MTSorterTest {

    @org.junit.jupiter.api.Test
    void sort() throws InterruptedException {
        int arraySize = 10_000_000;
        int numberThreads = 4;

        int[] array = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        long startTime = System.currentTimeMillis();
        int[] sortedArray = MTSorter.sort(array, numberThreads);
        long finishTime = System.currentTimeMillis();
        System.out.println("Parallel sort time: " + (finishTime - startTime) + " ms");
        startTime = System.currentTimeMillis();
        Arrays.sort(array);
        finishTime = System.currentTimeMillis();
        System.out.println("Default sort time: " + (finishTime - startTime) + " ms");
        Assertions.assertArrayEquals(array, sortedArray);
    }
}