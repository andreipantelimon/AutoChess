package Pieces;

import Main.BoardCell;
import Main.Move;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "P";
    }

    @Override
    public ArrayList<Move> generateMove(int x, int y, BoardCell[][] board) {
        ArrayList<Move> moves = new ArrayList<>();
        if (board[x][y].piece.color == 'W') {
            moves.add(genPiece(x, y, board, x + 1, y));
            if (x < 7) {
                if (y > 0) {
                    if (board[x + 1][y - 1].piece != null) {
                        moves.add(genPiece(x, y, board, x + 1, y - 1));
                    }
                }
                if (y < 7) {
                    if (board[x + 1][y + 1].piece != null) {
                        moves.add(genPiece(x, y, board, x + 1, y + 1));
                    }
                }
            }
        } else
            if (board[x][y].piece.color == 'B') {
            moves.add(genPiece(x, y, board, x - 1, y));
            if (x > 0) {
                if (y > 0) {
                    if (board[x - 1][y - 1].piece != null) {
                        moves.add(genPiece(x, y, board, x - 1, y - 1));
                    }
                }
                if (y < 7) {
                    if (board[x - 1][y + 1].piece != null) {
                        moves.add(genPiece(x, y, board, x - 1, y + 1));
                    }
                }
            }
        }
        return moves;
    }

}