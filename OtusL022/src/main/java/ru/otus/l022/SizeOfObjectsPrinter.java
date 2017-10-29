package ru.otus.l022;


import java.util.function.Supplier;

public class SizeOfObjectsPrinter {
    private static final int COUNT = 1_000_000;

    public static <T> Object sizePrinter(Supplier<T> supplier) {
        Runtime runtime = Runtime.getRuntime();
        Object[] array = new Object[COUNT];
        runtime.gc();

        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < COUNT; i++) {
            array[i] = supplier.get();
        }

        runtime.gc();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Размер объекта равен: " +
                (memoryAfter - memoryBefore) / COUNT +
                " байт");

        return array;
    }
}
