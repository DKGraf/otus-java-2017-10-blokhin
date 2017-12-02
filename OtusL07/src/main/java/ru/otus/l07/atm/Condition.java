package ru.otus.l07.atm;

import java.util.List;

/**
 * Данный класс предназначен для хранения начального состояния банкомата:
 * списка номиналов загруженных в него купюр и их количества.
 * Используется для реализации паттерна Memento.
 */

class Condition {
    private List<BanknotesNominal> nominals;
    private int count;

    Condition(List<BanknotesNominal> nominals, int count) {
        this.nominals = nominals;
        this.count = count;
    }

    List<BanknotesNominal> getNominals() {
        return nominals;
    }

    int getCount() {
        return count;
    }
}
