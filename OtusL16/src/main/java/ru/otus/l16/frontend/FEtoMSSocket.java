package ru.otus.l16.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FEtoMSSocket {
    private static final int DEFAULT_STEP_TIME = 10;
    private static final String HOST = "localhost";
    private static final int PORT = 9998;
    private PrintWriter out;
    private BufferedReader in;
    private final static String GET_STATE = "getState";

    FEtoMSSocket() {
    }

    public String getCacheStateJSON() {
        getConnection();
        String state = null;
        try {
            out.println(GET_STATE);
            while (true) {
                state = in.readLine();
                if (state != null && !state.equals(GET_STATE)) {
                    break;
                }
                try {
                    Thread.sleep(DEFAULT_STEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
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
