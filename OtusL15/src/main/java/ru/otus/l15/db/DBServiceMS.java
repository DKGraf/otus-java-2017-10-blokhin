package ru.otus.l15.db;

import ru.otus.l15.db.dbservice.DBServiceHibernateImpl;
import ru.otus.l15.db.monitoring.Monitoring;

public class DBServiceMS {
    private DBServiceHibernateImpl dbService = new DBServiceHibernateImpl();
    private Monitoring monitoring;
    private WSMonitoringClient client;

    public DBServiceMS() {
        monitoring = new Monitoring(dbService);
        client = new WSMonitoringClient(monitoring);
    }

    public void init() {
        client.start();

        new Thread(
                () ->
                {
                    try {
                        new ActivityEmulator(dbService).emulate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
    }
}
