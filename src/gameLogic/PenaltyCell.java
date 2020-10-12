package gameLogic;

public class PenaltyCell extends Cell {
    final double penaltyCoeff;

    public PenaltyCell() {
        penaltyCoeff = getDoubleFromRange(0.01, 0.1);
    }

    @Override
    public void cellAction(Player player) {
        player.sum -= penaltyCoeff * player.sum;
    }

    @Override
    public String toString() {
        return "%";
    }
}
