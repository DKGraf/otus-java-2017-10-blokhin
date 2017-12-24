package ru.otus.l10;

import ru.otus.l10.base.DBServiceImpl;
import ru.otus.l10.base.datasets.UserDataSet;
import ru.otus.l10.base.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

import static ru.otus.l09.connection.ConnectionHelper.getConnection;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
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

            UserDataSet user1 = new UserDataSet("Someone1", 32);
            UserDataSet user2 = new UserDataSet("Someone2", 64);
            dbs.save(user1, "users");
            dbs.save(user2, "users");

            UserDataSet user3 = dbs.load(1, UserDataSet.class, "users");
            UserDataSet user4 = dbs.load(1, UserDataSet.class, "users");

            System.out.println(user3);
            System.out.println(user4);
            //exec.execUpdate("drop table users");
            //exec.execUpdate("drop table ausers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
