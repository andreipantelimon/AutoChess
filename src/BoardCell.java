import Pieces.Piece;

public class BoardCell {
    public Piece piece;

    public BoardCell() {
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String toString() {
        if (this.piece != null) {
            return this.piece.toString();
        }

        return "xx";
    }
}
