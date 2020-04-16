package Pieces;

import Main.BoardCell;
import Main.Move;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "Q";
    }

    @Override
    public ArrayList<Move> generateMove(int x, int y, BoardCell[][] board) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y, board, x + i, y) != null) {
                moves.add(genPiece(x, y, board, x + i, y));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y, board, x - i, y) != null) {
                moves.add(genPiece(x, y, board, x - i, y));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y, board, x, y - i) != null) {
                moves.add(genPiece(x, y, board, x, y - i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y, board, x, y + i) != null) {
                moves.add(genPiece(x, y, board, x, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y, board, x + i, y + i) != null) {
                moves.add(genPiece(x, y, board, x + i, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y, board, x - i, y - i) != null) {
                moves.add(genPiece(x, y, board, x - i, y - i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y, board, x - i, y + i) != null) {
                moves.add(genPiece(x, y, board, x - i, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y, board, x + i, y - i) != null) {
                moves.add(genPiece(x, y, board, x + i, y - i));
            } else {
                break;
            }
        }
        return moves;
    }
}