package gameLogic;

import java.util.concurrent.ThreadLocalRandom;

public class Taxi extends Cell {
    int taxiDistance;

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public void cellAction(Player player) {
        taxiDistance = ThreadLocalRandom.current().nextInt(3, 6);
    }
}
