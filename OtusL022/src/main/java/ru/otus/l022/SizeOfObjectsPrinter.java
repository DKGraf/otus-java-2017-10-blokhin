package ru.otus.l022;


import java.util.function.Supplier;

public class SizeOfObjectsPrinter {
    private static final int COUNT = 10_000_000;
    Object o;
    final private Supplier<?> item;

    public SizeOfObjectsPrinter(Supplier<?> item) {
        this.item = item;
    }

    public void run() {
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        Object[] array = new Object[COUNT];
        for (int i = 0; i < COUNT; i++) {
            array[i] = item.get();
        }
        runtime.gc();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Размер объекта примерно равен: " +
                (memoryAfter - memoryBefore) / COUNT +
                " байт");
    }
}
