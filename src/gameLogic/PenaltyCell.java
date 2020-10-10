package gameLogic;

public class PenaltyCell extends Cell{
    final double penaltyCoeff;

    @Override
    public String toString() {
        return "%";
    }

    public PenaltyCell(){
        penaltyCoeff = getDoubleFromRange(0.01, 0.1);
    }
}
