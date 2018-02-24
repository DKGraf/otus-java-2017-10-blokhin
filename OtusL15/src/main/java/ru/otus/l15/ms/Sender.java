package ru.otus.l15.ms;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

public class Sender {
    public static void senToDB(String message) {
        try {
            Session session = Sessions.getSessions().get("db");
            if (session != null) {
                session.getRemote().sendString(message);
            } else {
                System.err.println("\n*****Session is NULL*****\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void senToFE(String message) {
        try {
            Session session = Sessions.getSessions().get("fe");
            if (session != null) {
                session.getRemote().sendString(message);
            } else {
                System.err.println("\n*****Session is NULL*****\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
