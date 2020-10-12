package gameLogic;

/**
 * Базовый класс для игроков в Монополию.
 */
public abstract class Player {

    protected double sum;

    protected int line; // Номер линии, на которой находится игрок.
    protected int cell; // Номер клетки.

    private final int width;
    private final int height;

    public Player(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Передвигает игрока вперед по карте.
     * @param cnt Кол-во клеток для передвижения.
     */
    void moveForward(int cnt) {
        //if (line % 2 == 0)
            //cell = (cell + cnt) %
        // TODO.
    }
}
