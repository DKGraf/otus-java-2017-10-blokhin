package ru.otus.l07.interfaces;

import ru.otus.l07.atm.Atm;

/**
 * Интерфейс, использумеый для реализации паттерна Observer.
 */

public interface Subject {
    void registerObserver(Atm atm);
    void removeObserver(Atm atm);
    void notifyObservers();
}
