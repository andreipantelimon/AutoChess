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
    public Boolean check(BoardCell[][] board) {
        return false;
    }

    private void checkMove(Move move, int lastx, int lasty) {
        if (move != null) {
            if (checkInTable(lastx, lasty)) {
                if (Engine.getInstance().getBoard()[lastx][lasty].getPiece() == null) {
                    Engine.getInstance().getBoard()[x][y].setPiece(null);
                    Engine.getInstance().getBoard()[lastx][lasty].setPiece(this);
                    if (Engine.getInstance().checkBoard() != -1) {
                        moves.add(move);
                    }
                    Engine.getInstance().getBoard()[x][y].setPiece(this);
                    Engine.getInstance().getBoard()[lastx][lasty].setPiece(null);
                } else {
                    if (Engine.getInstance().getBoard()[lastx][lasty].getPiece().color != this.color) {
                        Piece temp = Engine.getInstance().getBoard()[lastx][lasty].getPiece();
                        Engine.getInstance().getBoard()[x][y].setPiece(null);
                        Engine.getInstance().getBoard()[lastx][lasty].setPiece(this);
                        if (Engine.getInstance().checkBoard() != -1) {
                            moves.add(move);
                        }
                        Engine.getInstance().getBoard()[x][y].setPiece(this);
                        Engine.getInstance().getBoard()[lastx][lasty].setPiece(temp);
                    }
                }
            }
        }
    }

    public ArrayList<Move> generateMove() {
        moves.clear();

        checkMove(genPiece(x, y + 1), x, y + 1);
        checkMove(genPiece(x -  1, y + 1), x - 1, y + 1);
        checkMove(genPiece(x + 1, y + 1), x + 1, y + 1);
        checkMove(genPiece(x, y - 1), x, y - 1);
        checkMove(genPiece(x -  1, y - 1), x - 1, y - 1);
        checkMove(genPiece(x + 1, y - 1), x + 1, y - 1);
        checkMove(genPiece(x -  1, y ), x - 1, y);
        checkMove(genPiece(x + 1, y), x + 1, y);

        return moves;
    }

}