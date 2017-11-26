package ru.otus.l06.atm;

/**
 * Некий владелец банковского счета. Для упрощения баланс
 * его счета захардкожен.
 */

class AccountOwner {
    private int balance = 32_768;

    AccountOwner() {
    }

    int getBalance() {
        return balance;
    }

    void setBalance(int balance) {
        this.balance = balance;
    }
}
