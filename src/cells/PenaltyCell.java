package cells;

import players.Player;
import players.RealPlayer;

/**
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public class PenaltyCell extends Cell {

    private final double penaltyCoeff;

    PenaltyCell(){
        penaltyCoeff = getRandomDoubleFromRange(0.01, 0.1);
    }

    /**
     * Выводит коэффицент.
     */
    void displayCoeff(){
        System.out.println("penaltyCoeff = " + penaltyCoeff);
    }

    @Override
    public void cellAction(Player player) {
        super.cellAction(player);

        int lostMoney = (int)Math.round(penaltyCoeff * player.getMoney());
        player.setMoney(player.getMoney() - lostMoney);

        if (player instanceof RealPlayer)
            System.out.println("You have lost " + lostMoney + "$.\n" +
                    "Your current fortune: " + player.getMoney() + "$.");
        else
            System.out.println("The bot has lost " + lostMoney + "$.\n" +
                    "His current fortune: " + player.getMoney() + "$.");

    }

    @Override
    public String toString() {
        return "%";
    }
}
