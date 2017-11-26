package ru.otus.l06;

import ru.otus.l06.atm.Atm;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.getAtmBalance();
        atm.getOwnerBalance();
        atm.giveCash(13300);
    }
}
