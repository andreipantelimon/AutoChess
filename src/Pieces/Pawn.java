package Pieces;

public class Pawn extends Piece {

    public Pawn(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "P";
    }
}
