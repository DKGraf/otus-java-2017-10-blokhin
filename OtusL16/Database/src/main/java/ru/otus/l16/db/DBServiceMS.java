package ru.otus.l16.db;

import ru.otus.l16.db.dbservice.DBServiceHibernateImpl;
import ru.otus.l16.db.monitoring.Monitoring;

import java.io.IOException;

public class DBServiceMS {
    private DBServiceHibernateImpl dbService;
    private DBtoMSSocket socket;

    DBServiceMS(int param) {
        dbService = new DBServiceHibernateImpl(param);
        Monitoring monitoring = new Monitoring(dbService);
        socket = new DBtoMSSocket(monitoring, param);
    }

    public void run() {
        new Thread(
                () -> {
                    try {
                        socket.run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        new ActivityEmulator(dbService).emulate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
}
