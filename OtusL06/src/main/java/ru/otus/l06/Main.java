package ru.otus.l06;

import ru.otus.l06.atm.AccountOwner;
import ru.otus.l06.atm.Atm;

import java.util.ArrayList;
import java.util.List;
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

        List<AccountOwner> owners = new ArrayList<>();
        owners.add(new AccountOwner("John Doe", 40_000));
        owners.add(new AccountOwner("Jane Doe", 20_000));
        Atm atm = new Atm(nominals, 100, owners);

        atm.getAtmBalance();
        atm.getOwnerBalance(0);
        atm.getOwnerBalance(1);
        atm.giveCash(13300, 0);
        atm.getAtmBalance();
        atm.getOwnerBalance(0);

        Map<Integer, Integer> map = new TreeMap<>();
        map.put(50, 10);
        map.put(100, 10);
        map.put(500, 10);
        map.put(1000, 10);
        map.put(5000, 10);

        atm.acceptCash(map, 0);
        atm.getAtmBalance();
        atm.getOwnerBalance(0);
    }
}
