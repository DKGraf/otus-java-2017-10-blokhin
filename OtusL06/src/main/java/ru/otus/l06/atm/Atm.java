package ru.otus.l06.atm;

import java.util.*;

/**
 * При приеме денег предполагается, что купюры
 * попадают в ячейки соответствующих номиналов и становятся
 * доступны для выдачи.
 */

public class Atm {
    private Map<Integer, Integer> banknotes;
    private ArrayList<Integer> banknotesNominal;
    private List<AccountOwner> owners;

    //В конструктор передается список купюр, количество
    //купюр(одинаковое для каждого номинала) и список владельцев счетов
    public Atm(ArrayList<Integer> banknotesNominal, int banknotesCount, List<AccountOwner> owners) {
        banknotes = new TreeMap<>();
        for (Integer nominal :
                banknotesNominal) {
            banknotes.put(nominal, banknotesCount);
        }
        Collections.sort(banknotesNominal);
        Collections.reverse(banknotesNominal);
        this.banknotesNominal = banknotesNominal;
        this.owners = owners;
    }

    public void giveCash(int sum, int ownerId) {
        if (!checkSum(sum, ownerId)) {
            System.out.println("Невозможно выдать запрошенную сумму.");
        } else {
            Map<Integer, Integer> packOfCash = calcCountOfBanknotes(sum);
            decreaseAtmBalance(packOfCash);
            decreaseOwnerBalance(sum, ownerId);
            System.out.println("\nВыдано:");
            showInfo(packOfCash);
        }
    }

    public void acceptCash(Map<Integer, Integer> packOfCash, int ownerId) {
        increaseAtmBalance(packOfCash);
        int sum = calcSumOfCash(packOfCash);
        increaseOwnerBalance(sum, ownerId);
        System.out.println("\nПринято:");
        showInfo(packOfCash);
    }

    private boolean checkSum(int sum, int ownerId) {
        int minNominal = banknotesNominal.get(banknotesNominal.size() - 1);
        if (sum % (minNominal) != 0) {
            System.out.println("Сумма должна быть кратна 50.");
            return false;
        } else if (totalCash() < sum) {
            System.out.println("Максимальная сумма для выдачи: " + totalCash());
            return false;
        } else if (owners.get(ownerId).getBalance() < sum) {
            System.out.println("Недостаточно средств на счете.");
            return false;
        } else {
            return true;
        }
    }

    private int totalCash() {
        int total = 0;
        for (Map.Entry<Integer, Integer> pair :
                banknotes.entrySet()) {
            total += pair.getKey() * pair.getValue();
        }
        return total;
    }

    private Map<Integer, Integer> calcCountOfBanknotes(int sum) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Integer nominal :
                banknotesNominal) {
            int expectedCount = sum / nominal;
            int realCount = banknoteCount(nominal, expectedCount);
            sum -= realCount * nominal;
            map.put(nominal, realCount);
        }
        return map;
    }

    private int banknoteCount(int nominal, int count) {
        if (banknotes.get(nominal) >= count) {
            return count;
        } else {
            return banknotes.get(nominal);
        }
    }

    private void showInfo(Map<Integer, Integer> packOfCash) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Integer> pair :
                packOfCash.entrySet()) {
            int count = pair.getValue();
            int nominal = pair.getKey();
            sb.append("\tбанкнот номиналом ")
                    .append(nominal)
                    .append(" - ")
                    .append(count)
                    .append(" шт.\n");
        }

        System.out.println(sb.toString());
    }

    private void decreaseAtmBalance(Map<Integer, Integer> packOfCash) {
        for (Map.Entry<Integer, Integer> pair :
                packOfCash.entrySet()) {
            banknotes.put(pair.getKey(), banknotes.get(pair.getKey()) - pair.getValue());
        }
    }

    private void increaseAtmBalance(Map<Integer, Integer> packOfCash) {
        for (Map.Entry<Integer, Integer> pair :
                packOfCash.entrySet()) {
            banknotes.put(pair.getKey(), banknotes.get(pair.getKey()) + pair.getValue());
        }
    }

    private void decreaseOwnerBalance(int sum, int ownerId) {
        int ownerBalance = owners.get(ownerId).getBalance();
        owners.get(ownerId).setBalance(ownerBalance - sum);
    }

    private void increaseOwnerBalance(int sum, int ownerId) {
        int ownerBalance = owners.get(ownerId).getBalance();
        owners.get(ownerId).setBalance(ownerBalance + sum);
    }

    private int calcSumOfCash(Map<Integer, Integer> packOfCash) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair :
                packOfCash.entrySet()) {
            sum += pair.getKey() * pair.getValue();
        }
        return sum;
    }

    public int getAtmBalance() {
        int balance = totalCash();
        System.out.println("Остаток денежных средств в банкомате: " + balance);
        return balance;
    }

    public int getOwnerBalance(int ownerId) {
        int balance = owners.get(ownerId).getBalance();
        System.out.println("Остаток денежных средств на счете " +
                owners.get(ownerId).getName() + ": " + balance);
        return balance;
    }
}