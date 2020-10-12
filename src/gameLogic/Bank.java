package gameLogic;

public class Bank extends Cell {

    private final double creditCoeff;
    private final double debtCoeff;

    public Bank(){
        creditCoeff = getDoubleFromRange(0.002, 0.2);
        debtCoeff = getDoubleFromRange(1.0, 3.0);
    }

    @Override
    public void cellAction(Player player) {

    }

    @Override
    public String toString() {
        return "$";
    }
}
