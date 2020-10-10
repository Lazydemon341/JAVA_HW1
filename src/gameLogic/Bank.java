package gameLogic;

public class Bank extends Cell {
    final double creditCoeff;

    @Override
    public String toString() {
        return "$";
    }

    final double debtCoeff;

    public Bank(){
        creditCoeff = getDoubleFromRange(0.002, 0.2);
        debtCoeff = getDoubleFromRange(1.0, 3.0);
    }
}
