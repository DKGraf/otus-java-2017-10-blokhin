package ru.otus.l01;

import com.google.common.collect.Sets;

import java.util.Random;
import java.util.Set;

class SumOfIntersection {
    private Set<Integer> intersectedSet;

    private void createSets() {
        Set<Integer> firstSet = Sets.newHashSet();
        Set<Integer> secondSet = Sets.newHashSet();
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            firstSet.add(random.nextInt(50000));
        }
        for (int i = 0; i < 1000; i++) {
            secondSet.add(random.nextInt(50000));
        }

        intersectedSet = Sets.intersection(firstSet, secondSet);
    }

    private int calculateSum() {
        int sum = 0;
        for (Integer i :
                intersectedSet) {
            sum += i;
        }
        return sum;
    }

    void run() {
        createSets();
        System.out.println("Сумма пересекающихся элементов двух множеств: " +
                calculateSum());
    }
}
