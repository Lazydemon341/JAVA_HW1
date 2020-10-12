package gameLogic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Базовый класс для клеток на карте Монополии.
 */
public abstract class Cell {

    public abstract void cellAction(Player player);

    /**
     * Генерирует случайное дробное число
     * из промежутка [left; right].
     * @param left Левая граница.
     * @param right Правая граница.
     * @return дробное число из промежутка [left; right].
     */
    double getDoubleFromRange(double left, double right){
        double res = ThreadLocalRandom.current().nextDouble(left, right);

        if (res + Double.MIN_VALUE == right)
            res += ThreadLocalRandom.current().nextInt(0,2) == 0 ? Double.MIN_VALUE : 0;

        return res;
    }

    /**
     * Генерирует случайное целое число
     * из промежутка [left; right].
     * @param left Левая граница.
     * @param right Правая граница.
     * @return целое число из промежутка [left; right].
     */
    int getIntFromRange(int left, int right){
        return ThreadLocalRandom.current().nextInt(left, right + 1);
    }
}
