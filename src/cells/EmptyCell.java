package cells;

import players.Player;
import players.RealPlayer;

/**
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public class EmptyCell extends Cell {

    @Override
    public void cellAction(Player player) {
        super.cellAction(player);

        if (player instanceof RealPlayer)
            System.out.println("Just relax here");
    }

    @Override
    public String toString() {
        return "E";
    }
}
