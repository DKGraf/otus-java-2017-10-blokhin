package ru.otus.l06.atm;

import org.junit.jupiter.api.Assertions;

import java.util.Map;
import java.util.TreeMap;

import static ru.otus.l06.atm.BanknotesNominal.*;

class AtmTest {
    @org.junit.jupiter.api.Test
    void giveCash() {
        Atm atm = new Atm(100);
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
        Atm atm = new Atm(100);
        atm.insertCard(new AccountOwner("John Doe", 40_000));

        Map<BanknotesNominal, Integer> map = new TreeMap<>();
        map.put(RUB_50, 10);
        map.put(RUB_100, 10);
        map.put(RUB_500, 10);
        map.put(RUB_1000, 10);
        map.put(RUB_5000, 10);

        atm.acceptCash(map);

        int balanceAfter0 = 731_500;
        int ownerBalanceAfter0 = 106_500;
        Assertions.assertEquals(balanceAfter0, atm.getAtmBalance());
        Assertions.assertEquals(ownerBalanceAfter0, atm.getOwnerBalance());
    }

    @org.junit.jupiter.api.Test
    void getAtmBalance() {
        Atm atm = new Atm(100);
        Assertions.assertEquals(665_000, atm.getAtmBalance());
    }

    @org.junit.jupiter.api.Test
    void getOwnerBalance() {
        Atm atm = new Atm(100);
        atm.insertCard(new AccountOwner("John Doe", 40_000));
        Assertions.assertEquals(40_000, atm.getOwnerBalance());
    }
}