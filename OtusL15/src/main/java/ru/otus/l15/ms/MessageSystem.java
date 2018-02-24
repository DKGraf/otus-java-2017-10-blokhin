package ru.otus.l15.ms;

import ru.otus.l15.db.DBServiceMS;
import ru.otus.l15.frontend.WebServer;
import ru.otus.l15.ms.websocket.db.DBWSServer;
import ru.otus.l15.ms.websocket.fe.FrontWSServer;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageSystem {
    private static final int DEFAULT_STEP_TIME = 10;
    private final FrontWSServer frontWSServer;
    private final DBWSServer dbwsServer;
    private DBServiceMS dbServiceMS;
    private WebServer server;

    public MessageSystem() {
        frontWSServer = new FrontWSServer();
        dbwsServer = new DBWSServer();
        dbServiceMS = new DBServiceMS();
        server = new WebServer();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        frontWSServer.start();
        dbwsServer.start();
        dbServiceMS.init();
        server.run();

        new Thread(
                () -> {
                    while (true) {
                        ConcurrentLinkedQueue<String> frontToMSmessages = Messages.getFrontToMSmessages();
                        ConcurrentLinkedQueue<String> dbToMSmessages = Messages.getDbToMSmessages();

                        if (!frontToMSmessages.isEmpty()) {
                            String message = frontToMSmessages.poll();
                            Sender.senToDB(message);
                            System.out.println("Msg to DB: " + message);
                        }
                        if (!dbToMSmessages.isEmpty()) {
                            String message = dbToMSmessages.poll();
                            Sender.senToFE(message);
                            System.out.println("Msg to FE: " + message);
                        }

                        try {
                            Thread.sleep(DEFAULT_STEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
    }
}
