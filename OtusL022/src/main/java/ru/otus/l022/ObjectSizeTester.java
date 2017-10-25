package ru.otus.l022;

public class ObjectSizeTester {
    public static void main(String[] args) {
        new SizeOfObjectsPrinter(() -> new String(new char[0])).run();
    }
}