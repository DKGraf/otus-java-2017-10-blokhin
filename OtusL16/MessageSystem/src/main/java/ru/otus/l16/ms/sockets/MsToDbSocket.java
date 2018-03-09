package ru.otus.l16.ms.sockets;

import ru.otus.l16.ms.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MsToDbSocket {
    private static final int DEFAULT_STEP_TIME = 10;
    private static final String HOST = "localhost";
    private static final int PORT = 9000;
    private PrintWriter out;
    private BufferedReader in;
    private final static String GET_STATE = "getState";
    private final int index;

    public MsToDbSocket(int index) {
        this.index = index;
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
                    Thread.sleep(DEFAULT_STEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Messages.addDbToMSMessage(index, state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getConnection() {
        try {
            Socket socket = new Socket(HOST, PORT + index);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}