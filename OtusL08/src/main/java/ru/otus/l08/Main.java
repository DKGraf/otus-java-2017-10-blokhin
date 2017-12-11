package ru.otus.l08;

import com.google.gson.Gson;
import ru.otus.l08.writer.ObjectWriter;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        TestObject testObject = new TestObject();
        Gson gson = new Gson();
        String json = gson.toJson(testObject);
        System.out.println("gson: " + json);
        ObjectWriter os = new ObjectWriter(testObject);
        System.out.println("myOW: " + os.getJSONString());
    }
}
