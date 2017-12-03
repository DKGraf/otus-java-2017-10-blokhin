package ru.otus.l07.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.l07.atm.AtmImpl;
import ru.otus.l07.atm.BanknotesNominal;
import ru.otus.l07.interfaces.Atm;
import ru.otus.l07.interfaces.Department;
import ru.otus.l07.owner.AccountOwner;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.l07.atm.BanknotesNominal.*;

class DepartmentImplTest {
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

        Department department = new DepartmentImpl();

        AtmImpl atmImpl0 = new AtmImpl(list0, 200, department);
        AtmImpl atmImpl1 = new AtmImpl(list1, 350, department);
        AtmImpl atmImpl2 = new AtmImpl(list2, 500, department);

        AccountOwner owner = new AccountOwner("John Doe", 40_000);

        atmImpl0.insertCard(owner);
        atmImpl0.giveCash(13300);
        atmImpl0.ejectCard();

        atmImpl1.insertCard(owner);
        atmImpl1.giveCash(13300);
        atmImpl1.ejectCard();

        atmImpl2.insertCard(owner);
        atmImpl2.giveCash(13300);
        atmImpl2.ejectCard();

        department.restoreInitialCondition();
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

        Department department = new DepartmentImpl();

        Atm atm0 = new AtmImpl(list0, 200, department);
        Atm atm1 = new AtmImpl(list1, 350, department);
        Atm atm2 = new AtmImpl(list2, 500, department);

        int totalBalance = department.getSummaryBalance();

        Assertions.assertEquals(3_877_500, totalBalance);
    }

}