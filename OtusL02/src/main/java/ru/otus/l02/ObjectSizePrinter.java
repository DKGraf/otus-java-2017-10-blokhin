package ru.otus.l02;

import com.carrotsearch.sizeof.RamUsageEstimator;

public class ObjectSizePrinter {
    public static void getSize(Object o) {
        System.out.println(RamUsageEstimator.sizeOf(o));
    }
}