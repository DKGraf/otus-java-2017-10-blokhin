package ru.otus.l08.writer.processors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Фабрика, статичный метод которой выбирает какой вид процессора создать
 */

public class ProcessorFactory {
    private ProcessorFactory() {
    }

    public static Processor getProcessor(Class aClass) {
        if (aClass.isArray()) {
            return new ArrayProcessor();
        } else if (aClass.isInstance(new HashMap<>())) {
            return new MapProcessor();
        } else if (aClass.isInstance(new ArrayList())) {
            return new ListProcessor();
        } else if (aClass.isInstance(new LinkedList<>())) {
            return new ListProcessor();
        } else {
            return new SimpleProcessor();
        }
    }
}
