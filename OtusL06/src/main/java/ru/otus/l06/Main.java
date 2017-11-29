package ru.otus.l06;

import ru.otus.l06.atm.AccountOwner;
import ru.otus.l06.atm.Atm;
import ru.otus.l06.atm.BanknotesNominal;

import java.util.Map;
import java.util.TreeMap;

import static ru.otus.l06.atm.BanknotesNominal.*;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm(100);
        atm.insertCard(new AccountOwner("John Doe", 40_000));

        atm.getAtmBalance();
        atm.getOwnerBalance();
        atm.giveCash(13300);
        atm.getAtmBalance();
        atm.getOwnerBalance();

        Map<BanknotesNominal, Integer> map = new TreeMap<>();
        map.put(RUB_50, 10);
        map.put(RUB_100, 10);
        map.put(RUB_500, 10);
        map.put(RUB_1000, 10);
        map.put(RUB_5000, 10);

        atm.acceptCash(map);
        atm.getAtmBalance();
        atm.getOwnerBalance();

        atm.ejectCard();
    }
}
