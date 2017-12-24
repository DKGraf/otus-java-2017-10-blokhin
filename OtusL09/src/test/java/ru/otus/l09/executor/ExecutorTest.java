package ru.otus.l09.executor;

import org.junit.jupiter.api.Test;
import ru.otus.l09.base.DBServiceImpl;
import ru.otus.l09.base.dataset.AnotherUsersDataSet;
import ru.otus.l09.base.dataset.UsersDataSet;
import ru.otus.l09.base.executor.Executor;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.l09.base.connection.ConnectionHelper.getConnection;

class ExecutorTest {

    @Test
    void executorTest() throws Exception {
        try (Connection connection = getConnection()) {
            Executor exec = new Executor(connection);

            exec.execUpdate("create table if not exists users (" +
                    "id bigserial not null primary key, " +
                    "name varchar, " +
                    "age integer)");
            exec.execUpdate("create table if not exists ausers (" +
                    "id bigserial not null primary key, " +
                    "name varchar, " +
                    "age integer, " +
                    "address varchar, " +
                    "phone varchar)");
            DBServiceImpl dbs = new DBServiceImpl();

            UsersDataSet user1 = new UsersDataSet(1, "Someone1", 32);
            UsersDataSet user2 = new UsersDataSet(2, "Someone2", 64);
            AnotherUsersDataSet user3 = new AnotherUsersDataSet(1, "Anotherone",
                    30, "Some st.", "+15-468-795-45-67");
            dbs.save(user1, "users");
            dbs.save(user2, "users");
            dbs.save(user3, "ausers");

            UsersDataSet user4 = dbs.load(1, UsersDataSet.class, "users");
            UsersDataSet user5 = dbs.load(2, UsersDataSet.class, "users");
            AnotherUsersDataSet user6 = dbs.load(1, AnotherUsersDataSet.class, "ausers");
            System.out.println(user4);
            System.out.println(user5);
            System.out.println(user6);

            assertEquals(user4.getName(), "Someone1");
            assertEquals(user4.getAge(), 32);
            assertEquals(user5.getName(), "Someone2");
            assertEquals(user5.getAge(), 64);
            assertEquals(user6.getName(), "Anotherone");
            assertEquals(user6.getAge(), 30);
            assertEquals(user6.getAddress(), "Some st.");
            assertEquals(user6.getPhoneNumber(), "+15-468-795-45-67");

            exec.execUpdate("drop table users");
            exec.execUpdate("drop table ausers");
        }
    }
}