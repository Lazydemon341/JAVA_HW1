package gameLogic;

public class Bank extends Cell {

    private final double creditCoeff;
    private final double debtCoeff;

    Bank(){
        creditCoeff = getDoubleFromRange(0.002, 0.2);
        debtCoeff = getDoubleFromRange(1.0, 3.0);
    }

    void displayCoeffs(){
        System.out.println("creditCoeff = " + creditCoeff);
        System.out.println("creditCoeff = " + debtCoeff);
    }

    @Override
    void cellAction(Player player) {

    }

    @Override
    public String toString() {
        return "$";
    }
}
