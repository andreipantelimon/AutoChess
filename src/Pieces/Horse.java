package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Horse extends Piece {
    public Horse(char color, int x, int y) {
        super(color, x, y, 'H');
    }

    @Override
    public Boolean check() {
        if (checkInTable(x - 1, y + 2)) {
            if (Engine.getInstance().getBoard()[x - 1][y + 2].piece != null) {
                if ((Engine.getInstance().getBoard()[x - 1][y + 2].piece instanceof King) && Engine.getInstance().getBoard()[x - 1][y + 2].piece.color != this.color) {
                    return true;
                }
            }
        }
        if (checkInTable(x - 1, y - 2)) {
            if (Engine.getInstance().getBoard()[x - 1][y - 2].piece != null) {
                if ((Engine.getInstance().getBoard()[x - 1][y - 2].piece instanceof King) && Engine.getInstance().getBoard()[x - 1][y - 2].piece.color != this.color) {
                    return true;
                }
            }
        }
        if (checkInTable(x + 1, y + 2)) {
            if (Engine.getInstance().getBoard()[x + 1][y + 2].piece != null) {
                if ((Engine.getInstance().getBoard()[x + 1][y + 2].piece instanceof King) && Engine.getInstance().getBoard()[x + 1][y + 2].piece.color != this.color) {
                    return true;
                }
            }
        }
        if (checkInTable(x + 1, y - 2)) {
            if (Engine.getInstance().getBoard()[x + 1][y - 2].piece != null) {
                if ((Engine.getInstance().getBoard()[x + 1][y - 2].piece instanceof King) && Engine.getInstance().getBoard()[x + 1][y - 2].piece.color != this.color) {
                    return true;
                }
            }
        }
        if (checkInTable(x + 2, y + 1)) {
            if (Engine.getInstance().getBoard()[x + 2][y + 1].piece != null) {
                if ((Engine.getInstance().getBoard()[x + 2][y + 1].piece instanceof King) && Engine.getInstance().getBoard()[x + 2][y + 1].piece.color != this.color) {
                    return true;
                }
            }
        }
        if (checkInTable(x + 2, y - 1)) {
            if (Engine.getInstance().getBoard()[x + 2][y - 1].piece != null) {
                if ((Engine.getInstance().getBoard()[x + 2][y - 1].piece instanceof King) && Engine.getInstance().getBoard()[x + 2][y - 1].piece.color != this.color) {
                    return true;
                }
            }
        }
        if (checkInTable(x - 2, y + 1)) {
            if (Engine.getInstance().getBoard()[x - 2][y + 1].piece != null) {
                if ((Engine.getInstance().getBoard()[x - 2][y + 1].piece instanceof King) && Engine.getInstance().getBoard()[x - 2][y + 1].piece.color != this.color) {
                    return true;
                }
            }
        }
        if (checkInTable(x - 2, y - 1)) {
            if (Engine.getInstance().getBoard()[x - 2][y - 1].piece != null) {
                if ((Engine.getInstance().getBoard()[x - 2][y - 1].piece instanceof King) && Engine.getInstance().getBoard()[x - 2][y - 1].piece.color != this.color) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        if (genPiece(x - 1, y + 2) != null) {
            moves.add(genPiece(x - 1, y + 2));
        }
        if (genPiece(x - 1, y - 2) != null) {
            moves.add(genPiece(x - 1, y - 2));
        }
        if (genPiece(x + 1, y + 2) != null) {
            moves.add(genPiece(x + 1, y + 2));
        }
        if (genPiece(x + 1, y - 2) != null) {
            moves.add(genPiece(x + 1, y - 2));
        }
        if (genPiece(x + 2, y + 1) != null) {
            moves.add(genPiece(x + 2, y + 1));
        }
        if (genPiece(x + 2, y - 1) != null) {
            moves.add(genPiece(x + 2, y - 1));
        }
        if (genPiece(x - 2, y + 1) != null) {
            moves.add(genPiece(x - 2, y + 1));
        }
        if (genPiece(x - 2, y - 1) != null) {
            moves.add(genPiece(x - 2, y - 1));
        }

        return moves;
    }
}