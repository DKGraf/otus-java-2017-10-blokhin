package ru.otus.l13;

import com.google.gson.Gson;
import ru.otus.l13.base.DBServiceHibernateImpl;
import ru.otus.l13.base.monitoring.Monitoring;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CacheMonitoringServer {
    private final static int PORT = 9999;
    private final static String GET_STATE = "getState";
    private final Monitoring monitoring;
    private final Gson gson;

    CacheMonitoringServer(DBServiceHibernateImpl dbService) {
        monitoring = new Monitoring(dbService);
        gson = new Gson();
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
            String line = in.readLine();
            if (GET_STATE.equals(line)) {
                out.println(gson.toJson(monitoring.getCacheState()));
            }
        }
    }
}
