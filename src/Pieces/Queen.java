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
        Bishop bishop = new Bishop(this.color, this.x, this. y);
        Rook rook = new Rook(this.color, this.x, this.y);
        return bishop.check(board) || rook.check(board);
    }

    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        Bishop bishop = new Bishop(this.color, this.x, this. y);
        Rook rook = new Rook(this.color, this.x, this.y);
        moves.addAll(bishop.generateMove());
        moves.addAll(rook.generateMove());
        return moves;
    }
}