package Pieces;

import Main.BoardCell;
import Main.Engine;
import Main.Move;

import java.util.ArrayList;

public class King extends Piece {
    private ArrayList<Move> moves = new ArrayList<>();
    public King(char color, int x, int y) {
        super(color, x, y, 'K');
    }

    @Override
    public Boolean check(BoardCell[][] board) {
        return false;
    }

    // Metoda verifica daca urmatoarea mutare a regelui nu duce la apropierea regilor la mai putin de o unitate.
    private boolean checkKing( int nextx, int nexty) {
        BoardCell[][] board = Engine.getInstance().getBoard();
        if (checkInTable(nextx, nexty)) {
            if (board[nextx][nexty].getPiece() != null) {
                return (board[nextx][nexty].getPiece() instanceof King) && board[nextx][nexty].getPiece().color != this.color;
            }
        }
        return false;
    }

    // Apelarea metodei checkKing pentru toate pozitiile urmatoare posibile ale regelui.
    private boolean isLegal(int lastx, int lasty) {
        return (!checkKing(lastx + 1, lasty) &&
                !checkKing(lastx + 1, lasty + 1) &&
                !checkKing(lastx + 1, lasty - 1) &&
                !checkKing(lastx, lasty + 1) &&
                !checkKing(lastx, lasty - 1) &&
                !checkKing(lastx - 1, lasty) &&
                !checkKing(lastx - 1, lasty + 1) &&
                !checkKing(lastx - 1, lasty - 1));
    }
    // Verifica daca prin mutarea regelui se intra in sah. Daca nu, mutarea se adauga la vectorul de mutari.
    private void checkMove(Move move, int lastx, int lasty) {
        if (move != null) {
            if (checkInTable(lastx, lasty) && isLegal(lastx, lasty)) {
                if (Engine.getInstance().getBoard()[lastx][lasty].getPiece() == null) {
                    Engine.getInstance().getBoard()[x][y].setPiece(null);
                    Engine.getInstance().getBoard()[lastx][lasty].setPiece(this);
                    if (Engine.getInstance().checkBoard() != -1) {
                        moves.add(move);
                    }
                    Engine.getInstance().getBoard()[x][y].setPiece(this);
                    Engine.getInstance().getBoard()[lastx][lasty].setPiece(null);
                } else {
                    if (Engine.getInstance().getBoard()[lastx][lasty].getPiece().color != this.color) {
                        Piece temp = Engine.getInstance().getBoard()[lastx][lasty].getPiece();
                        Engine.getInstance().getBoard()[x][y].setPiece(null);
                        Engine.getInstance().getBoard()[lastx][lasty].setPiece(this);
                        if (Engine.getInstance().checkBoard() != -1) {
                            moves.add(move);
                        }
                        Engine.getInstance().getBoard()[x][y].setPiece(this);
                        Engine.getInstance().getBoard()[lastx][lasty].setPiece(temp);
                    }
                }
            }
        }
    }
    // Verifica daca conditia pentru a face rocada este indeplinita.
    public boolean castlingRule() {
        BoardCell[][] board = Engine.getInstance().getBoard();
        //Small castling
        if ((this.x == 7 || this.x == 0) && this.y == 4 && !this.hasMoved) {
            if (board[x][y + 3].getPiece() != null) {
                if (board[x][y + 1].getPiece() == null && board[x][y + 2].getPiece() == null
                        && (board[x][y + 3].getPiece() instanceof Rook) && !board[x][y + 3].getPiece().getHasMoved()) {
                    Piece start = board[x][y].getPiece();
                    board[x][y + 1].setPiece(start);
                    board[x][y].setPiece(null);
                    if (Engine.getInstance().checkBoard() != -1) {
                        Piece finalP = board[x][y + 1].getPiece();
                        board[x][y].setPiece(finalP);
                        board[x][y + 1].setPiece(null);
                        return true;
                    } else {
                        Piece finalP = board[x][y + 1].getPiece();
                        board[x][y].setPiece(finalP);
                        board[x][y + 1].setPiece(null);
                    }
                }
            }
        }
        //Big castling
        if ((this.x == 7 || this.x == 0) && this.y == 4 && !this.hasMoved) {
            if (board[x][y - 4].getPiece() != null) {
                if (board[x][y - 1].getPiece() == null && board[x][y - 2].getPiece() == null && board[x][y - 3].getPiece() == null
                        && (board[x][y - 4].getPiece() instanceof Rook) && !board[x][y - 4].getPiece().getHasMoved()) {
                    Piece start = board[x][y].getPiece();
                    board[x][y - 1].setPiece(start);
                    board[x][y].setPiece(null);
                    if (Engine.getInstance().checkBoard() != -1) {
                        Piece finalP = board[x][y - 1].getPiece();
                        board[x][y].setPiece(finalP);
                        board[x][y - 1].setPiece(null);
                        return true;
                    } else {
                        Piece finalP = board[x][y - 1].getPiece();
                        board[x][y].setPiece(finalP);
                        board[x][y - 1].setPiece(null);
                    }
                }
            }
        }
        return false;
    }
    // Adauga rocada la mutarile posibile daca este valida.
    public Move castling(int lastx, int lasty) {
        Move move = new Move();
        if (castlingRule() && checkInTable(lastx, lasty) && !Engine.getInstance().inCheck) {
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
            }
        }
        return null;
    }

    public ArrayList<Move> generateMove() {
        moves.clear();

        checkMove(genPiece(x, y + 1), x, y + 1);
        checkMove(genPiece(x -  1, y + 1), x - 1, y + 1);
        checkMove(genPiece(x + 1, y + 1), x + 1, y + 1);
        checkMove(genPiece(x, y - 1), x, y - 1);
        checkMove(genPiece(x -  1, y - 1), x - 1, y - 1);
        checkMove(genPiece(x + 1, y - 1), x + 1, y - 1);
        checkMove(genPiece(x -  1, y ), x - 1, y);
        checkMove(genPiece(x + 1, y), x + 1, y);
        //checkMove(castling(x, y + 2), x, y + 2);
        //checkMove(castling(x, y - 2), x, y - 2);

        return moves;
    }

}