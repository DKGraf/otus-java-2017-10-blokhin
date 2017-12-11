package ru.otus.l08.writer.processors;

import java.lang.reflect.Field;

public interface Processor {
    String process(Object obj, Field field) throws IllegalAccessException;
}
