package ru.otus.l09;

import ru.otus.l09.base.DBService;
import ru.otus.l09.base.dataset.AnotherUsersDataSet;
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
            Connection connection = ConnectionHelper.getConnection();
            dbs.prepareTables();

            UsersDataSet user1 = new UsersDataSet(1, "Somebody", 30);
            AnotherUsersDataSet user2 = new AnotherUsersDataSet(1, "Anotherone",
                    30, "Some st.", "+15-468-795-45-67");
            new Executor(connection, dbs).save(user1, "users");
            new Executor(connection, dbs).save(user2, "ausers");

            UsersDataSet user3 = new Executor(connection, dbs).load(1, UsersDataSet.class, "users");
            AnotherUsersDataSet user4 = new Executor(connection, dbs).load(1, AnotherUsersDataSet.class, "ausers");

            System.out.println(user3);
            System.out.println(user4);

            dbs.deleteTables();
        }
    }
}
