package Main;

import Pieces.Piece;

public class BoardCell {
    public Piece piece;
    public Piece previousPiece;

    public BoardCell() {
        this.piece = null;
        this.previousPiece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setPreviousPiece(Piece piece) {
        this.previousPiece = piece;
    }

    public String toString() {
        if (this.piece != null) {
            return this.piece.toString();
        }

        return "xx";
    }

    public Piece getPiece() {
        return this.piece;
    }
}
