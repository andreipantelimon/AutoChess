package Main;

import Pieces.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Engine {
    private static Engine engine = null;
    public BoardCell[][] board;
    private String side = "black";
    //private ArrayList<Move> allMoves = new ArrayList<Move>();
    private int engineTime = 0;
    private int opponentTime = 0;
    private int movesPerTime = 0;
    private int timeInControl = 0;

    private Engine() {
    }

    public static Engine getInstance() {
        if (engine == null) {
            engine = new Engine();
        }
        return engine;
    }

    public void initializeBoard() {
        board = new BoardCell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new BoardCell();
            }
        }
        board[0][0].setPiece(new Rook('W'));
        board[0][1].setPiece(new Horse('W'));
        board[0][2].setPiece(new Bishop('W'));
        board[0][3].setPiece(new Queen('W'));
        board[0][4].setPiece(new King('W'));
        board[0][5].setPiece(new Bishop('W'));
        board[0][6].setPiece(new Horse('W'));
        board[0][7].setPiece(new Rook('W'));

        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn('W'));
        }

        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Pawn('B'));
        }

        board[7][0].setPiece(new Rook('B'));
        board[7][1].setPiece(new Horse('B'));
        board[7][2].setPiece(new Bishop('B'));
        board[7][3].setPiece(new Queen('B'));
        board[7][4].setPiece(new King('B'));
        board[7][5].setPiece(new Bishop('B'));
        board[7][6].setPiece(new Horse('B'));
        board[7][7].setPiece(new Rook('B'));
    }

    public BoardCell[][] getBoard() {
        return this.board;
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.println("# " + Arrays.toString(board[i]));
        }
    }

    public boolean checkMate() {
        return false;
    }
    public boolean checkLines(int i, int ik, int j, int jk, King king) {
        for (int k = 1; k < 7; k++) {
            if (i + ik * k >= 0 && i + ik * k <= 7 && j + jk * k >= 0 && j + jk * k <= 7) {
                if (board[i + ik * k][j + jk * k].piece != null) {
                    if (board[i + ik * k][j + jk * k].piece.color != king.color && !(board[i + ik * k][j + jk * k].piece instanceof Horse ||
                            board[i + ik * k][j + jk * k].piece instanceof Bishop ||
                            board[i + ik * k][j + jk * k].piece instanceof Pawn)) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDiag(int i, int ik, int j, int jk, King king) {
        for (int k = 1; k < 7; k++) {
            if (i + ik * k >= 0 && i + ik * k <= 7 && j + jk * k >= 0 && j + jk * k <= 7) {
                if (board[i + ik * k][j + jk * k].piece != null) {
                    if (board[i + ik * k][j + jk * k].piece.color != king.color && !(board[i + ik * k][j + jk * k].piece instanceof Horse ||
                            board[i + ik * k][j + jk * k].piece instanceof Rook ||
                            board[i + ik * k][j + jk * k].piece instanceof Pawn)) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkPawn(int i, int j, King king) {
        if (board[i][j + 1].piece != null) {
            if (board[i][j + 1].piece.color != king.color && !(board[i][j + 1].piece instanceof Horse || board[i][j + 1].piece instanceof Rook)) {
                return true;
            }
        }
        if (board[i][j - 1].piece != null) {
            if (board[i][j - 1].piece.color != king.color && !(board[i][j - 1].piece instanceof Horse || board[i][j - 1].piece instanceof Rook)) {
                return true;
            }
        }
        return false;
    }

    public boolean check() {
        char color;
        if (getSide().equals("black")) {
             color = 'B';
        } else {
             color = 'W';
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].piece != null) {
                    if (board[i][j].piece instanceof King && board[i][j].piece.color == color) {
                        King king = (King) board[i][j].piece;
                        if (color == 'B') {
                            if (checkPawn(i + 1, j, king))
                                return true;
                        } else {
                            if (checkPawn(i - 1, j, king))
                                return true;
                        }
                        if (checkLines(i, 1, j, 0, king))
                            return true;
                        if (checkLines(i, -1, j, 0, king))
                            return true;
                        if (checkLines(i, 0, j, 1, king))
                            return true;
                        if (checkLines(i, 0, j, -1, king))
                            return true;
                        if (checkDiag(i , 1, j, 1, king))
                            return true;
                        if (checkDiag(i, 1, j, -1, king))
                            return true;
                        if (checkDiag(i, -1, j, 1, king))
                            return true;
                        if (checkDiag(i, -1, j, -1, king))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public void generateAllMoves(ArrayList<Move> allMoves, String side) {
        int color = ' ';
        if (side.equals("black")) {
            color = 'B';
        }
        if (side.equals("white")) {
            color = 'W';
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].piece != null) {
                    if (board[i][j].piece.color == color) {
                        allMoves.addAll(board[i][j].piece.generateMove(i, j, board));
                    }
                }
            }
        }

        Iterator<Move> it = allMoves.iterator();
        while (it.hasNext()) {
            Move temp = (Move) it.next();
            if (temp == null) {
                it.remove();
            }
        }
    }

    public int evaluate() {
        return 1;
    }

    public void applyMove(Move move) {
        int startY = Utils.getIndexOfLetter(move.string.charAt(0));
        int startX = Character.getNumericValue(move.string.charAt(1)) - 1;

        int finalY = Utils.getIndexOfLetter(move.string.charAt(2));
        int finalX = Character.getNumericValue(move.string.charAt(3)) - 1;

        Piece temp = board[startX][startY].piece;
        if (board[finalX][finalY].piece != null) {
            board[finalX][finalY].setPreviousPiece(board[finalX][finalY].getPiece());
        }

        board[startX][startY].piece = null;
        board[finalX][finalY].setPiece(temp);
    }

    public void undoMove(Move move) {
        int finalY = Utils.getIndexOfLetter(move.string.charAt(0));
        int finalX = Character.getNumericValue(move.string.charAt(1)) - 1;

        int startY = Utils.getIndexOfLetter(move.string.charAt(2));
        int startX = Character.getNumericValue(move.string.charAt(3)) - 1;

        Piece temp = board[startX][startY].piece;
        if (board[startX][startY].previousPiece != null) {
            board[startX][startY].setPiece(board[startX][startY].previousPiece);
            board[startX][startY].previousPiece = null;
        } else {
            board[startX][startY].setPiece(null);
        }

        board[finalX][finalY].setPiece(temp);

    }

    public Move negamax(String side, int depth) {

        if (checkMate() || depth == 0) {
            return new Move(evaluate());
        }

        int max = Integer.MIN_VALUE;
        Move bestMove = null;

        ArrayList<Move> allMoves = new ArrayList<Move>();

        generateAllMoves(allMoves, side);

        for (Move move : allMoves) {
            applyMove(move);

            int score;
            if (side.equals("black")) {
                score = - negamax("white", depth - 1).score;
            } else {
                score = - negamax("black", depth - 1).score;
            }

            if (score > max) {
                max = score;
                bestMove = new Move(move);
                bestMove.score = max;
            }

            undoMove(move);
        }

        return bestMove;
    }

    public char getColor() {
        return (getSide().equals("black")? 'B':'W');
    }

    public void generateMove() {
        Move move;
        move = negamax(this.side, 2);
        if (move != null) {
            System.out.println("move " + move.string);
            Utils.xboardMoves(board, move.string);
        } else {
            System.out.println("resign");
        }
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setEngineTime(int time) {
        this.engineTime = time;
    }

    public void setOpponentTime(int time) {
        this.opponentTime = time;
    }

    public void setMovesPerTime(int moves) {
        this.movesPerTime = moves;
    }

    public void setTimeInControl(int time) {
        this.timeInControl = time;
    }

//    //public ArrayList<Move> getAllMoves() {
//        return allMoves;
//    }
}
