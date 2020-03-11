package Pieces;

public class Queen extends Piece {
    public Queen(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "Q";
    }
}
