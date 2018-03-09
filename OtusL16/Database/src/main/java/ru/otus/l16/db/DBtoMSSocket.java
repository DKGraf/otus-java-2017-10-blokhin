package ru.otus.l16.db;

import com.google.gson.Gson;
import ru.otus.l16.db.monitoring.Monitoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DBtoMSSocket {
    private final static int PORT = 9000;
    private final static String GET_STATE = "getState";
    private final Monitoring monitoring;
    private final Gson gson;
    private final int index;

    DBtoMSSocket(Monitoring monitoring, int index) {
        this.monitoring = monitoring;
        this.gson = new Gson();
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
            String line = in.readLine();
            if (GET_STATE.equals(line)) {
                out.println(gson.toJson(monitoring.getCacheState()));
            }
        }
    }
}
