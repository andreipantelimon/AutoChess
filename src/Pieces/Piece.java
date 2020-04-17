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
//        if (Utils.check(board)) {
//            Utils.infoBox("SAH", "DAR NU E");
//            System.out.println("print");
//            System.out.println("\n --------------------------------------------------------------------------------------------------------------");
//            Engine.getInstance().printBoard();
//            //System.out.println("resign");
//            return null;
//        }
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

        BoardCell[][] copy = new BoardCell[8][8];
        for (int i = 0; i < 8; i++) {
            copy[i] = Engine.getInstance().getBoard()[i].clone();
        }

        if (checkInTable(lastx, lasty) && Engine.getInstance().inCheck) {
            if (Engine.getInstance().getBoard()[lastx][lasty].getPiece() == null) {
                move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                ArrayList<Piece> enginePiecesCopy = new ArrayList<>();
                ArrayList<Piece> opponentPiecesCopy = new ArrayList<>();
                Engine.getInstance().applyMove(copy, move, enginePiecesCopy, opponentPiecesCopy);
                Engine.getInstance().resetArray(copy, enginePiecesCopy, opponentPiecesCopy);
                if (Engine.getInstance().checkBoard(enginePiecesCopy, opponentPiecesCopy) != -1) {
                    Engine.getInstance().inCheck = false;
                    //Engine.getInstance().undoMove(copy, move, enginePiecesCopy, opponentPiecesCopy);
                    return move;
                }
            } else {
                if (Engine.getInstance().getBoard()[lastx][lasty].getPiece().color != this.color) {
                    move.string = toXboard(y) + (x + 1) + toXboard(lasty) + (lastx + 1);
                    ArrayList<Piece> enginePiecesCopy = new ArrayList<>();
                    ArrayList<Piece> opponentPiecesCopy = new ArrayList<>();
                    Engine.getInstance().applyMove(copy, move, enginePiecesCopy, opponentPiecesCopy);
                    Engine.getInstance().resetArray(copy, enginePiecesCopy, opponentPiecesCopy);
                    if (Engine.getInstance().checkBoard(enginePiecesCopy, opponentPiecesCopy) != -1) {
                        Engine.getInstance().inCheck = false;
                        //Engine.getInstance().undoMove(copy, move, enginePiecesCopy, opponentPiecesCopy);
                        return move;
                    }
                }
            }
        }
        return null;
    }

    public abstract Boolean check ();

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