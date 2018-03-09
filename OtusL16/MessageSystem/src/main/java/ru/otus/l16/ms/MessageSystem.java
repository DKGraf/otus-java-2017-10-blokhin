package ru.otus.l16.ms;

import ru.otus.l16.ms.sockets.MStoDBSocket;
import ru.otus.l16.ms.sockets.MStoFESocket;

import java.io.IOException;

public class MessageSystem {
    private static final int DEFAULT_STEP_TIME = 10;
    private final MStoDBSocket msToDbSocket;
    private final MStoFESocket msToFeSocket;

    private static final String DB_START_COMMAND = "java -jar ./Database/target/Database-1.0-SNAPSHOT.jar";
    private static final String FE_START_COMMAND = "java -jar ./Frontend/target/Frontend-1.0-SNAPSHOT.jar";
    private static final int START_DELAY = 3000;

    public MessageSystem() {
        msToDbSocket = new MStoDBSocket();
        msToFeSocket = new MStoFESocket();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        startDatabase();
        startSocket();
        startLoop();
        startFrontend();
    }

    private void startDatabase() {
        try {
            ProcessBuilder pb = new ProcessBuilder(DB_START_COMMAND.split(" "));
//            pb.inheritIO();
            pb.start();
            try {
                Thread.sleep(START_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startFrontend() {
        try {
            ProcessBuilder pb = new ProcessBuilder(FE_START_COMMAND.split(" "));
//            pb.inheritIO();
            pb.start();
            try {
                Thread.sleep(START_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("All modules is started!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startSocket() {
        new Thread(
                () -> {
                    try {
                        msToFeSocket.run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    private void startLoop() {
        new Thread(
                () -> {
                    while (true) {
                        String feToMSMessage = Messages.getFeToMsMessage(0);
                        String dbToMSMessage = Messages.getDbToMsMessage(0);

                        if (feToMSMessage != null) {
                            msToDbSocket.getCacheStateJSON();
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
}
