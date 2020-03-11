package Pieces;

public class Horse extends Piece {
    public Horse(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "H";
    }
}
