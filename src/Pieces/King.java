package Pieces;

public class King extends Piece {
    public King(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "K";
    }
}
