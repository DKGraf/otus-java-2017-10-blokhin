package ru.otus.l01;

/**
 * Данная программа предназначена для нахождения суммы элементов
 * пересечения двух множеств с использованием Google Guava.
 * Каждое из двух множеств содержит 1000 элементов, созданных
 * при помощи Random, с верхней границей в 50000.
 * Особой смысловой нагрузки данное действие не несет.
 */

public class Main {
    public static void main(String[] args) {
        new SumOfIntersection().run();
    }
}