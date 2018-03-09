package ru.otus.l16.ms;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Messages {
    private static final ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>> feToMsMessages = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>> dbToMsMessages = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>> msToFeMessages = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>> msToDbMessages = new ConcurrentHashMap<>();

    private Messages() {
    }

    public static void addFronend(Integer id) {
        feToMsMessages.put(id, new ConcurrentLinkedQueue<>());
        msToFeMessages.put(id, new ConcurrentLinkedQueue<>());
    }

    public static void addDatabase(Integer id) {
        dbToMsMessages.put(id, new ConcurrentLinkedQueue<>());
        msToDbMessages.put(id, new ConcurrentLinkedQueue<>());
    }

    public static void addFeToMsMessage(Integer id, String message) {
        feToMsMessages.get(id).add(message);
    }

    public static void addDbToMSMessage(Integer id, String message) {
        dbToMsMessages.get(id).add(message);
    }

    public static void addMsToFeMessage(Integer id, String message) {
        msToFeMessages.get(id).add(message);
    }

    public static void addMsToDbMessage(Integer id, String message) {
        msToDbMessages.get(id).add(message);
    }

    public static String getFeToMsMessage(Integer id) {
        return feToMsMessages.get(id).poll();
    }

    public static String getDbToMsMessage(Integer id) {
        return dbToMsMessages.get(id).poll();
    }

    public static String getMsToFeMessage(Integer id) {
        return msToFeMessages.get(id).poll();
    }

    public static String getMsToDbMessage(Integer id) {
        return msToDbMessages.get(id).poll();
    }
}
