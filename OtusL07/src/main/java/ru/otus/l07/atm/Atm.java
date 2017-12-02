package ru.otus.l07.atm;

import ru.otus.l07.interfaces.Observer;
import ru.otus.l07.interfaces.Subject;
import ru.otus.l07.owner.AccountOwner;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * При приеме денег предполагается, что купюры
 * попадают в ячейки соответствующих номиналов и становятся
 * доступны для выдачи.
 */

public class Atm implements Observer{
    private SortedMap<BanknotesNominal, Integer> banknotes;
    private AccountOwner owner;
    private Condition initialCondition;
    private Subject department;

    public Atm(List<BanknotesNominal> nominals, int banknotesCount, Subject department) {
        banknotes = new TreeMap<>((o1, o2) -> {
            int i1 = Integer.parseInt(o1.toString().substring(4));
            int i2 = Integer.parseInt(o2.toString().substring(4));
            return i2 - i1;
        });
        for (BanknotesNominal nominal :
                nominals) {
            banknotes.put(nominal, banknotesCount);
        }
        initialCondition = new Condition(nominals, banknotesCount);
        this.department = department;
        department.registerObserver(this);
    }

    public AccountOwner getOwner() {
        return owner;
    }

    public void insertCard(AccountOwner owner) {
        this.owner = owner;
    }

    public void ejectCard() {
        owner = null;
    }

    public void giveCash(int sum) {
        if (isCardInserted()) {
            if (!checkSum(sum)) {
                System.out.println("Невозможно выдать запрошенную сумму.");
            } else {
                Map<BanknotesNominal, Integer> packOfCash = calcCountOfBanknotes(sum);
                decreaseAtmBalance(packOfCash);
                decreaseOwnerBalance(sum);
                System.out.println("\nВыдано:");
                showInfo(packOfCash);
            }
        } else {
            System.out.println("Вставьте карту.");
        }
    }

    public void acceptCash(Map<BanknotesNominal, Integer> packOfCash) {
        if (isCardInserted()) {
            increaseAtmBalance(packOfCash);
            int sum = calcSumOfCash(packOfCash);
            increaseOwnerBalance(sum);
            System.out.println("\nПринято:");
            showInfo(packOfCash);
        } else {
            System.out.println("Вставьте карту.");
        }
    }

    public int getAtmBalance() {
        int balance = totalCash();
        System.out.println("Остаток денежных средств в банкомате: " + balance);
        return balance;
    }

    public int getOwnerBalance() {
        if (isCardInserted()) {
            int balance = owner.getBalance();
            System.out.println("Остаток денежных средств на счете " +
                    owner.getName() + ": " + balance);
            return balance;
        } else {
            System.out.println("Вставьте карту.");
            return -1;
        }
    }

    private boolean isCardInserted() {
        return owner != null;
    }

    private void decreaseAtmBalance(Map<BanknotesNominal, Integer> packOfCash) {
        for (Map.Entry<BanknotesNominal, Integer> pair :
                packOfCash.entrySet()) {
            banknotes.put(pair.getKey(), banknotes.get(pair.getKey()) - pair.getValue());
        }
    }

    private void increaseAtmBalance(Map<BanknotesNominal, Integer> packOfCash) {
        for (Map.Entry<BanknotesNominal, Integer> pair :
                packOfCash.entrySet()) {
            banknotes.put(pair.getKey(), banknotes.get(pair.getKey()) + pair.getValue());
        }
    }

    private void decreaseOwnerBalance(int sum) {
        int ownerBalance = owner.getBalance();
        owner.setBalance(ownerBalance - sum);
    }

    private void increaseOwnerBalance(int sum) {
        int ownerBalance = owner.getBalance();
        owner.setBalance(ownerBalance + sum);
    }

    private boolean checkSum(int sum) {
        int minNominal = getNominal(banknotes.lastKey());
        if (sum % (minNominal) != 0) {
            System.out.println("Сумма должна быть кратна " + minNominal + ".");
            return false;
        } else if (totalCash() < sum) {
            System.out.println("Максимальная сумма для выдачи: " + totalCash());
            return false;
        } else if (owner.getBalance() < sum) {
            System.out.println("Недостаточно средств на счете.");
            return false;
        } else {
            return true;
        }
    }

    private int totalCash() {
        int total = 0;
        for (Map.Entry<BanknotesNominal, Integer> pair :
                banknotes.entrySet()) {
            total += getNominal(pair.getKey()) * pair.getValue();
        }
        return total;
    }

    private int getNominal(BanknotesNominal nominal) {
        String stringNominal = nominal.toString();
        String nominalValue = stringNominal.substring(4, stringNominal.length());
        return Integer.parseInt(nominalValue);
    }

    private int calcSumOfCash(Map<BanknotesNominal, Integer> packOfCash) {
        int sum = 0;
        for (Map.Entry<BanknotesNominal, Integer> pair :
                packOfCash.entrySet()) {
            sum += getNominal(pair.getKey()) * pair.getValue();
        }
        return sum;
    }

    private Map<BanknotesNominal, Integer> calcCountOfBanknotes(int sum) {
        Map<BanknotesNominal, Integer> map = new TreeMap<>();
        for (Map.Entry<BanknotesNominal, Integer> pair :
                banknotes.entrySet()) {
            BanknotesNominal banknotesNominal = pair.getKey();
            int nominal = getNominal(banknotesNominal);
            int expectedCount = sum / nominal;
            int realCount = banknoteCount(banknotesNominal, expectedCount);
            sum -= realCount * nominal;
            map.put(banknotesNominal, realCount);
        }
        return map;
    }

    private int banknoteCount(BanknotesNominal nominal, int count) {
        if (banknotes.get(nominal) >= count) {
            return count;
        } else {
            return banknotes.get(nominal);
        }
    }

    private void showInfo(Map<BanknotesNominal, Integer> packOfCash) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<BanknotesNominal, Integer> pair :
                packOfCash.entrySet()) {
            int count = pair.getValue();
            int nominal = getNominal(pair.getKey());
            sb.append("\tбанкнот номиналом ")
                    .append(nominal)
                    .append(" - ")
                    .append(count)
                    .append(" шт.\n");
        }

        System.out.println(sb.toString());
    }

    private void restoreInitialCondition() {
        List<BanknotesNominal> nominals = initialCondition.getNominals();
        int count = initialCondition.getCount();
        banknotes = new TreeMap<>((o1, o2) -> {
            int i1 = Integer.parseInt(o1.toString().substring(4));
            int i2 = Integer.parseInt(o2.toString().substring(4));
            return i2 - i1;
        });
        for (BanknotesNominal nominal :
                nominals) {
            banknotes.put(nominal, count);
        }
    }

    @Override
    public void update() {
        restoreInitialCondition();
    }
}