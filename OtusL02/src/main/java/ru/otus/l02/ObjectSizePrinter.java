package ru.otus.l02;

import com.carrotsearch.sizeof.RamUsageEstimator;

/**Данный класс содержит метод, который выводит на экран
 * размер переданного ему объекта. Для этого используется
 * зависимоть на артифакт java-sizeof от com.carrotsearch
 */

public class ObjectSizePrinter {
    public static void printObjectSize(Object o) {
        String s = "Размер объекта: " +
                RamUsageEstimator.sizeOf(o) +
                " байт.";
        System.out.println(s);
    }
}