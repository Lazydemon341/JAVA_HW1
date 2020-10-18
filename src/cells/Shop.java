package cells;

import players.Bot;
import players.Player;
import players.RealPlayer;

/**
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public class Shop extends Cell {

    private int N; // Цена магазина.
    private int K; // Размер компенсации.

    private final double compensationCoeff;
    private final double improvementCoeff;

    private Player owner; // Владелец магзина.

    Shop() {
        N = getRandomIntFromRange(50, 501);
        K = getRandomIntFromRange((int) Math.round(0.5 * N), (int) Math.round(0.9 * N) + 1);
        compensationCoeff = getRandomDoubleFromRange(0.1, 1);
        improvementCoeff = getRandomDoubleFromRange(0.1, 2);
    }

    /**
     * Покупка магазина.
     *
     * @param player Игрок, покупающий магазин.
     */
    void purchase(Player player) {
        if (player.getMoney() < N) {
            if (player instanceof RealPlayer)
                System.out.println("You haven't got enough money to buy this shop! " +
                        "You only have " + player.getMoney() + "$");
            return;
        }
        player.setMoney(player.getMoney() - N);
        player.addLostMoney(N);
        owner = player;

        if (player instanceof RealPlayer) {
            System.out.println("Purchase successful! " +
                    "Your current fortune: " + player.getMoney() + "$");
        } else {
            System.out.println("The bot has bought the shop! " +
                    "His current fortune: " + player.getMoney() + "$");
        }
    }

    /**
     * Улучшение магазина.
     */
    void upgrade(Player player) {
        if (player.getMoney() < improvementCoeff * N) {
            if (player instanceof RealPlayer)
                System.out.println("You haven't got enough money to upgrade this shop! " +
                        "You only have " + player.getMoney() + "$");
            return;
        }
        int upgradeCost = (int)Math.round(improvementCoeff * N);
        player.setMoney(player.getMoney() - upgradeCost);
        player.addLostMoney(upgradeCost);

        N += Math.round(improvementCoeff * N);
        K += Math.round(compensationCoeff * K);

        if (player instanceof RealPlayer) {
            System.out.println("Upgrade successful! " +
                    "Your current fortune: " + player.getMoney() + "$");
        } else {
            System.out.println("The bot has upgraded the shop! " +
                    "His current fortune: " + player.getMoney() + "$");
        }
    }

    @Override
    public void cellAction(Player player) {
        super.cellAction(player);

        if (owner == null) {
            noOwnerDialog(player);
        }
        else {
            hasOwnerDialog(player);
        }
    }

    /**
     * Диалог игрока с магазином, которым никто не владеет.
     * @param player Игрок.
     */
    private void noOwnerDialog(Player player){
        int playersChoice;

        if (player instanceof RealPlayer) {
            System.out.println("This shop has no owner.\n" +
                    "Would you like to buy it for " + N + "$? Type '1' if you agree, '2' otherwise:");
            playersChoice = readInt(1, 2);
        } else {
            playersChoice = getRandomIntFromRange(1, 3);
        }
        if (playersChoice == 1)
            purchase(player);
        else{
            if (player instanceof RealPlayer)
                System.out.println("Have a nice day!");
        }
    }

    /**
     * Диалог игрока с магазином, у которого есть владелец.
     * @param player Игрок.
     */
    private void hasOwnerDialog(Player player){
        int playersChoice;

        // Игрок находящийся в магазине является его владельцем.
        if (owner.getClass().equals(player.getClass())) {
            if (player instanceof RealPlayer) {
                System.out.println("You are the owner of this shop. " +
                        "Would you like to upgrade it for " + Math.round(improvementCoeff * N) + "$? " +
                        "Type '1' if you agree, '2' otherwise:");
                playersChoice = readInt(1, 2);
            } else {
                playersChoice = getRandomIntFromRange(1, 3);
            }
            if (playersChoice == 1)
                upgrade(player);
        }
        // Магазином владеет другой игрок.
        else {
            if (player instanceof RealPlayer) {
                System.out.println("The bot is the owner of this shop. " +
                        "You have to pay " + K + "$.");
            }
            // Выплата долга.
            owner.setMoney(owner.getMoney() + K);
            player.setMoney(player.getMoney() - K);
        }
    }

    @Override
    public String toString() {
        if (owner == null)
            return "S";
        if (owner instanceof Bot)
            return "O";
        return "M";
    }
}
