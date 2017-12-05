package ru.otus.l07;

import ru.otus.l07.atm.AtmImpl;
import ru.otus.l07.atm.BanknotesNominal;
import ru.otus.l07.department.DepartmentImpl;
import ru.otus.l07.interfaces.Atm;
import ru.otus.l07.interfaces.Department;
import ru.otus.l07.owner.Card;

import java.util.*;

import static ru.otus.l07.atm.BanknotesNominal.*;

/**
 * Просто небольшое тестовое баловство с полученным приложением.
 * В данном приложении предпринята попытка использования паттернов
 * Memento и Atm
 */

public class Main {
    public static void main(String[] args) {
        Map<BanknotesNominal, Integer> map0 = new HashMap<>();
        map0.put(RUB_50, 100);
        map0.put(RUB_100, 100);
        map0.put(RUB_500, 100);
        map0.put(RUB_1000, 100);
        map0.put(RUB_5000, 150);

        Map<BanknotesNominal, Integer> map1 = new HashMap<>();
        map1.put(RUB_50, 125);
        map1.put(RUB_100, 125);
        map1.put(RUB_200, 125);
        map1.put(RUB_500, 125);
        map1.put(RUB_1000, 100);

        Map<BanknotesNominal, Integer> map2 = new HashMap<>();
        map2.put(RUB_100, 200);
        map2.put(RUB_200, 200);
        map2.put(RUB_500, 200);
        map2.put(RUB_1000, 200);
        map2.put(RUB_2000, 150);

        Department department = new DepartmentImpl();

        Atm atm0 = new AtmImpl(map0, department);
        Atm atm1 = new AtmImpl(map1, department);
        Atm atm2 = new AtmImpl(map2, department);

        department.getSummaryBalance();

        Card owner = new Card("John Doe", 40_000);

        atm0.insertCard(owner);
        atm0.getAtmBalance();
        atm0.getCardBalance();
        atm0.giveCash(13300);
        atm0.getAtmBalance();
        atm0.getCardBalance();
        Map<BanknotesNominal, Integer> map3 = new TreeMap<>();
        map3.put(RUB_50, 10);
        map3.put(RUB_100, 10);
        map3.put(RUB_500, 10);
        map3.put(RUB_1000, 10);
        map3.put(RUB_5000, 10);
        atm0.acceptCash(map3);
        atm0.getAtmBalance();
        atm0.getCardBalance();
        atm0.ejectCard();

        department.getSummaryBalance();

        atm1.insertCard(owner);
        atm1.getAtmBalance();
        atm1.getCardBalance();
        atm1.giveCash(13300);
        atm1.getAtmBalance();
        atm1.getCardBalance();
        Map<BanknotesNominal, Integer> map4 = new TreeMap<>();
        map4.put(RUB_50, 10);
        map4.put(RUB_100, 10);
        map4.put(RUB_200, 10);
        map4.put(RUB_500, 10);
        map4.put(RUB_1000, 10);
        atm1.acceptCash(map4);
        atm1.getAtmBalance();
        atm1.getCardBalance();
        atm1.ejectCard();

        department.getSummaryBalance();

        atm2.insertCard(owner);
        atm2.getAtmBalance();
        atm2.getCardBalance();
        atm2.giveCash(13300);
        atm2.getAtmBalance();
        atm2.getCardBalance();
        Map<BanknotesNominal, Integer> map5 = new TreeMap<>();
        map5.put(RUB_100, 10);
        map5.put(RUB_200, 10);
        map5.put(RUB_500, 10);
        map5.put(RUB_1000, 10);
        map5.put(RUB_2000, 10);
        atm2.acceptCash(map5);
        atm2.getAtmBalance();
        atm2.getCardBalance();
        atm2.ejectCard();

        department.getSummaryBalance();
        department.restoreInitialCondition();
        department.getSummaryBalance();
    }
}
