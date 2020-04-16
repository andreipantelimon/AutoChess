package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;
import Main.Utils;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece {
    public char color;
    public int x;
    public int y;
    public char type;

    public Piece(char color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Piece(char color, int x, int y, char type) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public String toString() {
        return "(" + this.color + this.type + ", " + this.x + ", " + this.y + ")";
    }

    public String toXboard(int x) {
        String board = "abcdefgh";
        return String.valueOf(board.charAt(x));
    }

    public Boolean checkInTable(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public Move genPiece(int lastx, int lasty) {
        Move move = new Move();
//        if (Utils.check(board)) {
//            Utils.infoBox("SAH", "DAR NU E");
//            System.out.println("print");
//            System.out.println("\n --------------------------------------------------------------------------------------------------------------");
//            Engine.getInstance().printBoard();
//            //System.out.println("resign");
//            return null;
//        }
        if (checkInTable(lastx, lasty)) {
            if (Engine.getInstance().getBoard()[lastx][lasty].getPiece() == null) {
                System.out.println("# -----------------if1");
                    move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                    return move;
            } else {
                if (Engine.getInstance().getBoard()[lastx][lasty].getPiece().color != this.color) {
                    System.out.println("# -----------------if2");
                    move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                    return move;
                }
            }
        }
        return null;
    }

    public abstract ArrayList<Move> generateMove ();

    public void setX (int x) {
        this.x = x;
    }

    public void setY (int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Piece piece = (Piece) o;
        return color == piece.color &&
                x == piece.x &&
                y == piece.y &&
                type == piece.type;
    }
}