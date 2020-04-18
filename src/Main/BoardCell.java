package Main;

import Pieces.Piece;

import java.util.ArrayDeque;
import java.util.Deque;

public class BoardCell {
    public Piece piece;
    public Deque<Piece> previousPieceStack = new ArrayDeque<Piece>();

    public BoardCell() {
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void addPreviousPiece(Piece piece) {
        this.previousPieceStack.push(piece);
    }

    public String toString() {
        if (this.piece != null) {
            return this.piece.toString();
        }

        return "xxxxxxxxxx";
    }

    public Piece getPiece() {
        return this.piece;
    }
}
