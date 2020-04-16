package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;
import Main.Utils;

import java.util.ArrayList;

public class King extends Piece {
    private ArrayList<Move> moves = new ArrayList<>();
    public King(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "K";
    }

    private void checkMove(BoardCell[][] copy, Move move) {
        if (move != null) {
            Engine.getInstance().applyMove(copy, move);
            if (!Utils.check(copy)) {
                moves.add(move);
            }
            Engine.getInstance().undoMove(copy, move);
        }
    }

    @Override
    public ArrayList<Move> generateMove(int x, int y, BoardCell[][] board) {
        BoardCell[][] copy = new BoardCell[8][8];
        moves.clear();
        for (int i = 0; i < 8; i++) {
            copy[i] = board[i].clone();
        }
        checkMove(copy, genPiece(x, y, board, x, y + 1));
        checkMove(copy, genPiece(x, y, board, x -  1, y + 1));
        checkMove(copy, genPiece(x, y, board, x + 1, y + 1));
        checkMove(copy, genPiece(x, y, board, x, y - 1));
        checkMove(copy, genPiece(x, y, board, x -  1, y - 1));
        checkMove(copy, genPiece(x, y, board, x + 1, y - 1));
        checkMove(copy, genPiece(x, y, board, x -  1, y ));
        checkMove(copy, genPiece(x, y, board, x + 1, y));

        return moves;
    }

}