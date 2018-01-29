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
//                new Thread(() -> {
//                    while (!client.isClosed()) {
//                        try {
//                            process(client);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
            }
        }
    }

    private void process(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            if (line.equals(GET_STATE)) {
                out.println(gson.toJson(monitoring.getCacheState()));
            }
        }
    }
}
