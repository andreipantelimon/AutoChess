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
        ArrayList<Move> moves;
        Bishop bishop = new Bishop(board[x][y].piece.color);
        moves = bishop.generateMove(x, y, board);
        Rook rook = new Rook(board[x][y].piece.color);
        ArrayList<Move> moves1 = rook.generateMove(x, y, board);
        moves.addAll(moves1);
        return moves;
    }
}