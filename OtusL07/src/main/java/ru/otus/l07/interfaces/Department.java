package ru.otus.l07.interfaces;

import ru.otus.l07.atm.AtmImpl;

public interface Department {
    void registerAtm(AtmImpl atmImpl);

    void removeAtm(AtmImpl atmImpl);

    void restoreInitialCondition();

    int getSummaryBalance();
}
