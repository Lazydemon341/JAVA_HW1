package players;

import cells.Map;

/**
 * Базовый класс для игроков в Монополию.
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public abstract class Player {

    protected int money; // Деньги игрока.
    protected int lostMoney; // Потраченные на магазины деньги.

    Map map; // Карта игры.

    protected int line; // Номер линии, на которой находится игрок.
    protected int cell; // Номер клетки.

    public Player(Map map, int money) {
        if (money < 500 || money > 15000)
            throw new IllegalArgumentException("Players' money should be in [500; 15000] range!");

        this.map = map;
        this.money = money;
    }

    /**
     * Передвигает игрока вперед по карте.
     *
     * @param cnt Кол-во клеток для передвижения.
     */
    public void moveForward(int cnt) {

        // Длина линии, на которой на данный момент находится игрок.
        int lengthOfLine = line % 2 == 0 ? map.getWidth() : map.getHeight();

        // Игрок еремещается на следующую линию.
        if (cell + cnt > lengthOfLine - 2) {
            line = (line + 1) % 4;
            cell = (cell + cnt) % (lengthOfLine - 1);
        }
        // Остается на той же.
        else {
            cell += cnt;
        }

        // Взаимодействие с клеткой, на которую попал игрок.
        map.getCells()[line][cell].cellAction(this);
    }

    /**
     * Возвращает кол-во денег у игрока.
     *
     * @return Деньги игркоа.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Задает кол-во денег игрока.
     *
     * @param value Новое кол-во денег игрока.
     */
    public void setMoney(int value) {
        money = value;
    }

    public int getLostMoney(){
        return lostMoney;
    }

    public void addLostMoney(int value){
        lostMoney += value;
    }

    public String getPosition() {
        return String.format("[%d; %d].", line, cell);
    }
}
