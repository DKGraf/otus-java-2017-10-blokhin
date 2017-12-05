package ru.otus.l07.owner;

/**
 * Некий владелец банковского счета.
 */

public class Card {
    private String ownerName;
    private int balance;

    public Card(String ownerName, int balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public void decreaseBalance(int sum) {
        balance -= sum;
    }

    public void increaseBalance(int sum) {
        balance += sum;
    }
}