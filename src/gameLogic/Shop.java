package gameLogic;

public class Shop extends Cell {

    private int N; // Цена магазина.
    private int K; // Размер компенсации.

    private final double compensationCoeff;
    private final double improvementCoeff;

    private Player owner; // Владелец магзина.

    Shop(){
        N = getIntFromRange(50, 500);
        K = getIntFromRange((int)Math.round(0.5*N), (int)Math.round(0.9*N) + 1);
        compensationCoeff = getDoubleFromRange(0.1, 1);
        improvementCoeff = getDoubleFromRange(0.1, 2);
    }

    /**
     * Покупка магазина.
     * @param buyer Игрок, покупающий магазин.
     */
    void buy(String buyer){
        // TODO.
    }

    /**
     * Улучшение магазина.
     */
    void upgrade(){
        N += improvementCoeff * N;
        K += compensationCoeff * K;
    }

    @Override
    void cellAction(Player player) {
        // TODO.
    }

    @Override
    public String toString() {
        if (owner == null)
            return "S";
        if (owner instanceof Bot)
            return "O";
        return "M";
    }
}
