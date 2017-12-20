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
            dbs.addUser("Somebody", 25);
            new Executor(connection, dbs).save(new UsersDataSet(1, "NewUser", 30));
            new Executor(connection, dbs).save(new AnotherUsersDataSet(1, "Anotherone", 64,
                    "Some st. 666", "+9-666-999-18-57"));
            UsersDataSet user1 = new Executor(connection, dbs).load(1, UsersDataSet.class);
            System.out.println(user1);
            AnotherUsersDataSet user2 = new Executor(connection, dbs).load(3, AnotherUsersDataSet.class);
            System.out.println(user2);

            dbs.deleteTables();
        }
    }
}
