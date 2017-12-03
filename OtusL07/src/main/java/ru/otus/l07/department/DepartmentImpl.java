package ru.otus.l07.department;

import ru.otus.l07.atm.AtmImpl;
import ru.otus.l07.interfaces.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс содержит в себе список всех банкоматов. Кроме того,
 * он может получать информацию о суммарном балансе всех банкоматов,
 * а также восстановить состояние всех банкоматов до изначального.
 */

public class DepartmentImpl implements Department {
    private List<AtmImpl> listOfAtmImpl;

    public DepartmentImpl() {
        this.listOfAtmImpl = new ArrayList<>();
    }

    @Override
    public void registerAtm(AtmImpl atmImpl) {
        listOfAtmImpl.add(atmImpl);
    }

    @Override
    public void removeAtm(AtmImpl atmImpl) {
        listOfAtmImpl.remove(atmImpl);
    }

    @Override
    public void restoreInitialCondition() {
        for (AtmImpl atmImpl :
                listOfAtmImpl) {
            atmImpl.restoreInitialCondition();
        }
    }

    public int getSummaryBalance() {
        int sum = 0;
        for (AtmImpl atmImpl :
                listOfAtmImpl) {
            sum += atmImpl.getAtmBalance();
        }
        System.out.println("Суммарный баланс всех банкоматов: " + sum);
        return sum;
    }
}
