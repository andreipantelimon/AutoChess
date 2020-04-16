package Pieces;

import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(char color, int x, int y) {
        super(color, x, y, 'P');
    }

    public Boolean check() {
        if (this.color == 'W') {
            if (x < 7) {
                if (y > 0) {
                    if (Engine.getInstance().getBoard()[x + 1][y - 1].piece != null) {
                        if ((Engine.getInstance().getBoard()[x + 1][y - 1].piece instanceof King) && Engine.getInstance().getBoard()[x + 1][y - 1].piece.color != this.color) {
                            return true;
                        }
                    }
                }
                if (y < 7) {
                    if (Engine.getInstance().getBoard()[x + 1][y + 1].piece != null) {
                        if ((Engine.getInstance().getBoard()[x + 1][y + 1].piece instanceof King) && Engine.getInstance().getBoard()[x + 1][y + 1].piece.color != this.color) {
                            return true;
                        }
                    }
                }
            }
        }
        if (this.color == 'B') {
            if (x > 0) {
                if (y > 0) {
                    if (Engine.getInstance().getBoard()[x - 1][y - 1].piece != null) {
                        if ((Engine.getInstance().getBoard()[x - 1][y - 1].piece instanceof King) && Engine.getInstance().getBoard()[x - 1][y - 1].piece.color != this.color) {
                            return true;
                        }
                    }
                }
                if (y < 7) {
                    if (Engine.getInstance().getBoard()[x - 1][y + 1].piece != null) {
                        if ((Engine.getInstance().getBoard()[x - 1][y + 1].piece instanceof King) && Engine.getInstance().getBoard()[x - 1][y + 1].piece.color != this.color) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Move> generateMove() {
        ArrayList<Move> moves = new ArrayList<>();
        if (this.color == 'W') {
            if (x < 7) {
                if (Engine.getInstance().getBoard()[x + 1][y].piece == null) {
                    if (genPiece(x + 1, y) != null) {
                        moves.add(genPiece(x + 1, y));
                    }
                }
                if (y > 0) {
                    if (Engine.getInstance().getBoard()[x + 1][y - 1].piece != null) {
                        if (genPiece(x + 1, y - 1) != null) {
                            moves.add(genPiece(x + 1, y - 1));
                        }
                    }
                }
                if (y < 7) {
                    if (Engine.getInstance().getBoard()[x + 1][y + 1].piece != null) {
                        if (genPiece(x + 1, y + 1) != null) {
                            moves.add(genPiece(x + 1, y + 1));
                        }
                    }
                }
            }
        }
        if (this.color == 'B') {
            if (x > 0) {
                if (Engine.getInstance().getBoard()[x - 1][y].piece == null) {
                    if (genPiece(x - 1, y) != null) {
                        moves.add(genPiece(x - 1, y));
                    }
                }
                if (y > 0) {
                    if (Engine.getInstance().getBoard()[x - 1][y - 1].piece != null) {
                        if (genPiece(x - 1, y - 1) != null) {
                            moves.add(genPiece(x - 1, y - 1));
                        }
                    }
                }
                if (y < 7) {
                    if (Engine.getInstance().getBoard()[x - 1][y + 1].piece != null) {
                        if (genPiece(x - 1, y + 1) != null) {
                            moves.add(genPiece(x - 1, y + 1));
                        }
                    }
                }
            }
        }
        return moves;
    }

}