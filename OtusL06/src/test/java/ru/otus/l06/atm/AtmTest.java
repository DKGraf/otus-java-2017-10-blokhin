package ru.otus.l06.atm;

import org.junit.jupiter.api.Assertions;

class AtmTest {
    @org.junit.jupiter.api.Test
    void giveCash() {
        Atm atm = new Atm();
        atm.giveCash(6_650);
        int balanceAfter = 658_350;
        int ownerBalanceAfter = 26_118;
        Assertions.assertEquals(balanceAfter, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter, atm.getOwnerBalance());
    }

    @org.junit.jupiter.api.Test
    void acceptCash() {
        Atm atm = new Atm();
        atm.acceptCash(5, 5, 5, 5, 5);
        int balanceAfter = 698_250;
        int ownerBalanceAfter = 66_018;
        Assertions.assertEquals(balanceAfter, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter, atm.getOwnerBalance());
    }

    @org.junit.jupiter.api.Test
    void getAtmBalance() {
        Atm atm = new Atm();
        Assertions.assertEquals(665_000, atm.getAtmBalance());
    }

    @org.junit.jupiter.api.Test
    void getOwnerBalance() {
        Atm atm = new Atm();
        Assertions.assertEquals(32_768, atm.getOwnerBalance());
    }
}