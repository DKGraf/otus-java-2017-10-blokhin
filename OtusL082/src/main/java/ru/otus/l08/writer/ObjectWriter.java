package ru.otus.l08.writer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import ru.otus.l08.writer.checker.TypeChecker;
import ru.otus.l08.writer.processors.Processor;
import ru.otus.l08.writer.processors.ProcessorFactory;

import java.lang.reflect.Field;

/**
 * Основной класс сериализатора
 */

public class ObjectWriter {
    private Object obj;
    private Field[] fields;
    private Class aClass;
    private JSONObject json;

    public ObjectWriter(Object obj) {
        this.obj = obj;
        if (obj != null) {
            aClass = obj.getClass();
            fields = aClass.getDeclaredFields();
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject getJSON() throws IllegalAccessException {
        JSONObject result = new JSONObject();
        for (Field field :
                fields) {
            String fieldName = field.getName();
            Class fieldType = field.getType();
            Processor processor = ProcessorFactory.getProcessor(fieldType);
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            if (fieldValue != null) {
                result.put(fieldName, processor.process(fieldValue, field));
            }
        }
        return result;
    }

    public String getJSONString() throws IllegalAccessException {
        if (obj == null) return "null";
        if (TypeChecker.isPrimitiveOrWrapper(aClass)) {
            return JSONValue.toJSONString(obj);
        } else if (TypeChecker.isString(aClass)) {
            return JSONValue.toJSONString(obj);
        } else {
            json = getJSON();
        }
        return json.toJSONString();
    }
}