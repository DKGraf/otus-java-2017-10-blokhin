package ru.otus.l09;

import ru.otus.l09.base.DBService;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    void run() throws Exception {
        try (DBService dbs = new DBService()) {
            dbs.prepareTables();
        }
    }
}
