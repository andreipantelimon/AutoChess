package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(char color, int x, int y) {
        super(color, x, y, 'B');
    }

    @Override
    public Boolean check(BoardCell[][] board) {
        for (int i = 1; i < 8; i++) {
            if (checkInTable(x + i, y + i)) {
                if (board[x + i][y + i].piece != null) {
                    if ((board[x + i][y + i].piece instanceof King) && board[x + i][y + i].piece.color != this.color) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (checkInTable(x - i, y - i)) {
                if (board[x - i][y - i].piece != null) {
                    if ((board[x - i][y - i].piece instanceof King) && board[x - i][y - i].piece.color != this.color) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (checkInTable(x - i, y + i)) {
                if (board[x - i][y + i].piece != null) {
                    if ((board[x - i][y + i].piece instanceof King) && board[x - i][y + i].piece.color != this.color) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (checkInTable(x + i, y - i)) {
                if (board[x + i][y - i].piece != null) {
                    if ((board[x + i][y - i].piece instanceof King) && board[x + i][y - i].piece.color != this.color) {
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
            if (genPiece(x + i, y + i) != null) {
                moves.add(genPiece(x + i, y + i));
                if (Engine.getInstance().getBoard()[x + i][y + i].getPiece() != null) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (genPiece(x - i, y - i) != null) {
                moves.add(genPiece(x - i, y - i));
                if (Engine.getInstance().getBoard()[x - i][y - i].getPiece() != null) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (genPiece(x - i, y + i) != null) {
                moves.add(genPiece(x - i, y + i));
                if (Engine.getInstance().getBoard()[x - i][y + i].getPiece() != null) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (genPiece(x + i, y - i) != null) {
                moves.add(genPiece(x + i, y - i));
                if (Engine.getInstance().getBoard()[x + i][y - i].getPiece() != null) {
                    break;
                }
            } else {
                break;
            }
        }

        return moves;
    }

}