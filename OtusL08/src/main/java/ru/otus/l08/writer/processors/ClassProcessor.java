package ru.otus.l08.writer.processors;

import ru.otus.l08.writer.ObjectWriter;

import java.lang.reflect.Field;

public class ClassProcessor implements Processor {
    @Override
    public String process(Object obj, Field field) throws IllegalAccessException {
        return new ObjectWriter(obj).getJSONString();
    }
}
