package ru.otus.l07.atm;

import org.junit.jupiter.api.Assertions;
import ru.otus.l07.department.DepartmentImpl;
import ru.otus.l07.interfaces.Atm;
import ru.otus.l07.interfaces.Department;
import ru.otus.l07.owner.Card;

import java.util.*;

import static ru.otus.l07.atm.BanknotesNominal.*;

class AtmImplTest {
    @org.junit.jupiter.api.Test
    void giveCash() {
        Map<BanknotesNominal, Integer> map0 = new HashMap<>();
        map0.put(RUB_50, 100);
        map0.put(RUB_100, 100);
        map0.put(RUB_500, 100);
        map0.put(RUB_1000, 100);
        map0.put(RUB_5000, 150);

        Department department = new DepartmentImpl();
        Atm atm = new AtmImpl(map0, department);
        Card owner = new Card("John Doe", 40_000);

        atm.insertCard(owner);
        atm.getCardBalance();
        atm.giveCash(13300);
        int atmBalanceAfter0 = atm.getAtmBalance();
        int atmOwnerBalanceAfter0 = atm.getCardBalance();
        atm.ejectCard();

        Assertions.assertEquals(901_700, atmBalanceAfter0);
        Assertions.assertEquals(26_700, atmOwnerBalanceAfter0);
    }

    @org.junit.jupiter.api.Test
    void acceptCash() {
        Map<BanknotesNominal, Integer> map0 = new HashMap<>();
        map0.put(RUB_50, 100);
        map0.put(RUB_100, 100);
        map0.put(RUB_500, 100);
        map0.put(RUB_1000, 100);
        map0.put(RUB_5000, 150);

        Department department = new DepartmentImpl();
        Atm atm = new AtmImpl(map0, department);
        Card owner = new Card("John Doe", 40_000);

        atm.insertCard(owner);
        Map<BanknotesNominal, Integer> map = new TreeMap<>();
        map.put(RUB_50, 10);
        map.put(RUB_100, 10);
        map.put(RUB_500, 10);
        map.put(RUB_1000, 10);
        map.put(RUB_5000, 10);
        atm.acceptCash(map);
        int atmBalanceAfter = atm.getAtmBalance();
        int atmOwnerBalanceAfter = atm.getCardBalance();
        atm.ejectCard();

        Assertions.assertEquals(981_500, atmBalanceAfter);
        Assertions.assertEquals(106_500, atmOwnerBalanceAfter);
    }

    @org.junit.jupiter.api.Test
    void getAtmBalance() {
        Map<BanknotesNominal, Integer> map0 = new HashMap<>();
        map0.put(RUB_50, 100);
        map0.put(RUB_100, 100);
        map0.put(RUB_500, 100);
        map0.put(RUB_1000, 100);
        map0.put(RUB_5000, 150);

        Department department = new DepartmentImpl();
        Atm atm = new AtmImpl(map0, department);
        int atmBalance = atm.getAtmBalance();
        Assertions.assertEquals(915_000, atmBalance);
    }

    @org.junit.jupiter.api.Test
    void getOwnerBalance() {
        Map<BanknotesNominal, Integer> map0 = new HashMap<>();
        map0.put(RUB_50, 100);
        map0.put(RUB_100, 100);
        map0.put(RUB_500, 100);
        map0.put(RUB_1000, 100);
        map0.put(RUB_5000, 150);

        Department department = new DepartmentImpl();
        Atm atm = new AtmImpl(map0, department);
        Card owner = new Card("John Doe", 40_000);

        atm.insertCard(owner);
        int ownerBalance = atm.getCardBalance();
        Assertions.assertEquals(40_000, ownerBalance);
    }
}