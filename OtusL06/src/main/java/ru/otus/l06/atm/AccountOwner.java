package ru.otus.l06.atm;

/**
 * Некий владелец банковского счета.
 */

public class AccountOwner {
    private static int id = 0;
    private String name;
    private int balance;

    public AccountOwner(String name, int balance) {
        this.name = name;
        this.balance = balance;
        id++;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    int getBalance() {
        return balance;

    }

    void setBalance(int balance) {
        this.balance = balance;
    }
}