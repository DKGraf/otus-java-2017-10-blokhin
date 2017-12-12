package ru.otus.l08.writer;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.l08.victims.TestObject;

class ObjectWriterTest {
    @Test
    void getJSONString() throws IllegalAccessException {
        TestObject to1 = new TestObject();

        String gson1 = new Gson().toJson(to1);
        String gson2 = new Gson().toJson(123);
        String gson3 = new Gson().toJson("abc");
        Object gson4 = new Gson().toJson(null);

        String myJSON1 = new ObjectWriter(to1).getJSONString();
        String myJSON2 = new ObjectWriter(123).getJSONString();
        String myJSON3 = new ObjectWriter("abc").getJSONString();
        Object myJSON4 = new ObjectWriter(null).getJSONString();

        Assertions.assertEquals(gson1, myJSON1);
        Assertions.assertEquals(gson2, myJSON2);
        Assertions.assertEquals(gson3, myJSON3);
        Assertions.assertEquals(gson4, myJSON4);
    }
}