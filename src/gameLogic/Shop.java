package gameLogic;

public class Shop extends Cell {

    int N; // Цена магазина.
    int K; // Размер компенсации.

    final double compensationCoeff;
    final double improvementCoeff;

    Player owner; // Владелец магзина.

    public Shop(){
        N = getIntFromRange(50, 500);
        K = getIntFromRange((int)Math.round(0.5*N), (int)Math.round(0.9*N) + 1);
        compensationCoeff = getDoubleFromRange(0.1, 1);
        improvementCoeff = getDoubleFromRange(0.1, 2);
    }

    /**
     * Покупка магазина.
     * @param buyer Игрок, покупающий магазин.
     */
    public void buy(String buyer){
        // TODO.
    }

    /**
     * Улучшение магазина.
     */
    public void upgrade(){
        N += improvementCoeff * N;
        K += compensationCoeff * K;
    }

    @Override
    public void cellAction(Player player) {
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
