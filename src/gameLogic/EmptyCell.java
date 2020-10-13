package gameLogic;

public class EmptyCell extends Cell {

    @Override
    void cellAction(Player player) {
        System.out.println("Just relax here");
    }

    @Override
    public String toString() {
        return "E";
    }
}
