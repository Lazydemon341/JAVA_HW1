package gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Map {

    private final int width;
    private final int height;

    // TODO: 1D array.
    private final Cell[][] cells = new Cell[4][];

    public Map(int width, int height) {
        // Проверка на корректность
        if (width < 6 || width > 30 || height < 6 || height > 30) {
            throw new IllegalArgumentException("Height and width should be in [6; 30] range!");
        }
        this.width = width;
        this.height = height;

        generateMap();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        // Верхняя горизонтальная линия.
        for (int i = 0; i < cells[0].length; i++) {
            res.append(cells[0][i].toString()).append(" ");
        }
        res.append(cells[1][0]).append("\n");

        // Вертикальные линии.
        for (int i = 1; i < cells[1].length; i++) {
            res.append(cells[3][cells[3].length - i]);

            // Внутренняя (пустая) часть карты.
            res.append(" ".repeat(Math.max(0, 2 * cells[0].length - 1)));

            res.append(cells[1][i]).append("\n");
        }

        // Нижняя горизонтальная линия.
        res.append(cells[3][0]).append(" ");

        for (int i = 0; i < cells[0].length; i++) {
            res.append(cells[0][cells[0].length - i - 1].toString()).append(" ");
        }

        return res.toString();
    }

    /**
     * Заполняет карту клетками.
     */
    void generateMap() {
        Random rnd = new Random();

        Bank bank = new Bank();
        PenaltyCell penaltyCell = new PenaltyCell();

        // Заполняем отдельно каждую линию карты.
        for (int i = 0; i < cells.length; i++) {
            // Размер линии.
            int size = i % 2 == 0 ? width - 1 : height - 1;
            cells[i] = new Cell[size];

            // Первая клетка - пустая.
            cells[i][0] = new EmptyCell();

            // Список из возможных индексов для клеток на линии.
            ArrayList<Integer> positions = new ArrayList<>();
            for (int j = 1; j < size; j++) {
                positions.add(j);
            }
            // Перемешиваем его, чтобы рандомно распределить клетки.
            Collections.shuffle(positions, rnd);

            // Проходимся по индексам и ставим на них клетки.
            int cnt = 0;
            cells[i][positions.get(cnt++)] = bank;

            // До 2 штрафных клеток.
            int penaltyCellsCount = rnd.nextInt(3);
            for (int j = 0; j < penaltyCellsCount; j++) {
                cells[i][positions.get(cnt++)] = penaltyCell;
            }

            // До 2 клеток-такси.
            int taxiCount = rnd.nextInt(3);
            for (int j = 0; j < taxiCount; j++) {
                cells[i][positions.get(cnt++)] = new Taxi();

                /* На линии длины 6 не могут поместиться одновременно
                2 клетки-такси и 2 штрафные клетки. В slack-чате было указано
                предпочтение в таком случае отдавать штрафным клеткам.
                 */
                if (cnt == positions.size())
                    break;
            }

            // Оставшиеся клетки - магазины.
            while (cnt < positions.size()) {
                cells[i][positions.get(cnt++)] = new Shop();
            }
        }
    }
}
