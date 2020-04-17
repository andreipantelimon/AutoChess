package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class King extends Piece {
    private ArrayList<Move> moves = new ArrayList<>();
    public King(char color, int x, int y) {
        super(color, x, y, 'K');
    }

    @Override
    public Boolean check() {
        return false;
    }

    private void checkMove(BoardCell[][] copy, Move move) {
        if (move != null) {
//            Engine.getInstance().applyMove(copy, move);
//            if (!Utils.check(copy)) {
//                moves.add(move);
//            }
//            Engine.getInstance().undoMove(copy, move);
            moves.add(move);
        }
    }

    public ArrayList<Move> generateMove() {
        BoardCell[][] copy = new BoardCell[8][8];
        moves.clear();
        for (int i = 0; i < 8; i++) {
            copy[i] = Engine.getInstance().getBoard()[i].clone();
        }
        checkMove(copy, genPiece(x, y + 1));
        checkMove(copy, genPiece(x -  1, y + 1));
        checkMove(copy, genPiece(x + 1, y + 1));
        checkMove(copy, genPiece(x, y - 1));
        checkMove(copy, genPiece(x -  1, y - 1));
        checkMove(copy, genPiece(x + 1, y - 1));
        checkMove(copy, genPiece(x -  1, y ));
        checkMove(copy, genPiece(x + 1, y));

        return moves;
    }

}