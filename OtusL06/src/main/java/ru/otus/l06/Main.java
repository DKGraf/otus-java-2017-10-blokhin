package ru.otus.l06;

import ru.otus.l06.atm.AccountOwner;
import ru.otus.l06.atm.Atm;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> nominals = new ArrayList<>();
        nominals.add(50);
        nominals.add(100);
        nominals.add(500);
        nominals.add(1000);
        nominals.add(5000);

        Atm atm = new Atm(nominals, 100);
        atm.insertCard(new AccountOwner("John Doe", 40_000));

        atm.getAtmBalance();
        atm.getOwnerBalance();
        atm.giveCash(13300);
        atm.getAtmBalance();
        atm.getOwnerBalance();

        Map<Integer, Integer> map = new TreeMap<>();
        map.put(50, 10);
        map.put(100, 10);
        map.put(500, 10);
        map.put(1000, 10);
        map.put(5000, 10);

        atm.acceptCash(map);
        atm.getAtmBalance();
        atm.getOwnerBalance();

        atm.ejectCard();
    }
}
