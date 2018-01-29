package ru.otus.l13;

import ru.otus.l13.base.DBServiceHibernateImpl;

import java.io.IOException;

public class Main {

    private static DBServiceHibernateImpl dbService = new DBServiceHibernateImpl();

    public static void main(String[] args) {
        CacheMonitoringServer server = new CacheMonitoringServer(dbService);
        ActivityEmulator emulator = new ActivityEmulator(dbService);

        new Thread(() -> {
            try {
                emulator.emulate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                server.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}