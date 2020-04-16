package Pieces;

import Main.BoardCell;
import Main.Move;
import Main.Utils;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(char color) {
        super(color);
    }

    public String toString() {
        return this.color + "P";
    }

    @Override
    public ArrayList<Move> generateMove(int x, int y, BoardCell[][] board) {
        ArrayList<Move> moves = new ArrayList<>();
        if (board[x][y].piece.color == 'W') {
            if (board[x + 1][y].piece == null) {
                moves.add(genPiece(x, y, board, x + 1, y));
            }
            if (x < 7) {
                if (y > 0) {
                    if (board[x + 1][y - 1].piece != null) {
                        //Utils.infoBox("Culoare" + board[x][y].piece.color + " mananca pe " + board[x + 1][y - 1].piece.color, "PLS");
                        moves.add(genPiece(x, y, board, x + 1, y - 1));
                    }
                }
                if (y < 7) {
                    if (board[x + 1][y + 1].piece != null) {
                        //Utils.infoBox("Culoare" + board[x][y].piece.color + " mananca pe " + board[x + 1][y + 1].piece.color, "PLS");
                        moves.add(genPiece(x, y, board, x + 1, y + 1));
                    }
                }
            }
        } else {
            if (board[x][y].piece.color == 'B') {
                if (board[x - 1][y].piece == null) {
                    moves.add(genPiece(x, y, board, x - 1, y));
                }
                if (x > 0) {
                    if (y > 0) {
                        if (board[x - 1][y - 1].piece != null) {
                            //Utils.infoBox("Culoare" + board[x][y].piece.color + " mananca pe " + board[x - 1][y - 1].piece.color, "PLS");
                            moves.add(genPiece(x, y, board, x - 1, y - 1));
                        }
                    }
                    if (y < 7) {
                        if (board[x - 1][y + 1].piece != null) {
                            //Utils.infoBox("Culoare" + board[x][y].piece.color + " mananca pe " + board[x - 1][y + 1].piece.color, "PLS");
                            moves.add(genPiece(x, y, board, x - 1, y + 1));
                        }
                    }
                }
            }
        }
        return moves;
    }

}