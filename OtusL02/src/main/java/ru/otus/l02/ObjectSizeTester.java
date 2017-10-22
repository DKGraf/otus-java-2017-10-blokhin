package ru.otus.l02;

import static ru.otus.l02.ObjectSizePrinter.printObjectSize;

/**Пример вывода на экран размера некоторых объектов
 */

public class ObjectSizeTester {
    public static void main(String[] args) {
        printObjectSize(new Object());
        printObjectSize("");
        printObjectSize('C');
        printObjectSize(new int[0]);
        printObjectSize(new int[1]);
        printObjectSize(new int[2]);
        printObjectSize(new int[3]);
        printObjectSize(new int[4]);
        printObjectSize(new int[5]);
    }
}