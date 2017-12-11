package ru.otus.l08.writer.processors;

import org.json.simple.JSONObject;
import ru.otus.l08.writer.ObjectWriter;

import java.lang.reflect.Field;

public class ClassProcessor implements Processor {
    @Override
    public Object process(Object obj, Field field) throws IllegalAccessException {
        JSONObject result = new JSONObject();
        Object json = new ObjectWriter(obj).getJSONString();
        System.out.println(json);
        return json;
    }
}
