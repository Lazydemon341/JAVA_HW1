package cells;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public class Map {

    final int width;
    final int height;

    final Cell[][] cells = new Cell[4][];

    public Map(int width, int height) {
        // Проверка на корректность
        if (width < 6 || width > 30 || height < 6 || height > 30) {
            throw new IllegalArgumentException("Height and width should be in [6; 30] range!");
        }
        this.width = width;
        this.height = height;

        generateMap();
    }

    /**
     * Возвращает ширину карты.
     *
     * @return Ширина карты.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Возвращает высоту карты.
     *
     * @return Высота карты.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Возвращает клетки карты.
     *
     * @return Массив клеток карты.
     */
    public Cell[][] getCells() {
        return this.cells;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        // Номера клеток сверху.
        res.append("\n ");
        for (int i = 0; i < cells[0].length; i++) {
            addNumber(res, i);
        }
        res.append("  ").append("\n ");

        // Верхняя горизонтальная линия.
        for (int i = 0; i < cells[0].length; i++) {
            res.append("  ").append(cells[0][i].toString()).append(" ");
        }
        res.append("  ").append(cells[1][0]).append(" 0\n");

        // Вертикальные линии.
        getVerticalLines(res);

        // Нижняя горизонтальная линия.
        res.append(" " + 0 + " ").append(cells[3][0] + " ");
        for (int i = 0; i < cells[2].length; i++) {
            res.append("  " + cells[2][cells[2].length - i - 1].toString() + " ");
        }
        res.append("\n ");

        // Номера нижних клеток.
        res.append(" ".repeat(4));
        for (int i = 1; i < cells[0].length; i++) {
            addNumber(res, width - i - 1);
        }
        res.append("  0");

        return res.toString();
    }

    /**
     * Добавляет в строку вертикальные линии карты.
     *
     * @param str Строка.
     */
    private void getVerticalLines(StringBuilder str) {
        for (int i = 1; i < cells[1].length; i++) {

            // Номера клеток.
            int tmp = height - i - 1;
            if (tmp < 10)
                str.append(" " + (height - i - 1)).append(" ");
            else
                str.append(height - i - 1).append(" ");

            str.append(cells[3][cells[3].length - i]);

            // Внутренняя (пустая) часть карты.
            str.append(" ".repeat(Math.max(0, 4 * cells[0].length - 1)));

            str.append(cells[1][i] + " ");
            str.append(i + "\n");
        }
    }

    /**
     * Добавляет в строку число через 2 пробела от предыдущего.
     *
     * @param str Строка.
     * @param num Число.
     */
    private void addNumber(StringBuilder str, int num) {
        if (num < 10)
            str.append("  " + num + " ");
        else
            str.append("  " + num);
    }


    /**
     * Заполняет карту клетками.
     */
    private void generateMap() {

        Bank bank = new Bank();
        PenaltyCell penaltyCell = new PenaltyCell();

        // Заполняем отдельно каждую линию карты.
        for (int i = 0; i < cells.length; i++) {
            // Размер линии.
            int size = i % 2 == 0 ? width - 1 : height - 1;
            cells[i] = new Cell[size];

            addCells(i, size, bank, penaltyCell);
        }

        printMapAndCoeffs(bank, penaltyCell);
    }

    /**
     * Добавляет клетки на линию карты.
     *
     * @param i           Индекс линии.
     * @param size        Размер линии.
     * @param bank        Банк.
     * @param penaltyCell Штрафная клетка.
     */
    private void addCells(int i, int size, Bank bank, PenaltyCell penaltyCell) {
        // Первая клетка - пустая.
        cells[i][0] = new EmptyCell();

        // Перемешанный список из возможных индексов для клеток на линии.
        ArrayList<Integer> positions = getShuffledNumbers(size);

        // Проходимся по индексам и ставим на них клетки.
        int cnt = 0;
        cells[i][positions.get(cnt++)] = bank;

        // До 2 штрафных клеток.
        int penaltyCellsCount = ThreadLocalRandom.current().nextInt(3);
        for (int j = 0; j < penaltyCellsCount; j++) {
            cells[i][positions.get(cnt++)] = penaltyCell;
        }

        // До 2 клеток-такси (но не больше, чем есть места на линии).
        int taxiCount = ThreadLocalRandom.current().nextInt(Math.min(3, size - penaltyCellsCount - 1));
        for (int j = 0; j < taxiCount; j++) {
            cells[i][positions.get(cnt++)] = new Taxi();
        }

        // Оставшиеся клетки - магазины.
        while (cnt < positions.size()) {
            cells[i][positions.get(cnt++)] = new Shop();
        }
    }

    /**
     * Возвращает список из чисел от 1 до n, расставленных в случайном порядке.
     *
     * @param n Наибольшее число в списке.
     * @return Список из чисел от 1 до n, расставленных в случайном порядке.
     */
    private ArrayList<Integer> getShuffledNumbers(int n) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int j = 1; j < n; j++) {
            res.add(j);
        }
        Collections.shuffle(res);

        return res;
    }

    /**
     * Выводит карту и коэффиценты.
     *
     * @param bank        Банк.
     * @param penaltyCell Штрафная клетка.
     */
    private void printMapAndCoeffs(Bank bank, PenaltyCell penaltyCell) {
        System.out.println(this + "\n");
        bank.displayCoeffs();
        penaltyCell.displayCoeff();
        System.out.println();
    }
}
