package ru.otus.l14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MTSorter {

    private MTSorter() {
    }

    public static int[] sort(int[] array, int threads) throws InterruptedException {
        List<int[]> listOfSubarrays = createListOfSubarrays(array, threads);
        List<Thread> listOfThreads = createListOfThreads(listOfSubarrays, threads);
        startThreads(listOfThreads);
        joinThreads(listOfThreads);
        return getResult(listOfSubarrays, threads);
    }

    private static List<int[]> createListOfSubarrays(int[] array, int threads) {
        int length = array.length;
        int threadLength = length / threads;
        int lastLength = length - threadLength * (threads - 1);

        final List<int[]> list = new ArrayList<>();
        for (int i = 0; i < threads - 1; i++) {
            list.add(new int[threadLength]);
        }
        list.add(new int[lastLength]);

        for (int i = 0; i < threads; i++) {
            System.arraycopy(array, (i * threadLength), list.get(i), 0, list.get(i).length);
        }

        return list;
    }

    private static List<Thread> createListOfThreads(List<int[]> listOfSubarrays, int threads) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            int finalI = i;
            list.add(new Thread(() -> Arrays.sort(listOfSubarrays.get(finalI))));
        }
        return list;
    }

    private static void startThreads(List<Thread> listOfThreads) {
        for (Thread t :
                listOfThreads) {
            t.start();
        }
    }

    private static void joinThreads(List<Thread> listOfThreads) throws InterruptedException {
        for (Thread t :
                listOfThreads) {
            t.join();
        }
    }

    private static int[] getResult(List<int[]> listOfSubarrays, int threads) {
        int[] result = new int[0];
        for (int i = 0; i < threads; i++) {
            result = merge(result, listOfSubarrays.get(i));
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int leftLength = left.length, rightLength = right.length;
        int l = 0, r = 0, len = leftLength + rightLength;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (r < rightLength && l < leftLength) {
                if (left[l] > right[r]) result[i] = right[r++];
                else result[i] = left[l++];
            } else if (r < rightLength) {
                result[i] = right[r++];
            } else {
                result[i] = left[l++];
            }
        }
        return result;
    }
}
