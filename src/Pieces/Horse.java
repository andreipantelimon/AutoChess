package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Horse extends Piece {
    public Horse(char color, int x, int y) {
        super(color, x, y, 'H');
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