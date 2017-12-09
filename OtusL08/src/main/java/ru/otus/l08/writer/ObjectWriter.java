package ru.otus.l08.writer;

import org.json.simple.JSONObject;
import ru.otus.l08.writer.processors.Processor;
import ru.otus.l08.writer.processors.ProcessorFactory;

import java.lang.reflect.Field;

/**
 * Основной класс сериализатора
 */

public class ObjectWriter {
    private Object obj;
    private Field[] fields;

    public ObjectWriter(Object obj) {
        this.obj = obj;
        Class aClass = obj.getClass();
        fields = aClass.getDeclaredFields();
    }

    @SuppressWarnings("unchecked")
    public JSONObject write() throws IllegalAccessException {
        JSONObject result = new JSONObject();
        for (Field field :
                fields) {
            String fieldName = field.getName();
            Class fieldType = field.getType();
            Processor processor = ProcessorFactory.getProcessor(fieldType);
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            result.put(fieldName, processor.process(fieldValue, field));
        }
        return result;
    }
}
