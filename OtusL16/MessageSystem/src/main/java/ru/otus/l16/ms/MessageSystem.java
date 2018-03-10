package ru.otus.l16.ms;

import ru.otus.l16.ms.sockets.MsToDbSocket;
import ru.otus.l16.ms.sockets.MsToFeSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageSystem {
    private static final int DEFAULT_STEP_TIME = 10;
    private final int count;
    private final Map<Integer, MsToDbSocket> msToDbSocketList;
    private final Map<Integer, MsToFeSocket> msToFeSocketList;

    private static final String DB_START_COMMAND = "java -jar ./Database/target/Database-1.0-SNAPSHOT.jar";
    private static final String FE_START_COMMAND = "java -jar ./Frontend/target/Frontend-1.0-SNAPSHOT.jar";
    private static final int START_DELAY = 5000;

    MessageSystem(int count) {
        this.count = count;
        msToDbSocketList = new HashMap<>();
        msToFeSocketList = new HashMap<>();
    }

    public void start() {
        initLists();
        startDatabase();
        startSocket();
        startLoop();
        startFrontend();
    }

    private void initLists() {
        for (int i = 0; i < count; i++) {
            msToDbSocketList.put(i, new MsToDbSocket(i));
            Messages.addDatabase(i);
            msToFeSocketList.put(i, new MsToFeSocket(i));
            Messages.addFronend(i);
        }
    }

    private void startDatabase() {
        for (int i = 0; i < count; i++) {
            try {
                String command = DB_START_COMMAND + " " + i;
                String[] pbCommand = command.split(" ");
                ProcessBuilder pb = new ProcessBuilder(pbCommand);
                pb.start();
                try {
                    Thread.sleep(START_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Database service #" + i + " started. Cache parameters: " +
                        "elements size - " + (50 * (i + 1)) + ", lifetime - " + (1500 * (i + 1)) + " ms.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void startFrontend() {
        for (int i = 0; i < count; i++) {
            try {
                String command = FE_START_COMMAND + " " + i;
                String[] pbCommand = command.split(" ");
                ProcessBuilder pb = new ProcessBuilder(pbCommand);
                pb.start();
                try {
                    Thread.sleep(START_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Frontend service #" + i + " started on localhost:" + (8090 + i) + ".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All modules is started!");
    }

    private void startSocket() {
        for (int i = 0; i < count; i++) {
            int finalI = i;
            new Thread(
                    () -> {
                        try {
                            msToFeSocketList.get(finalI).run();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            ).start();
        }

    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void startLoop() {
        for (int i = 0; i < count; i++) {
            int finalI = i;
            new Thread(
                    () -> {
                        while (true) {
                            String feToMSMessage = Messages.getFeToMsMessage(finalI);
                            String dbToMSMessage = Messages.getDbToMsMessage(finalI);

                            if (feToMSMessage != null) {
                                msToDbSocketList.get(finalI).getCacheStateJSON();
                            }
                            if (dbToMSMessage != null) {
                                Messages.addMsToFeMessage(finalI, dbToMSMessage);
                            }

                            try {
                                Thread.sleep(DEFAULT_STEP_TIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();
            System.out.println("Loop #" + i + " started.");
        }
    }
}
