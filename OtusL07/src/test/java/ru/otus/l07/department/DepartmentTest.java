package ru.otus.l07.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.l07.atm.Atm;
import ru.otus.l07.atm.BanknotesNominal;
import ru.otus.l07.owner.AccountOwner;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.l07.atm.BanknotesNominal.*;

class DepartmentTest {
    @Test
    void restoreInitialState() {
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

        AccountOwner owner = new AccountOwner("John Doe", 40_000);

        atm0.insertCard(owner);
        atm0.giveCash(13300);
        atm0.ejectCard();

        atm1.insertCard(owner);
        atm1.giveCash(13300);
        atm1.ejectCard();

        atm2.insertCard(owner);
        atm2.giveCash(13300);
        atm2.ejectCard();

        department.restoreInitialState();
        int totalBalance = department.getSummaryBalance();

        Assertions.assertEquals(3_877_500, totalBalance);
    }

    @Test
    void getSummaryBalance() {
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

        int totalBalance = department.getSummaryBalance();

        Assertions.assertEquals(3_877_500, totalBalance);
    }

}