package ru.otus.l02;

import static ru.otus.l02.ObjectSizePrinter.getSize;

public class Main {
    public static void main(String[] args) {
        getSize(new Object());
        getSize("");
        getSize(new int[0]);
        getSize(new int[1]);
        getSize(new int[2]);
        getSize(new int[3]);
        getSize(new int[4]);
        getSize(new int[5]);
    }
}