package Pieces;

import Main.BoardCell;
import Main.Move;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(char color, int x, int y) {
        super(color, x, y, 'Q');
    }

    @Override
    public Boolean check(BoardCell[][] board) {
        return multipleCheck(1, 0) ||
                multipleCheck(-1, 0) ||
                multipleCheck(0, 1) ||
                multipleCheck(0, -1) ||
                multipleCheck(1, 1) ||
                multipleCheck(1, -1) ||
                multipleCheck(-1, 1) ||
                multipleCheck(-1, -1);

    }

    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        multipleMove(moves, 1, 1);
        multipleMove(moves, 1, -1);
        multipleMove(moves, -1, -1);
        multipleMove(moves, -1, 1);
        multipleMove(moves, 1, 0);
        multipleMove(moves, -1, 0);
        multipleMove(moves,0, 1);
        multipleMove(moves,0, -1);
        return moves;
    }
}