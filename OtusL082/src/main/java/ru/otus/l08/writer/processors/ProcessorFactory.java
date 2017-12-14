package ru.otus.l08.writer.processors;

import ru.otus.l08.writer.checker.TypeChecker;

import java.util.*;

/**
 * Фабрика, статичный метод которой выбирает какой вид процессора создать
 */

public class ProcessorFactory {
    private ProcessorFactory() {
    }

    @SuppressWarnings("unchecked")
    public static Processor getProcessor(Class aClass) {
        if (aClass.isArray()) {
            return new ArrayProcessor();
        } else if (aClass.isAssignableFrom(HashMap.class)) {
            return new MapProcessor();
        } else if (aClass.isAssignableFrom(TreeMap.class)) {
            return new MapProcessor();
        } else if (aClass.isAssignableFrom(ArrayList.class)) {
            return new IterableProcessor();
        } else if (aClass.isAssignableFrom(LinkedList.class)) {
            return new IterableProcessor();
        } else if (aClass.isAssignableFrom(HashSet.class)) {
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
