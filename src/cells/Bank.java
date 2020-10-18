package cells;

import players.*;

import java.util.HashMap;

/**
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public class Bank extends Cell {

    private final double creditCoeff;
    private final double debtCoeff;

    // Список должников.
    private HashMap<Player, Integer> debtorsList = new HashMap<>();

    Bank() {
        creditCoeff = getRandomDoubleFromRange(0.002, 0.2);
        debtCoeff = -getRandomDoubleFromRange(-3.0, -1.0);
    }

    /**
     * Выводит коэффиценты.
     */
    void displayCoeffs() {
        System.out.println("creditCoeff = " + creditCoeff);
        System.out.println("debtCoeff = " + debtCoeff);
    }

    /**
     * Взятие игроком кредита.
     *
     * @param player     Игрок.
     * @param loanAmount Размер кредита.
     */
    private void takeLoan(Player player, int loanAmount) {
        player.setMoney(player.getMoney() + loanAmount);

        debtorsList.put(player, (int) Math.round(debtCoeff * loanAmount));

        if (player instanceof RealPlayer)
            System.out.println("Loan successful! \n" +
                    "Your current fortune: " + player.getMoney() + "$.\n" +
                    "Your debt is: " + debtorsList.get(player) + "$.");
        else
            System.out.println("The bot has taken a loan! \n" +
                    "His current fortune: " + player.getMoney() + "$.\n" +
                    "His debt is: " + debtorsList.get(player) + "$.");
    }

    /**
     * Взыскивает долг у игрока.
     *
     * @param player Игрок.
     */
    private void payDebt(Player player) {
        player.setMoney(player.getMoney() - debtorsList.get(player));
        debtorsList.remove(player);

        if (player instanceof RealPlayer) {
            System.out.println("You have paid the debt.\n" +
                    "Your current fortune: " + player.getMoney() + "$.");
        } else {
            System.out.println("The bot has paid a debt.\n" +
                    "His current fortune: " + player.getMoney() + "$.");
        }
    }

    @Override
    public void cellAction(Player player) {
        super.cellAction(player);

        if (debtorsList.containsKey(player)) {
            if (player instanceof RealPlayer) {
                System.out.println("You owe this bank " + debtorsList.get(player) + "$.\n" +
                        "The amount will be taken from your wallet right now.");
            }
            payDebt(player);
        } else {
            loanDialog(player);
        }
    }

    /**
     * Диалог с игроком о взятии кредита.
     * @param player Игрок.
     */
    private void loanDialog(Player player) {
        // Максимальная сумма кредита.
        int maxLoan = (int) Math.round(creditCoeff * player.getLostMoney());

        if (maxLoan == 0) {
            if (player instanceof RealPlayer)
                System.out.println("You can't take money from this bank currently!\n" +
                        "To do that, you should first spend some money buying and upgrading shops.");
        } else {
            int playersChoice = chooseLoanOrNot(player, maxLoan);
            if (playersChoice == 1) {
                int loanAmount;

                if (player instanceof RealPlayer) {
                    System.out.println("Input the amount of money you want to loan:");
                    loanAmount = readInt(1, maxLoan + 1);
                } else {
                    loanAmount = getRandomIntFromRange(1, maxLoan + 1);
                }
                takeLoan(player, loanAmount);
            } else {
                if (player instanceof RealPlayer)
                    System.out.println("Have a nice day!");
            }
        }
    }

    /**
     * Игрок выбирает, взять ли кредит в банке.
     * @param player Игрок
     * @param maxLoan Максимальный размер кредита.
     * @return Выбор игрока (1 - взять кредит, 2 - нет).
     */
    private int chooseLoanOrNot(Player player, int maxLoan){
        int res;
        if (player instanceof RealPlayer) {
            System.out.println("You can take up to " + maxLoan + "$ from this bank!\n" +
                    "Input '1' if you would like to take a loan, '2' otherwise.");
            res = readInt(1, 2);
        } else {
            res = getRandomIntFromRange(1, 3);
        }
        return res;
    }

    @Override
    public String toString() {
        return "$";
    }
}
