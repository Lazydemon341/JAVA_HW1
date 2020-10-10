package gameLogic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Map {
    
    final int width;
    final int height;
    Cell[][] cells = new Cell[4][];

    public Map(int width, int height){
        // Проверка на корректность
        if (width < 6 || width > 30 || height < 6 || height > 30){
            throw new IllegalArgumentException("Height and width should be in [6; 30] range!");
        }
        this.width = width;
        this.height = height;

        generateMap();
    }

    @Override
    public String toString() {
        String res = "";

        // Верхняя горизонтальная линия.
        for (int i = 0; i < cells[0].length; i++) {
            res += cells[0][i].toString() + " ";
        }
        res += cells[1][0] + "\n";

        // Вертикальные линии.
        for (int i = 1; i < cells[1].length; i++) {
            // Конец левой вертикальной линии - сверху.
            res += cells[3][cells[3].length - i];

            // Внутренняя (пустая) часть карты.
            for (int j = 0; j < cells[0].length - 1; j++)
                res += "  ";

            res += " " + cells[1][i] + "\n";
        }

        // Нижняя горизонтальная линия.
        res += cells[3][0] + " ";

        for (int i = 0; i < cells[0].length; i++) {
            res += cells[0][cells[0].length - i - 1].toString() + " ";
        }

        return res;
    }

    void generateMap(){
        Random rnd = new Random();

        // Один банк на всю карту.
        Bank bank = new Bank();

        // Заполняем отдельно каждую линию карты.
        for (int i = 0; i < cells.length; i++) {
            // Размер линии.
            int size = i % 2 == 0 ? width - 1 : height - 1;
            cells[i] = new Cell[size];

            // Первая клетка - пустая.
            cells[i][0] = new EmptyCell();

            // Список из возможных индексов для клеток на карте.
            ArrayList<Integer> positions = new ArrayList<Integer>();
            for (int j = 1; j < size; j++) {
                positions.add(j);
            }
            // Перемешиваем его, чтобы рандомно распределить клетки.
            Collections.shuffle(positions, rnd);

            // Проходимся по индексам и ставим на них клетки.
            int cnt = 0;
            cells[i][positions.get(cnt++)] = bank;

            // До 2 клеток-такси.
            int taxiCount = rnd.nextInt(3);
            for (int j = 0; j < taxiCount; j++) {
                cells[i][positions.get(cnt++)] = new Taxi();
            }

            // До 2 штрафных клеток.
            int penaltyCellsCount = rnd.nextInt(3);
            for (int j = 0; j < penaltyCellsCount; j++) {
                cells[i][positions.get(cnt++)] = new PenaltyCell();
            }

            // Оставшиеся клетки - магазины.
            while(cnt < positions.size()){
                cells[i][positions.get(cnt++)] = new Shop();
            }
        }
    }
}
