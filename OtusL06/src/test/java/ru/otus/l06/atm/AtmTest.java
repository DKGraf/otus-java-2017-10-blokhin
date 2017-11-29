package ru.otus.l06.atm;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

class AtmTest {
    @org.junit.jupiter.api.Test
    void giveCash() {
        ArrayList<Integer> nominals = new ArrayList<>();
        nominals.add(50);
        nominals.add(100);
        nominals.add(500);
        nominals.add(1000);
        nominals.add(5000);

        Atm atm = new Atm(nominals, 100);
        atm.getOwnerBalance();
        atm.insertCard(new AccountOwner("John Doe", 40_000));

        atm.giveCash(50_000);
        atm.giveCash(13_300);
        int balanceAfter = 651_700;
        int ownerBalanceAfter = 26_700;
        Assertions.assertEquals(balanceAfter, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter, atm.getOwnerBalance());

        atm.ejectCard();
        Assertions.assertEquals(null, atm.getOwner());
    }

    @org.junit.jupiter.api.Test
    void acceptCash() {
        ArrayList<Integer> nominals = new ArrayList<>();
        nominals.add(50);
        nominals.add(100);
        nominals.add(500);
        nominals.add(1000);
        nominals.add(5000);

        Atm atm = new Atm(nominals, 100);
        atm.insertCard(new AccountOwner("John Doe", 40_000));

        Map<Integer, Integer> map = new TreeMap<>();
        map.put(50, 10);
        map.put(100, 10);
        map.put(500, 10);
        map.put(1000, 10);
        map.put(5000, 10);

        atm.acceptCash(map);

        int balanceAfter0 = 731_500;
        int ownerBalanceAfter0 = 106_500;
        Assertions.assertEquals(balanceAfter0, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter0, atm.getOwnerBalance());

    }

    @org.junit.jupiter.api.Test
    void getAtmBalance() {
        ArrayList<Integer> nominals = new ArrayList<>();
        nominals.add(50);
        nominals.add(100);
        nominals.add(500);
        nominals.add(1000);
        nominals.add(5000);

        Atm atm = new Atm(nominals, 100);

        Assertions.assertEquals(665_000, atm.getAtmBalance());
    }

    @org.junit.jupiter.api.Test
    void getOwnerBalance() {
        ArrayList<Integer> nominals = new ArrayList<>();
        nominals.add(50);
        nominals.add(100);
        nominals.add(500);
        nominals.add(1000);
        nominals.add(5000);

        Atm atm = new Atm(nominals, 100);
        atm.insertCard(new AccountOwner("John Doe", 40_000));

        Assertions.assertEquals(40_000, atm.getOwnerBalance());
    }
}