package ru.otus.l16.ms;

import ru.otus.l16.db.DBServiceMS;
import ru.otus.l16.frontend.WebServer;
import ru.otus.l16.ms.sockets.MStoDBSocket;
import ru.otus.l16.ms.sockets.MStoFESocket;

import java.io.IOException;

public class MessageSystem {
    private static final int DEFAULT_STEP_TIME = 10;
    private DBServiceMS dbServiceMS;
    private WebServer server;
    private final MStoDBSocket msToDBSocket;
    private final MStoFESocket msToFESocket;

    public MessageSystem() {
        dbServiceMS = new DBServiceMS();
        server = new WebServer();
        msToDBSocket = new MStoDBSocket();
        msToFESocket = new MStoFESocket();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        runServices();

        new Thread(
                () -> {
                    while (true) {
                        String feToMSMessage = Messages.getFeToMsMessage(0);
                        String dbToMSMessage = Messages.getDbToMsMessage(0);

                        if (feToMSMessage != null) {
                            msToDBSocket.getCacheStateJSON();
                        }
                        if (dbToMSMessage != null) {
                            Messages.addMsToFeMessage(0, dbToMSMessage);
                        }

                        try {
                            Thread.sleep(DEFAULT_STEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    private void runServices() {
        dbServiceMS.init();

        new Thread(
                () -> {
                    try {
                        msToFESocket.run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

        server.run();
    }
}
