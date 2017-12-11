package ru.otus.l08.writer;

import ru.otus.l08.writer.builder.JSONStringBuilder;
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

    public ObjectWriter(Object obj) {
        this.obj = obj;
        aClass = obj.getClass();
        fields = aClass.getDeclaredFields();
    }

    @SuppressWarnings("unchecked")
    public String getJSONString() throws IllegalAccessException {
        JSONStringBuilder stringCreator = new JSONStringBuilder();
        if (TypeChecker.isPrimitiveOrWrapper(aClass)) {
            return obj.toString();
        } else if (TypeChecker.isString(aClass)) {
            return "\"" + obj + "\"";
        } else {
            for (Field field :
                    fields) {
                String fieldName = field.getName();
                Class fieldType = field.getType();
                Processor processor = ProcessorFactory.getProcessor(fieldType);
                field.setAccessible(true);
                Object fieldValue = field.get(obj);
                stringCreator.build(fieldName, processor.process(fieldValue, field), fieldType);
            }
        }
        return stringCreator.getJSONString();
    }
}
