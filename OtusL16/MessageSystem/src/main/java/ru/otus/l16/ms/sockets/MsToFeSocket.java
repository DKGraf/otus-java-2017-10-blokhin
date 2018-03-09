package ru.otus.l16.ms.sockets;

import ru.otus.l16.ms.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MsToFeSocket {
    private static final int DEFAULT_STEP_TIME = 10;
    private final static int PORT = 9500;
    private final static String GET_STATE = "getState";
    private final int index;

    public MsToFeSocket(int index) {
        this.index = index;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() throws IOException {
        try (ServerSocket ss = new ServerSocket(PORT + index)) {
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
                Messages.addFeToMsMessage(index, message);

                while (true) {
                    String cacheState = Messages.getMsToFeMessage(index);
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
