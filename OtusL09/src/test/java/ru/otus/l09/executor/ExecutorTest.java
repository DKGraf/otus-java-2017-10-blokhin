package ru.otus.l09.executor;

import org.junit.jupiter.api.Test;
import ru.otus.l09.base.DBService;
import ru.otus.l09.base.dataset.AnotherUsersDataSet;
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

            UsersDataSet user1 = new UsersDataSet(1, "Somebody", 30);
            AnotherUsersDataSet user2 = new AnotherUsersDataSet(1, "Anotherone",
                    30, "Some st.", "+15-468-795-45-67");
            new Executor(connection, dbs).save(user1, "users");
            new Executor(connection, dbs).save(user2, "ausers");

            UsersDataSet user3 = new Executor(connection, dbs).load(1, UsersDataSet.class, "users");
            AnotherUsersDataSet user4 = new Executor(connection, dbs).load(1, AnotherUsersDataSet.class, "ausers");

            assertEquals(user3.getName(), "Somebody");
            assertEquals(user3.getAge(), 30);

            assertEquals(user4.getName(), "Anotherone");
            assertEquals(user4.getAge(), 30);
            assertEquals(user4.getAddress(), "Some st.");
            assertEquals(user4.getPhoneNumber(), "+15-468-795-45-67");

            dbs.deleteTables();
        }
    }
}