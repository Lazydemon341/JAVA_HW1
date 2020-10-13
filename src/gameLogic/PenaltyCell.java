package gameLogic;

public class PenaltyCell extends Cell {
    private final double penaltyCoeff;

    PenaltyCell() {
        penaltyCoeff = getDoubleFromRange(0.01, 0.1);
    }

    void displayCoeff(){
        System.out.println("penaltyCoeff = " + penaltyCoeff);
    }

    @Override
    void cellAction(Player player) {
        player.sum -= penaltyCoeff * player.sum;
    }

    @Override
    public String toString() {
        return "%";
    }
}
