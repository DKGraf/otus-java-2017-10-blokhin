package ru.otus.l08.writer.processors;

import org.json.simple.JSONArray;

import java.lang.reflect.Field;

/**
 * Процессор для обработки списков
 */

public class ListProcessor implements Processor {
    @SuppressWarnings("unchecked")
    @Override
    public Object process(Object obj, Field field) {
        JSONArray array = new JSONArray();
        for (Object o :
                (Iterable) obj) {
            array.add(o);
        }
        return array;
    }
}
