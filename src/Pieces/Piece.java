package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;
import Main.Utils;

import java.util.ArrayList;

public abstract class Piece {
    public char color;

    public Piece(char color) {
        this.color = color;
    }

    public String toString() {
        return String.valueOf(color);
    }

    public String toXboard(int x) {
        String board = "abcdefgh";
        return String.valueOf(board.charAt(x));
    }

    public Boolean checkInTable(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public Move genPiece(int x, int y, BoardCell[][] board, int lastx, int lasty) {
        Move move = new Move();
        if (Utils.check(board)) {
            Utils.infoBox("SAH", "DAR NU E");
            System.out.println("print");
            System.out.println("\n --------------------------------------------------------------------------------------------------------------");
            Engine.getInstance().printBoard();
            //System.out.println("resign");
            return null;
        }
        Piece piece = board[x][y].piece;
        if (checkInTable(lastx, lasty)) {
            if (board[lastx][lasty].piece == null || board[lastx][lasty].piece.color != piece.color) {
                move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                return move;
            }
        }
        return null;
    }

    public abstract ArrayList<Move> generateMove (int x, int y, BoardCell[][] boardcell);
}