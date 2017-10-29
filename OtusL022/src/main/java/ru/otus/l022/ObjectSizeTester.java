package ru.otus.l022;

/**
 * -XX:-UseCompressedOops
 */

public class ObjectSizeTester {
    public static void main(String[] args) {
        SizeOfObjectsPrinter.sizePrinter(String::new);
        SizeOfObjectsPrinter.sizePrinter(Object::new);
    }
}