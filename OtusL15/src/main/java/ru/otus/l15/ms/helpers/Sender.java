package ru.otus.l15.ms.helpers;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

public class Sender {
    private Sender() {
    }

    public static void senToDB(String message) {
        try {
            Session session = Sessions.getSessions().get("db");
            if (session != null) {
                session.getRemote().sendString(message);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
