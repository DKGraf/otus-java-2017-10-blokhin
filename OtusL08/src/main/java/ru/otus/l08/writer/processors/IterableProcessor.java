package ru.otus.l08.writer.processors;

import org.json.simple.JSONArray;

import java.lang.reflect.Field;

/**
 * Процессор для обработки списков и множеств.
 */

public class IterableProcessor implements Processor {
    @SuppressWarnings("unchecked")
    @Override
    public String process(Object obj, Field field) {
        JSONArray array = new JSONArray();
        for (Object o :
                (Iterable) obj) {
            array.add(o);
        }
        return array.toJSONString();
    }
}
