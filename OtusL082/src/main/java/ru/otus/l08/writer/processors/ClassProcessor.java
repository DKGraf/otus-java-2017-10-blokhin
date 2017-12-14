package ru.otus.l08.writer.processors;

import org.json.simple.JSONValue;
import ru.otus.l08.writer.ObjectWriter;

import java.lang.reflect.Field;

public class ClassProcessor implements Processor {
    @Override
    public Object process(Object obj, Field field) throws IllegalAccessException {
        return JSONValue.parse(new ObjectWriter(obj).getJSONString());
    }
}