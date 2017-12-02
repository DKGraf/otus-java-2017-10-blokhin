package ru.otus.l07.department;

import ru.otus.l07.atm.Atm;
import ru.otus.l07.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс содержит в себе список всех банкоматов. Кроме того,
 * он может получать информацию о суммарном балансе всех банкоматов,
 * а также восстановить состояние всех банкоматов до изначального.
 */

public class Department implements Subject {
    private List<Atm> listOfAtm;

    public Department() {
        this.listOfAtm = new ArrayList<>();
    }

    @Override
    public void registerObserver(Atm atm) {
        listOfAtm.add(atm);
    }

    @Override
    public void removeObserver(Atm atm) {
        listOfAtm.remove(atm);
    }

    @Override
    public void notifyObservers() {
        for (Atm atm:
                listOfAtm) {
            atm.update();
        }
    }

    public void restoreInitialState() {
        notifyObservers();
    }

    public int getSummaryBalance() {
        int sum = 0;
        for (Atm atm:
                listOfAtm) {
            sum += atm.getAtmBalance();
        }
        System.out.println("Суммарный баланс всех банкоматов: " + sum);
        return sum;
    }
}
