package ru.otus.l07.interfaces;

import ru.otus.l07.atm.AtmImpl;

/**
 * Интерфейс, использумеый для реализации паттерна Atm.
 */

public interface Department {
    void registerAtm(AtmImpl atmImpl);

    void removeAtm(AtmImpl atmImpl);

    void restoreInitialCondition();

    int getSummaryBalance();
}
