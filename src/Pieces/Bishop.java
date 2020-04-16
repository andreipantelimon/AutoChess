package Pieces;

import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(char color, int x, int y) {
        super(color, x, y, 'B');
    }

    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (genPiece(x + i, y + i) != null) {
                moves.add(genPiece(x + i, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x - i, y - i) != null) {
                moves.add(genPiece(x - i, y - i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x - i, y + i) != null) {
                moves.add(genPiece(x - i, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x + i, y - i) != null) {
                moves.add(genPiece(x + i, y - i));
            } else {
                break;
            }
        }

        return moves;
    }

}