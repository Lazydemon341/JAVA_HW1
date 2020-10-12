package gameLogic;

public class EmptyCell extends Cell {

    private final String message = "Just relax here";

    @Override
    public void cellAction(Player player) {
        System.out.println(message);
    }

    @Override
    public String toString() {
        return "E";
    }
}
