package ru.otus.l08.writer;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.otus.l08.victims.TestObject;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ObjectWriterTest {
    @Test
    void getJSONString() throws IllegalAccessException {
        TestObject to1 = new TestObject();

        String gson1 = new Gson().toJson(123);
        String gson2 = new Gson().toJson("abc");
        Object gson3 = new Gson().toJson(null);

        String myJSON = new ObjectWriter(to1).getJSONString();
        String myJSON1 = new ObjectWriter(123).getJSONString();
        String myJSON2 = new ObjectWriter("abc").getJSONString();
        Object myJSON3 = new ObjectWriter(null).getJSONString();

        assertEquals(gson1, myJSON1);
        assertEquals(gson2, myJSON2);
        assertEquals(gson3, myJSON3);

        TestObject to2 = new Gson().fromJson(myJSON, TestObject.class);

        assertEquals(to1.getI1(), to2.getI1());
        assertEquals(to1.getS1(),to2.getS1());
        assertArrayEquals(to1.getArrayOfInt(), to2.getArrayOfInt());
        assertArrayEquals(to1.getArrayOfString(), to2.getArrayOfString());
        assertIterableEquals(to1.getList(), to2.getList());
        assertIterableEquals(to1.getLinList(), to2.getLinList());
        assertEquals(to1.getSet(), to2.getSet());
        assertEquals(to1.getField(), to2.getField());
        assertIterableEquals(to1.getMap().entrySet(), to2.getMap().entrySet());
    }
}