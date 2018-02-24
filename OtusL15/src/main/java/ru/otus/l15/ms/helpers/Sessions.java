package ru.otus.l15.ms.helpers;

import org.eclipse.jetty.websocket.api.Session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Sessions {
    private Sessions() {
    }

    private final static ConcurrentMap<String, Session> sessions = new ConcurrentHashMap<>();

    public static ConcurrentMap<String, Session> getSessions() {
        return sessions;
    }
}
