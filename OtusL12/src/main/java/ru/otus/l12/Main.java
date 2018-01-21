package ru.otus.l12;

import ru.otus.l12.base.DBServiceHibernateImpl;

public class Main {

    private static DBServiceHibernateImpl dbService = new DBServiceHibernateImpl();

    public static void main(String[] args) throws Exception {
        WebServer server = new WebServer(dbService);
        server.run();
        ActivityEmulator emulator = new ActivityEmulator(dbService);
        emulator.emulate();
    }
}