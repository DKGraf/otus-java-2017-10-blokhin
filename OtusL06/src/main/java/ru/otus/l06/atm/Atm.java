package ru.otus.l06.atm;

/**
 * Для простоты количество банкнот каждого типа захардкожено.
 * Будем считать, что в банкомат загружено по 100 купюр каждого
 * из 5 номиналов. При приеме денег предполагается, что купюры
 * попадают в ячейки соответствующих номиналов и становятся
 * доступны для выдачи.
 */

public class Atm {
    private int count_50 = 100;
    private int count_100 = 100;
    private int count_500 = 100;
    private int count_1000 = 100;
    private int count_5000 = 100;
    private AccountOwner owner;

    public Atm() {
        owner = new AccountOwner();
    }

    public void giveCash(int sum) {
        if (!checkSum(sum)) {
            System.out.println("Невозможно выдать запрошенную сумму.");
        } else {
            int[] packOfCash = calcCountOfBanknotes(sum);
            decreaseAtmBalance(packOfCash);
            decreaseOwnerBalance(sum);
            showInfo(packOfCash);
        }
    }

    public void acceptCash(int countOf50, int countOf100, int countOf500, int countOf1000, int countOf5000) {
        int[] packOfCash = new int[]{countOf50, countOf100, countOf500, countOf1000, countOf5000};
        increaseAtmBalance(packOfCash);
        int sum = calcSumOfCash(packOfCash);
        increaseOwnerBalance(sum);
    }

    private boolean checkSum(int sum) {
        if (sum % 50 != 0) {
            System.out.println("Сумма должна быть кратна 50.");
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
        return (count_50 * 50) +
                (count_100 * 100) +
                (count_500 * 500) +
                (count_1000 * 1000) +
                (count_5000 * 5000);
    }

    private int[] calcCountOfBanknotes(int sum) {
        int countOf5000 = sum / 5000;
        sum -= countOf5000 * 5000;
        int countOf1000 = sum / 1000;
        sum -= countOf1000 * 1000;
        int countOf500 = sum / 500;
        sum -= countOf500 * 500;
        int countOf100 = sum / 100;
        sum -= countOf100 * 100;
        int countOf50 = sum / 50;
        return new int[]{countOf50, countOf100, countOf500, countOf1000, countOf5000};
    }

    private void showInfo(int[] packOfCash) {
        String info = "Выдано:\n" +
                "\tбанкнот номиналом 50 - " +
                packOfCash[0] +
                "\n\tбанкнот номиналом 100 - " +
                packOfCash[1] +
                "\n\tбанкнот номиналом 500 - " +
                packOfCash[2] +
                "\n\tбанкнот номиналом 1000 - " +
                packOfCash[3] +
                "\n\tбанкнот номиналом 5000 - " +
                packOfCash[4];
        System.out.println(info);
    }

    private void decreaseAtmBalance(int[] packOfCash) {
        count_50 -= packOfCash[0];
        count_100 -= packOfCash[1];
        count_500 -= packOfCash[2];
        count_1000 -= packOfCash[3];
        count_5000 -= packOfCash[4];
    }

    private void increaseAtmBalance(int[] packOfCash) {
        count_50 += packOfCash[0];
        count_100 += packOfCash[1];
        count_500 += packOfCash[2];
        count_1000 += packOfCash[3];
        count_5000 += packOfCash[4];
    }

    private void decreaseOwnerBalance(int sum) {
        owner.setBalance((owner.getBalance() - sum));
    }

    private void increaseOwnerBalance(int sum) {
        owner.setBalance(owner.getBalance() + sum);
    }

    private int calcSumOfCash(int[] packOfCash) {
        return (packOfCash[0] * 50) +
                (packOfCash[1] * 100) +
                (packOfCash[2] * 500) +
                (packOfCash[3] * 1000) +
                (packOfCash[4] * 5000);
    }

    public int getAtmBalance() {
        System.out.println("Остаток денежных средств в банкомате: " + totalCash());
        return totalCash();
    }

    public int getOwnerBalance() {
        System.out.println("Остаток денежных средств на вашем счете: " + owner.getBalance());
        return owner.getBalance();
    }
}
