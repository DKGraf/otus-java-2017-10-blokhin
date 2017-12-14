package ru.otus.l08.writer.processors;

import org.json.simple.JSONArray;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Процессор для обработки массивов
 */

public class ArrayProcessor implements Processor {
    @SuppressWarnings("unchecked")
    @Override
    public Object process(Object obj, Field field) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < Array.getLength(obj); i++) {
            array.add(Array.get(obj, i));
        }
        return array;
    }
}
