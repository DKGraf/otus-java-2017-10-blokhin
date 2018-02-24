package ru.otus.l15.ms.helpers;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Messages {
    private static final ConcurrentLinkedQueue<String> frontToMSmessages = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<String> dbToMSmessages = new ConcurrentLinkedQueue<>();

    private Messages() {
    }

    public static ConcurrentLinkedQueue<String> getFrontToMSmessages() {
        return frontToMSmessages;
    }

    public static ConcurrentLinkedQueue<String> getDbToMSmessages() {
        return dbToMSmessages;
    }
}
