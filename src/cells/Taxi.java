package cells;

import players.Player;
import players.RealPlayer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public class Taxi extends Cell {
    int taxiDistance;

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public void cellAction(Player player) {
        super.cellAction(player);

        taxiDistance = ThreadLocalRandom.current().nextInt(3, 6);
        if (player instanceof RealPlayer) {
            System.out.printf(
                    "You are shifted forward by %d cells%n",
                    taxiDistance);
        }
        else{
            System.out.printf(
                    "The bot is shifted forward by %d cells%n",
                    taxiDistance);
        }

        player.moveForward(taxiDistance);
    }
}
