package Main;

import Pieces.Piece;

import java.util.LinkedList;
import java.util.Queue;

public class BoardCell {
    public Piece piece;
    public Queue<Piece> previousPieceQueue = new LinkedList<>();

    public BoardCell() {
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void addPreviousPiece(Piece piece) {
        this.previousPieceQueue.add(piece);
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
