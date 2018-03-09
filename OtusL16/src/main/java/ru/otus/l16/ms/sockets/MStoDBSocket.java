package ru.otus.l16.ms.sockets;

import ru.otus.l16.ms.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class MStoDBSocket {
    private static final String HOST = "localhost";
    private static final int PORT = 9999;
    private PrintWriter out;
    private BufferedReader in;
    private final static AtomicInteger ID_GENERATOR = new AtomicInteger();
    private final int id;
    private final static String GET_STATE = "getState";

    public MStoDBSocket() {
        id = ID_GENERATOR.getAndIncrement();
        Messages.addDatabase(id);
    }

    public void getCacheStateJSON() {
        getConnection();
        String state;
        try {
            out.println(GET_STATE);

            while (true) {
                state = in.readLine();
                if (state != null) {
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Messages.addDbToMSMessage(0, state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getConnection() {
        try {
            Socket socket = new Socket(HOST, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}