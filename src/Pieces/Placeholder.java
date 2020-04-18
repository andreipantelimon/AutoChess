package Pieces;

import Main.BoardCell;
import Main.Move;

import java.util.ArrayList;

public class Placeholder extends Piece {
    public Placeholder(char color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public Boolean check(BoardCell[][] board) {
        return null;
    }

    @Override
    public ArrayList<Move> generateMove() {
        return null;
    }
}
