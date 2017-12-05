package ru.otus.l07.atm;

import ru.otus.l07.interfaces.Atm;
import ru.otus.l07.interfaces.Department;
import ru.otus.l07.owner.Card;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * При приеме денег предполагается, что купюры
 * попадают в ячейки соответствующих номиналов и становятся
 * доступны для выдачи.
 */

public class AtmImpl implements Atm {
    private static int atmCount = 0;
    private int atmNumber = 0;
    private SortedMap<BanknotesNominal, Integer> banknotes;
    private Card card;
    private Condition initialCondition;
    private Department department;

    public AtmImpl(Map<BanknotesNominal, Integer> cash, Department department) {
        ++atmCount;
        atmNumber = atmCount;
        banknotes = new TreeMap<>((o1, o2) -> {
            int i1 = Integer.parseInt(o1.toString().substring(4));
            int i2 = Integer.parseInt(o2.toString().substring(4));
            return i2 - i1;
        });
        for (Map.Entry<BanknotesNominal, Integer> pair :
                cash.entrySet()) {
            banknotes.put(pair.getKey(), pair.getValue());
        }
        initialCondition = new Condition(banknotes);
        this.department = department;
        department.registerAtm(this);
    }

    @Override
    public void insertCard(Card card) {
        this.card = card;
    }

    @Override
    public void ejectCard() {
        card = null;
    }

    @Override
    public void giveCash(int sum) {
        if (isCardInserted()) {
            if (!checkSum(sum)) {
                System.out.println("Невозможно выдать запрошенную сумму.");
            } else {
                Map<BanknotesNominal, Integer> packOfCash = calcCountOfBanknotes(sum);
                decreaseAtmBalance(packOfCash);
                card.decreaseBalance(sum);
                System.out.println("\nВыдано:");
                showInfo(packOfCash);
            }
        } else {
            System.out.println("Вставьте карту.");
        }
    }

    @Override
    public void acceptCash(Map<BanknotesNominal, Integer> packOfCash) {
        if (isCardInserted()) {
            increaseAtmBalance(packOfCash);
            int sum = calcSumOfCash(packOfCash);
            card.increaseBalance(sum);
            System.out.println("\nПринято:");
            showInfo(packOfCash);
        } else {
            System.out.println("Вставьте карту.");
        }
    }

    @Override
    public int getAtmBalance() {
        int balance = totalCash();
        System.out.println("Остаток денежных средств в банкомате № " + atmNumber + ": " + balance);
        return balance;
    }

    @Override
    public int getCardBalance() {
        if (isCardInserted()) {
            int balance = card.getBalance();
            System.out.println("Остаток денежных средств на счете " +
                    card.getOwnerName() + ": " + balance);
            return balance;
        } else {
            System.out.println("Вставьте карту.");
            return -1;
        }
    }

    private boolean isCardInserted() {
        return card != null;
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

    private boolean checkSum(int sum) {
        int minNominal = getNominal(banknotes.lastKey());
        if (sum % (minNominal) != 0) {
            System.out.println("Сумма должна быть кратна " + minNominal + ".");
            return false;
        } else if (totalCash() < sum) {
            System.out.println("Максимальная сумма для выдачи: " + totalCash());
            return false;
        } else if (card.getBalance() < sum) {
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
        return nominal.getNominal();
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

    @Override
    public void restoreInitialCondition() {
        Map<BanknotesNominal, Integer> condition = initialCondition.getCondition();
        banknotes = new TreeMap<>((o1, o2) -> {
            int i1 = Integer.parseInt(o1.toString().substring(4));
            int i2 = Integer.parseInt(o2.toString().substring(4));
            return i2 - i1;
        });
        for (Map.Entry<BanknotesNominal, Integer> pair :
                condition.entrySet()) {
            banknotes.put(pair.getKey(), pair.getValue());
        }
    }
}