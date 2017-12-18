package ru.otus.l09;

import ru.otus.l09.base.DBService;
import ru.otus.l09.base.dataset.UsersDataSet;
import ru.otus.l09.connection.ConnectionHelper;
import ru.otus.l09.executor.Executor;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    private void run() throws Exception {
        try (DBService dbs = new DBService()) {
            System.out.println(dbs.getMetaData() + "\n");
            dbs.prepareTables();
            dbs.addUser("Somebody", 25);
            System.out.println("User name: " + dbs.getUserName(1));
            System.out.println("User age: " + dbs.getUserAge(1) + "\n");

            Connection connection = ConnectionHelper.getConnection();
            new Executor(connection, new DBService()).save(new UsersDataSet(1, "Anotherone", 30));
            System.out.println("User name: " + dbs.getUserName(2));
            System.out.println("User age: " + dbs.getUserAge(2) + "\n");

            System.out.println(new Executor(connection, new DBService()).load(1, UsersDataSet.class));

            dbs.deleteTables();
        }
    }
}
