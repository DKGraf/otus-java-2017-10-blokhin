package ru.otus.l07.owner;

/**
 * Некий владелец банковского счета.
 */

public class AccountOwner {
    private String name;
    private int balance;

    public AccountOwner(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}