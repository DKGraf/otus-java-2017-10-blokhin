package ru.otus.l07;

import ru.otus.l07.atm.Atm;
import ru.otus.l07.atm.BanknotesNominal;
import ru.otus.l07.department.Department;
import ru.otus.l07.owner.AccountOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ru.otus.l07.atm.BanknotesNominal.*;

/**
 * Просто небольшое тестовое баловство с полученным приложением.
 * В данном приложении предпринята попытка использования паттернов
 * Memento и Observer
 */

public class Main {
    public static void main(String[] args) {
        List<BanknotesNominal> list0 = new ArrayList<>();
        list0.add(RUB_50);
        list0.add(RUB_100);
        list0.add(RUB_500);
        list0.add(RUB_1000);
        list0.add(RUB_5000);

        List<BanknotesNominal> list1 = new ArrayList<>();
        list1.add(RUB_50);
        list1.add(RUB_100);
        list1.add(RUB_200);
        list1.add(RUB_500);
        list1.add(RUB_1000);

        List<BanknotesNominal> list2 = new ArrayList<>();
        list2.add(RUB_100);
        list2.add(RUB_200);
        list2.add(RUB_500);
        list2.add(RUB_1000);
        list2.add(RUB_2000);

        Department department = new Department();

        Atm atm0 = new Atm(list0, 200, department);
        Atm atm1 = new Atm(list1, 350, department);
        Atm atm2 = new Atm(list2, 500, department);

        department.getSummaryBalance();

        AccountOwner owner = new AccountOwner("John Doe", 40_000);

        atm0.insertCard(owner);
        atm0.getAtmBalance();
        atm0.getOwnerBalance();
        atm0.giveCash(13300);
        atm0.getAtmBalance();
        atm0.getOwnerBalance();
        Map<BanknotesNominal, Integer> map0 = new TreeMap<>();
        map0.put(RUB_50, 10);
        map0.put(RUB_100, 10);
        map0.put(RUB_500, 10);
        map0.put(RUB_1000, 10);
        map0.put(RUB_5000, 10);
        atm0.acceptCash(map0);
        atm0.getAtmBalance();
        atm0.getOwnerBalance();
        atm0.ejectCard();

        department.getSummaryBalance();

        atm1.insertCard(owner);
        atm1.getAtmBalance();
        atm1.getOwnerBalance();
        atm1.giveCash(13300);
        atm1.getAtmBalance();
        atm1.getOwnerBalance();
        Map<BanknotesNominal, Integer> map1 = new TreeMap<>();
        map1.put(RUB_50, 10);
        map1.put(RUB_100, 10);
        map1.put(RUB_200, 10);
        map1.put(RUB_500, 10);
        map1.put(RUB_1000, 10);
        atm1.acceptCash(map1);
        atm1.getAtmBalance();
        atm1.getOwnerBalance();
        atm1.ejectCard();

        department.getSummaryBalance();

        atm2.insertCard(owner);
        atm2.getAtmBalance();
        atm2.getOwnerBalance();
        atm2.giveCash(13300);
        atm2.getAtmBalance();
        atm2.getOwnerBalance();
        Map<BanknotesNominal, Integer> map2 = new TreeMap<>();
        map2.put(RUB_100, 10);
        map2.put(RUB_200, 10);
        map2.put(RUB_500, 10);
        map2.put(RUB_1000, 10);
        map2.put(RUB_2000, 10);
        atm2.acceptCash(map2);
        atm2.getAtmBalance();
        atm2.getOwnerBalance();
        atm2.ejectCard();

        department.getSummaryBalance();
        department.restoreInitialState();
        department.getSummaryBalance();
    }
}
