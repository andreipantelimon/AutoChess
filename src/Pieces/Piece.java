package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

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

    public Piece(Piece p) {
        this.color = p.color;
        this.x = p.x;
        this.y = p.y;
        this.type = p.type;
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
        if (checkInTable(lastx, lasty) && !Engine.getInstance().inCheck) {
            if (Engine.getInstance().getBoard()[lastx][lasty].getPiece() == null) {
                    move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                    return move;
            } else {
                if (Engine.getInstance().getBoard()[lastx][lasty].getPiece().color != this.color) {
                    move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                    return move;
                }
            }
        }

        if (checkInTable(lastx, lasty) && Engine.getInstance().inCheck) {
            if (Engine.getInstance().getBoard()[lastx][lasty].getPiece() == null) {
                Piece start = Engine.getInstance().getBoard()[x][y].getPiece();
                Engine.getInstance().getBoard()[lastx][lasty].setPiece(start);
                Engine.getInstance().getBoard()[x][y].setPiece(null);
                if (Engine.getInstance().checkBoard() != -1) {
                    move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                    Piece finalP = Engine.getInstance().getBoard()[lastx][lasty].getPiece();
                    Engine.getInstance().getBoard()[x][y].setPiece(finalP);
                    Engine.getInstance().getBoard()[lastx][lasty].setPiece(null);
                    return move;
                } else {
                    Piece finalP = Engine.getInstance().getBoard()[lastx][lasty].getPiece();
                    Engine.getInstance().getBoard()[x][y].setPiece(finalP);
                    Engine.getInstance().getBoard()[lastx][lasty].setPiece(null);
                }
            } else {
                if (Engine.getInstance().getBoard()[lastx][lasty].getPiece().color != this.color) {
                    Piece startP = Engine.getInstance().getBoard()[x][y].getPiece();
                    Piece finalP = Engine.getInstance().getBoard()[lastx][lasty].getPiece();
                    Engine.getInstance().getBoard()[x][y].setPiece(null);
                    Engine.getInstance().getBoard()[lastx][lasty].setPiece(startP);
                    if (Engine.getInstance().checkBoard() != -1) {
                        move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                        Engine.getInstance().getBoard()[lastx][lasty].setPiece(finalP);
                        Engine.getInstance().getBoard()[x][y].setPiece(startP);
                        return move;
                    } else {
                        Engine.getInstance().getBoard()[lastx][lasty].setPiece(finalP);
                        Engine.getInstance().getBoard()[x][y].setPiece(startP);
                    }
                }
            }
        }
        return null;
    }

    public abstract Boolean check (BoardCell[][] board);

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