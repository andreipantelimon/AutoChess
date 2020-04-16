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
    public Boolean check() {
        for (int i = 0; i < 7; i++) {
            if (Engine.getInstance().getBoard()[x + i][y + i].piece != null) {
                if ((Engine.getInstance().getBoard()[x + i][y + i].piece instanceof King) && Engine.getInstance().getBoard()[x + i][y + i].piece.color != this.color){
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Engine.getInstance().getBoard()[x - i][y - i].piece != null) {
                if ((Engine.getInstance().getBoard()[x - i][y - i].piece instanceof King) && Engine.getInstance().getBoard()[x - i][y - i].piece.color != this.color){
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Engine.getInstance().getBoard()[x - i][y + i].piece != null) {
                if ((Engine.getInstance().getBoard()[x - i][y + 1].piece instanceof King) && Engine.getInstance().getBoard()[x - i][y + 1].piece.color != this.color ){
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Engine.getInstance().getBoard()[x + i][y - i].piece != null) {
                if ((Engine.getInstance().getBoard()[x + i][y - 1].piece instanceof King) && Engine.getInstance().getBoard()[x + i][y - 1].piece.color != this.color) {
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Engine.getInstance().getBoard()[x + i][y].piece != null) {
                if ((Engine.getInstance().getBoard()[x + i][y].piece instanceof King) && Engine.getInstance().getBoard()[x + i][y].piece.color != this.color){
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Engine.getInstance().getBoard()[x - i][y].piece != null) {
                if ((Engine.getInstance().getBoard()[x - i][y].piece instanceof King) && Engine.getInstance().getBoard()[x - i][y].piece.color != this.color){
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Engine.getInstance().getBoard()[x][y + i].piece != null) {
                if ((Engine.getInstance().getBoard()[x][y + 1].piece instanceof King) && Engine.getInstance().getBoard()[x][y + 1].piece.color != this.color ){
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Engine.getInstance().getBoard()[x][y - i].piece != null) {
                if ((Engine.getInstance().getBoard()[x][y - 1].piece instanceof King) && Engine.getInstance().getBoard()[x][y - 1].piece.color != this.color) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (genPiece(x + i, y) != null) {
                moves.add(genPiece(x + i, y));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x - i, y) != null) {
                moves.add(genPiece(x - i, y));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y - i) != null) {
                moves.add(genPiece(x, y - i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x, y + i) != null) {
                moves.add(genPiece(x, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x + i, y + i) != null) {
                moves.add(genPiece(x + i, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x - i, y - i) != null) {
                moves.add(genPiece(x - i, y - i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x - i, y + i) != null) {
                moves.add(genPiece(x - i, y + i));
            } else {
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (genPiece(x + i, y - i) != null) {
                moves.add(genPiece(x + i, y - i));
            } else {
                break;
            }
        }
        return moves;
    }
}