package Pieces;

public abstract class Piece {
    public char color;

    public Piece(char color) {
        this.color = color;
    }

    public String toString() {
        return String.valueOf(color);
    }
}
