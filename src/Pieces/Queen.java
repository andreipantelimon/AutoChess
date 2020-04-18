package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(char color, int x, int y) {
        super(color, x, y, 'Q');
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
        for (int i = 0; i < 8; i++) {
            if (genPiece(x + i, y) != null) {
                moves.add(genPiece(x + i, y));
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (genPiece(x - i, y) != null) {
                moves.add(genPiece(x - i, y));
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (genPiece(x, y - i) != null) {
                moves.add(genPiece(x, y - i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (genPiece(x, y + i) != null) {
                moves.add(genPiece(x, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (genPiece(x + i, y + i) != null) {
                moves.add(genPiece(x + i, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (genPiece(x - i, y - i) != null) {
                moves.add(genPiece(x - i, y - i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (genPiece(x - i, y + i) != null) {
                moves.add(genPiece(x - i, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (genPiece(x + i, y - i) != null) {
                moves.add(genPiece(x + i, y - i));
            } else {
                break;
            }
        }
        return moves;
    }
}