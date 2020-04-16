package Pieces;

import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(char color, int x, int y) {
        super(color, x, y, 'R');
    }

    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (genPiece(x + i, y) != null) {
                moves.add(genPiece(x + i, y));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x - i, y) != null) {
                moves.add(genPiece(x - i, y));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y - i) != null) {
                moves.add(genPiece(x, y - i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y + i) != null) {
                moves.add(genPiece(x, y + i));
            } else {
                break;
            }
        }
        return moves;
    }
}