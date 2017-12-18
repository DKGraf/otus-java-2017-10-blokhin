package ru.otus.l09.executor;

import org.junit.jupiter.api.Test;
import ru.otus.l09.base.DBService;
import ru.otus.l09.base.dataset.UsersDataSet;
import ru.otus.l09.connection.ConnectionHelper;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExecutorTest {

    @Test
    void executorTest() throws Exception {
        try (DBService dbs = new DBService()) {
            Connection connection = ConnectionHelper.getConnection();
            dbs.prepareTables();
            dbs.addUser("Somebody1", 25);
            dbs.addUser("Somebody2", 20);
            dbs.addUser("Somebody3", 24);
            dbs.addUser("Somebody4", 32);
            new Executor(connection, new DBService()).save(new UsersDataSet(1, "Anotherone", 30));
            UsersDataSet user6 =  new Executor(connection, new DBService()).load(1, UsersDataSet.class);

            assertEquals(dbs.getUserName(1) , "Somebody1");
            assertEquals(dbs.getUserName(2) , "Somebody2");
            assertEquals(dbs.getUserName(3) , "Somebody3");
            assertEquals(dbs.getUserName(4) , "Somebody4");
            assertEquals(dbs.getUserName(5) , "Anotherone");

            assertEquals(dbs.getUserAge(1) , 25);
            assertEquals(dbs.getUserAge(2) , 20);
            assertEquals(dbs.getUserAge(3) , 24);
            assertEquals(dbs.getUserAge(4) , 32);
            assertEquals(dbs.getUserAge(5) , 30);

            assertEquals(user6.getName(), "Somebody1");
            assertEquals(user6.getAge(), 25);

            dbs.deleteTables();
        }
    }
}