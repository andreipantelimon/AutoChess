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
        moves.add(genPiece(x, y, board, x, y + 1));
        moves.add(genPiece(x, y, board, x -  1, y + 1));
        moves.add(genPiece(x, y, board, x + 1, y + 1));

        return moves;
    }

}