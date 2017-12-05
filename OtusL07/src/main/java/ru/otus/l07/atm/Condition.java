package ru.otus.l07.atm;

import java.util.Map;

/**
 * Данный класс предназначен для хранения начального состояния банкомата:
 * списка номиналов загруженных в него купюр и их количества.
 * Используется для реализации паттерна Memento.
 */

class Condition {
    private Map<BanknotesNominal, Integer> cash;

    Condition(Map<BanknotesNominal, Integer> cash) {
        this.cash = cash;
    }

    Map<BanknotesNominal, Integer> getCondition() {
        return cash;
    }
}
