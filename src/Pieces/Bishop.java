package Pieces;

public class Bishop extends Piece {
    public Bishop(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "B";
    }
}
