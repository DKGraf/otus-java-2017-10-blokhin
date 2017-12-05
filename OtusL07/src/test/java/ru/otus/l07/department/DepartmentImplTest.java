package ru.otus.l07.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.l07.atm.AtmImpl;
import ru.otus.l07.atm.BanknotesNominal;
import ru.otus.l07.interfaces.Atm;
import ru.otus.l07.interfaces.Department;
import ru.otus.l07.owner.Card;

import java.util.HashMap;
import java.util.Map;

import static ru.otus.l07.atm.BanknotesNominal.*;

class DepartmentImplTest {
    @Test
    void restoreInitialState() {
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

        Card owner = new Card("John Doe", 40_000);

        atm0.insertCard(owner);
        atm0.giveCash(13300);
        atm0.ejectCard();

        atm1.insertCard(owner);
        atm1.giveCash(13300);
        atm1.ejectCard();

        atm2.insertCard(owner);
        atm2.giveCash(13300);
        atm2.ejectCard();

        department.restoreInitialCondition();
        int totalBalance = department.getSummaryBalance();

        Assertions.assertEquals(1_741_350, totalBalance);
    }

    @Test
    void getSummaryBalance() {
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

        int totalBalance = department.getSummaryBalance();

        Assertions.assertEquals(1_781_250, totalBalance);
    }

}