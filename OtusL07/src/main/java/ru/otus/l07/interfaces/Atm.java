package ru.otus.l07.interfaces;

import ru.otus.l07.atm.BanknotesNominal;
import ru.otus.l07.owner.Card;

import java.util.Map;

/**
 * Интерфейс, использумеый для реализации паттерна Atm.
 */

public interface Atm {
    void insertCard(Card card);

    void ejectCard();

    void giveCash(int sum);

    void acceptCash(Map<BanknotesNominal, Integer> packOfCash);

    int getAtmBalance();

    int getCardBalance();

    void restoreInitialCondition();
}
