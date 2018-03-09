package ru.otus.l16.db;

import ru.otus.l16.db.dbservice.DBServiceHibernateImpl;
import ru.otus.l16.db.monitoring.Monitoring;

import java.io.IOException;

public class DBServiceMS {
    private DBServiceHibernateImpl dbService = new DBServiceHibernateImpl();
    private DBtoMSSocket socket;

    DBServiceMS(int index) {
        Monitoring monitoring = new Monitoring(dbService);
        socket = new DBtoMSSocket(monitoring, index);
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
