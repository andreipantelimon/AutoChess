package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(char color, int x, int y) {
        super(color, x, y, 'R');
    }

    @Override
    public Boolean check(BoardCell[][] board) {
        for (int i = 1; i < 8; i++) {
            if (checkInTable(x + i, y)) {
                if (board[x + i][y].piece != null) {
                    if ((board[x + i][y].piece instanceof King) && board[x + i][y].piece.color != this.color) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (checkInTable(x - i, y)) {
                if (board[x - i][y].piece != null) {
                    if ((board[x - i][y].piece instanceof King) && board[x - i][y].piece.color != this.color) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (checkInTable(x, y + i)) {
                if (board[x][y + i].piece != null) {
                    if ((board[x][y + i].piece instanceof King) && board[x][y + i].piece.color != this.color) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (checkInTable(x, y - i)) {
                if (board[x][y - i].piece != null) {
                    if ((board[x][y - i].piece instanceof King) && board[x][y - i].piece.color != this.color) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (genPiece(x + i, y) != null) {
                moves.add(genPiece(x + i, y));
                if (Engine.getInstance().getBoard()[x + i][y].getPiece() != null) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (genPiece(x - i, y) != null) {
                moves.add(genPiece(x - i, y));
                if (Engine.getInstance().getBoard()[x - i][y].getPiece() != null) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (genPiece(x, y - i) != null) {
                moves.add(genPiece(x, y - i));
                if (Engine.getInstance().getBoard()[x][y - i].getPiece() != null) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (genPiece(x, y + i) != null) {
                moves.add(genPiece(x, y + i));
                if (Engine.getInstance().getBoard()[x][y + i].getPiece() != null) {
                    break;
                }
            } else {
                break;
            }
        }
        return moves;
    }
}