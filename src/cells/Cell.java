package cells;

import players.Player;
import players.RealPlayer;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Базовый класс для клеток на карте Монополии.
 *
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public abstract class Cell {

    protected static final Scanner in;

    static {
        in = new Scanner(System.in);
    }

    /**
     * Генерирует случайное дробное число
     * из промежутка [left; right].
     *
     * @param left  Левая граница.
     * @param right Правая граница.
     * @return дробное число из промежутка [left; right].
     */
    protected double getRandomDoubleFromRange(double left, double right) {
        return ThreadLocalRandom.current().nextDouble(left, right);
    }

    /**
     * Генерирует случайное целое число
     * из промежутка [left; right].
     *
     * @param left  Левая граница.
     * @param right Правая граница.
     * @return целое число из промежутка [left; right].
     */
    protected int getRandomIntFromRange(int left, int right) {
        return ThreadLocalRandom.current().nextInt(left, right);
    }

    /**
     * Получает от пользователя целое число
     * из промежутка [left; right].
     *
     * @param left  Левая граница.
     * @param right Правая граница.
     * @return целое число из промежутка [left; right].
     */
    protected int readInt(int left, int right) {
        int res;

        while (true) {
            try {
                res = Integer.parseInt(in.next());
                if (res < left || res > right) {
                    throw new IllegalArgumentException();
                }
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("You should input an integer number! Please try again:");
            }
            catch (RuntimeException ex) {
                System.out.println("Incorrect input! Please try again:");
            }
        }
        return res;
    }

    /**
     * Взаимодействие игрока с клеткой.
     *
     * @param player Игрок, находящийся на этой клетке.
     */
    public void cellAction(Player player) {
        if (player instanceof RealPlayer)
            System.out.println("You are in the " + this.getClass().getSimpleName() +
                    " " + player.getPosition());
        else
            System.out.println("The bot is in the " + this.getClass().getSimpleName() +
                    " " + player.getPosition());
    }
}
