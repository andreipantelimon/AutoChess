package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(char color, int x, int y) {
        super(color, x, y, 'B');
    }


    @Override
    public Boolean check(BoardCell[][] board) {
        return multipleCheck(1, 1) ||
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

        return moves;
    }

}