package ru.otus.l06;

import ru.otus.l06.atm.Atm;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.acceptCash(5, 5, 10, 10, 10);
    }
}
