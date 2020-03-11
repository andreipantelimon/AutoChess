package Pieces;

public class Rook extends Piece {
    public Rook(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "R";
    }
}
