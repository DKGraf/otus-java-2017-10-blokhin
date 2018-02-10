package ru.otus.l14;

public class MTSorter {

    private MTSorter() {
    }

    public static int[] sort(int[] array) throws InterruptedException {
        int length = array.length;
        int threadLength = length / 4;
        int lastLength = length - threadLength * 3;

        int[] array1 = new int[threadLength];
        int[] array2 = new int[threadLength];
        int[] array3 = new int[threadLength];
        int[] array4 = new int[lastLength];

        System.arraycopy(array, 0, array1, 0, threadLength);
        System.arraycopy(array, threadLength, array2, 0, threadLength);
        System.arraycopy(array, threadLength * 2, array3, 0, threadLength);
        System.arraycopy(array, threadLength * 3, array4, 0, lastLength);

        SimpleSorter ss1 = new SimpleSorter(array1);
        SimpleSorter ss2 = new SimpleSorter(array2);
        SimpleSorter ss3 = new SimpleSorter(array3);
        SimpleSorter ss4 = new SimpleSorter(array4);
        ss1.start();
        ss2.start();
        ss3.start();
        ss4.start();
        ss1.join();
        ss2.join();
        ss3.join();
        ss4.join();

        return merge(merge(array1, array2), merge(array3, array4));
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
