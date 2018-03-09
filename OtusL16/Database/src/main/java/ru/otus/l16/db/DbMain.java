package ru.otus.l16.db;

public class DbMain {
    public static void main(String[] args) {
        int index = Integer.parseInt(args[0]);
        DBServiceMS dbService = new DBServiceMS(index);
        dbService.run();
    }
}
