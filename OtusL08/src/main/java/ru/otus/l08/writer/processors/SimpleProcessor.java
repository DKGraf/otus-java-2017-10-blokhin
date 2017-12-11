package ru.otus.l08.writer.processors;

import java.lang.reflect.Field;

/**
 * Процессор для обработки примитивов, строк и прочего
 */

public class SimpleProcessor implements Processor {
    @Override
    public String process(Object obj, Field field) {
        return obj.toString();
    }
}
