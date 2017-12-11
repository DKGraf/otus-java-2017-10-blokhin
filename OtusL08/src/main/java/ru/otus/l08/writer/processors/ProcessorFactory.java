package ru.otus.l08.writer.processors;

import ru.otus.l08.writer.checker.TypeChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
        } else if (aClass.isInstance(new ArrayList<>())) {
            return new IterableProcessor();
        } else if (aClass.isInstance(new LinkedList<>())) {
            return new IterableProcessor();
        } else if (aClass.isInstance(new HashSet<>())) {
            return new IterableProcessor();
        } else if (TypeChecker.isString(aClass)) {
            return new SimpleProcessor();
        } else if (TypeChecker.isPrimitiveOrWrapper(aClass)) {
            return new SimpleProcessor();
        } else {
            return new ClassProcessor();
        }
    }
}
