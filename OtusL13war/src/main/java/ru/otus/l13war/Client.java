package ru.otus.l13war;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private final static String GET_STATE = "getState";

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getCacheStateJSON() throws IOException {
        getConnection();
        String state = "";
        try {
            out.println(GET_STATE);
            state = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
    }

    private void getConnection() throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
}