package gameLogic;


public class Shop extends Cell {

    int N; // Цена магазина.
    int K; // Размер компенсации.
    final double compensationCoeff;
    final double improvementCoeff;

    @Override
    public String toString() {
        return "S";
    }

    public Shop(){
        N = getIntFromRange(50, 500);
        K = getIntFromRange((int)Math.round(0.5*N), (int)Math.round(0.9*N) + 1);
        compensationCoeff = getDoubleFromRange(0.1, 1);
        improvementCoeff = getDoubleFromRange(0.1, 2);
    }

    public void upgrade(){
        N += improvementCoeff * N;
        K += compensationCoeff * K;
    }
}
