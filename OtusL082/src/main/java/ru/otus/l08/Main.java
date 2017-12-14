package ru.otus.l08;

import com.google.gson.Gson;
import ru.otus.l08.victims.TestObject;
import ru.otus.l08.writer.ObjectWriter;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        TestObject testObject = new TestObject();
        System.out.println("gson: " + new Gson().toJson(testObject));
        System.out.println("myOW: " + new ObjectWriter(testObject).getJSONString() + "\n");

        System.out.println("gson: " + new Gson().toJson(123));
        System.out.println("myOW: " + new ObjectWriter(123).getJSONString() + "\n");

        System.out.println("gson: " + new Gson().toJson("abc"));
        System.out.println("myOW: " + new ObjectWriter("abc").getJSONString() + "\n");

        System.out.println("gson: " + new Gson().toJson(null));
        System.out.println("myOW: " + new ObjectWriter(null).getJSONString() + "\n");
    }
}
