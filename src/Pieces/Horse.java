package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Horse extends Piece {
    public Horse(char color, int x, int y) {
        super(color, x, y, 'H');
    }
    public Boolean horseCheck(int x, int y) {
        BoardCell[][] board = Engine.getInstance().getBoard();
        if (checkInTable(x, y)) {
            if (board[x][y].piece != null) {
                if ((board[x][y].piece instanceof King) && board[x][y].piece.color != this.color) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public Boolean check(BoardCell[][] board) {
        return horseCheck(x - 1, y + 2) ||
                horseCheck( x - 1, y - 2) ||
                horseCheck( x + 1, y + 2) ||
                horseCheck( x + 1, y - 2) ||
                horseCheck( x + 2, y + 1) ||
                horseCheck( x + 2, y - 1) ||
                horseCheck( x - 2, y + 1) ||
                horseCheck( x - 2, y - 1);
    }

    public void horseMove (ArrayList<Move> moves, int x, int y) {
        if (genPiece(x, y) != null) {
            moves.add(genPiece(x, y));
        }
    }
    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        horseMove(moves,x - 1, y + 2);
        horseMove(moves,x - 1, y - 2);
        horseMove(moves, x + 1, y + 2);
        horseMove(moves, x + 1, y - 2);
        horseMove(moves, x + 2, y + 1);
        horseMove(moves, x + 2, y - 1);
        horseMove(moves, x - 2, y + 1);
        horseMove(moves, x - 2, y - 1);

        return moves;
    }
}