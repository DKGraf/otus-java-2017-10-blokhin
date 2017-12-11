package ru.otus.l08.writer.processors;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Процессор для обработки Map
 */

public class MapProcessor implements Processor {
    @SuppressWarnings("unchecked")
    @Override
    public String process(Object obj, Field field) {
        Map map = new HashMap<>((Map) obj);
        JSONObject result = new JSONObject();
        map.forEach(result::put);
        return result.toJSONString();
    }
}
