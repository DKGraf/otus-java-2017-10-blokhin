package ru.otus.l07.atm;

import org.junit.jupiter.api.Assertions;
import ru.otus.l07.department.DepartmentImpl;
import ru.otus.l07.interfaces.Atm;
import ru.otus.l07.interfaces.Department;
import ru.otus.l07.owner.AccountOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ru.otus.l07.atm.BanknotesNominal.*;

class AtmImplTest {
    @org.junit.jupiter.api.Test
    void giveCash() {
        List<BanknotesNominal> list = new ArrayList<>();
        list.add(RUB_50);
        list.add(RUB_100);
        list.add(RUB_500);
        list.add(RUB_1000);
        list.add(RUB_5000);

        Department department = new DepartmentImpl();
        Atm atm = new AtmImpl(list, 200, department);
        AccountOwner owner = new AccountOwner("John Doe", 40_000);

        atm.insertCard(owner);
        atm.getOwnerBalance();
        atm.giveCash(13300);
        int atmBalanceAfter0 = atm.getAtmBalance();
        int atmOwnerBalanceAfter0 = atm.getOwnerBalance();
        atm.ejectCard();

        Assertions.assertEquals(1_316_700, atmBalanceAfter0);
        Assertions.assertEquals(26_700, atmOwnerBalanceAfter0);
    }

    @org.junit.jupiter.api.Test
    void acceptCash() {
        List<BanknotesNominal> list = new ArrayList<>();
        list.add(RUB_50);
        list.add(RUB_100);
        list.add(RUB_500);
        list.add(RUB_1000);
        list.add(RUB_5000);

        Department department = new DepartmentImpl();
        Atm atm = new AtmImpl(list, 200, department);
        AccountOwner owner = new AccountOwner("John Doe", 40_000);

        atm.insertCard(owner);
        Map<BanknotesNominal, Integer> map = new TreeMap<>();
        map.put(RUB_50, 10);
        map.put(RUB_100, 10);
        map.put(RUB_500, 10);
        map.put(RUB_1000, 10);
        map.put(RUB_5000, 10);
        atm.acceptCash(map);
        int atmBalanceAfter = atm.getAtmBalance();
        int atmOwnerBalanceAfter = atm.getOwnerBalance();
        atm.ejectCard();

        Assertions.assertEquals(1_396_500, atmBalanceAfter);
        Assertions.assertEquals(106_500, atmOwnerBalanceAfter);
    }

    @org.junit.jupiter.api.Test
    void getAtmBalance() {
        List<BanknotesNominal> list = new ArrayList<>();
        list.add(RUB_50);
        list.add(RUB_100);
        list.add(RUB_500);
        list.add(RUB_1000);
        list.add(RUB_5000);

        Department department = new DepartmentImpl();
        Atm atm = new AtmImpl(list, 200, department);
        int atmBalance = atm.getAtmBalance();
        Assertions.assertEquals(1_330_000, atmBalance);
    }

    @org.junit.jupiter.api.Test
    void getOwnerBalance() {
        List<BanknotesNominal> list = new ArrayList<>();
        list.add(RUB_50);
        list.add(RUB_100);
        list.add(RUB_500);
        list.add(RUB_1000);
        list.add(RUB_5000);

        Department department = new DepartmentImpl();
        Atm atm = new AtmImpl(list, 200, department);
        AccountOwner owner = new AccountOwner("John Doe", 40_000);

        atm.insertCard(owner);
        int ownerBalance = atm.getOwnerBalance();
        Assertions.assertEquals(40_000, ownerBalance);
    }
}