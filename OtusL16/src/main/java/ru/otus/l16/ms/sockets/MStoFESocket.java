package ru.otus.l16.ms.sockets;

import ru.otus.l16.ms.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class MStoFESocket {
    private static final int DEFAULT_STEP_TIME = 10;
    private final static int PORT = 9998;
    private final static String GET_STATE = "getState";
    private final static AtomicInteger ID_GENERATOR = new AtomicInteger();
    private final int id;

    public MStoFESocket() {
        id = ID_GENERATOR.getAndIncrement();
        Messages.addFronend(id);
    }

    public void run() throws IOException {
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (true) {
                Socket client = ss.accept();
                process(client);
                client.close();
            }
        }
    }

    private void process(Socket client) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            String message = in.readLine();
            if (GET_STATE.equals(message)) {
                Messages.addFeToMsMessage(0, message);

                while (true) {
                    String cacheState = Messages.getMsToFeMessage(0);
                    if (cacheState != null) {
                        out.println(cacheState);
                        break;
                    }
                    try {
                        Thread.sleep(DEFAULT_STEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
