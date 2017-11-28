package ru.otus.l06.atm;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
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
        List<AccountOwner> owners = new ArrayList<>();
        owners.add(new AccountOwner("John Doe", 40_000));
        owners.add(new AccountOwner("Jane Doe", 20_000));
        Atm atm = new Atm(nominals, 100, owners);

        atm.giveCash(13_300, 0);
        int balanceAfter0 = 651_700;
        int ownerBalanceAfter0 = 26_700;
        Assertions.assertEquals(balanceAfter0, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter0, atm.getOwnerBalance(0));

        atm.giveCash(6_000, 1);
        int balanceAfter1 = 645_700;
        int ownerBalanceAfter1 = 14_000;
        Assertions.assertEquals(balanceAfter1, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter1, atm.getOwnerBalance(1));

        atm = new Atm(nominals, 2, owners);
        atm.giveCash(13300, 0);
        balanceAfter0 = 0;
        Assertions.assertEquals(balanceAfter0, atm.getAtmBalance());
    }

    @org.junit.jupiter.api.Test
    void acceptCash() {
        ArrayList<Integer> nominals = new ArrayList<>();
        nominals.add(50);
        nominals.add(100);
        nominals.add(500);
        nominals.add(1000);
        nominals.add(5000);
        List<AccountOwner> owners = new ArrayList<>();
        owners.add(new AccountOwner("John Doe", 40_000));
        owners.add(new AccountOwner("Jane Doe", 20_000));
        Atm atm = new Atm(nominals, 100, owners);

        Map<Integer, Integer> map0 = new TreeMap<>();
        map0.put(50, 10);
        map0.put(100, 10);
        map0.put(500, 10);
        map0.put(1000, 10);
        map0.put(5000, 10);

        atm.acceptCash(map0, 0);

        int balanceAfter0 = 731_500;
        int ownerBalanceAfter0 = 106_500;
        Assertions.assertEquals(balanceAfter0, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter0, atm.getOwnerBalance(0));

        Map<Integer, Integer> map1 = new TreeMap<>();
        map1.put(50, 1);
        map1.put(100, 1);
        map1.put(500, 1);
        map1.put(1000, 1);
        map1.put(5000, 1);

        atm.acceptCash(map1, 1);

        int balanceAfter1 = 738_150;
        int ownerBalanceAfter1 = 26_650;
        Assertions.assertEquals(balanceAfter1, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter1, atm.getOwnerBalance(1));
    }

    @org.junit.jupiter.api.Test
    void getAtmBalance() {
        ArrayList<Integer> nominals = new ArrayList<>();
        nominals.add(50);
        nominals.add(100);
        nominals.add(500);
        nominals.add(1000);
        nominals.add(5000);
        List<AccountOwner> owners = new ArrayList<>();
        owners.add(new AccountOwner("John Doe", 40_000));
        Atm atm = new Atm(nominals, 100, owners);

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
        List<AccountOwner> owners = new ArrayList<>();
        owners.add(new AccountOwner("John Doe", 40_000));
        Atm atm = new Atm(nominals, 100, owners);

        Assertions.assertEquals(40_000, atm.getOwnerBalance(0));
    }
}