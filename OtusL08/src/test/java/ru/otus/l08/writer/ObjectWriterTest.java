package ru.otus.l08.writer;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.l08.TestObject;

class ObjectWriterTest {

    @Test
    void write() throws IllegalAccessException {
        TestObject to1 = new TestObject();
        ObjectWriter os = new ObjectWriter(to1);
        String myJSON = os.write().toString();

        Gson gson = new Gson();
        TestObject to2 = gson.fromJson(myJSON, TestObject.class);

        Assertions.assertEquals(to1.getI1(), to2.getI1());
        Assertions.assertEquals(to1.getS1(), to2.getS1());
        Assertions.assertArrayEquals(to1.getArrayOfInt(), to2.getArrayOfInt());
        Assertions.assertArrayEquals(to1.getArrayOfString(), to2.getArrayOfString());
        Assertions.assertIterableEquals(to1.getList(), to2.getList());
        Assertions.assertIterableEquals(to1.getLinList(), to2.getLinList());
        Assertions.assertEquals(to1.getMap(), to2.getMap());
    }
}