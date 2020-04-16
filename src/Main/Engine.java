package Main;

import Pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Engine {
    private static Engine engine = null;
    public BoardCell[][] board;
    private String side = "black";
    private ArrayList<Piece> enginePieces;
    private ArrayList<Piece> opponentPieces;
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
        enginePieces = new ArrayList<>();
        opponentPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new BoardCell();
            }
        }
        for (int i = 0; i < 8; i++) {
            Pawn WP = new Pawn('W', 1, i);
            board[1][i].setPiece(WP);
            opponentPieces.add(WP);
        }

        Rook WR = new Rook('W', 0, 0);
        board[0][0].setPiece(WR);
        opponentPieces.add(WR);

        Horse WH = new Horse('W', 0, 1);
        board[0][1].setPiece(WH);
        opponentPieces.add(WH);

        Bishop WB = new Bishop('W', 0, 2);
        board[0][2].setPiece(WB);
        opponentPieces.add(WB);

        Queen WQ = new Queen('W', 0, 3);
        board[0][3].setPiece(WQ);
        opponentPieces.add(WQ);

        King WK = new King('W', 0, 4);
        board[0][4].setPiece(WK);
        opponentPieces.add(WK);

        Bishop WB2 = new Bishop('W', 0, 5);
        board[0][5].setPiece(WB2);
        opponentPieces.add(WB2);

        Horse WH2 = new Horse('W', 0, 6);
        board[0][6].setPiece(WH2);
        opponentPieces.add(WH2);

        Rook WR2 = new Rook('W', 0, 7);
        board[0][7].setPiece(WR2);
        opponentPieces.add(WR2);

        for (int i = 0; i < 8; i++) {
            Pawn BP = new Pawn('B', 6, i);
            board[6][i].setPiece(BP);
            enginePieces.add(BP);
        }

        Rook BR = new Rook('B', 7, 0);
        board[7][0].setPiece(BR);
        enginePieces.add(BR);

        Horse BH = new Horse('B', 7, 1);
        board[7][1].setPiece(BH);
        enginePieces.add(BH);

        Bishop BB = new Bishop('B', 7, 2);
        board[7][2].setPiece(BB);
        enginePieces.add(BB);

        Queen BQ = new Queen('B', 7, 3);
        board[7][3].setPiece(BQ);
        enginePieces.add(BQ);

        King BK = new King('B', 7, 4);
        board[7][4].setPiece(BK);
        enginePieces.add(BK);

        Bishop BB2 = new Bishop('B', 7, 5);
        board[7][5].setPiece(BB2);
        enginePieces.add(BB2);

        Horse BH2 = new Horse('B', 7, 6);
        board[7][6].setPiece(BH2);
        enginePieces.add(BH2);

        Rook BR2 = new Rook('B', 7, 7);
        board[7][7].setPiece(BR2);
        enginePieces.add(BR2);
    }

    public BoardCell[][] getBoard() {
        return this.board;
    }

    public void printBoard() {
        System.out.println("# --------------------------------------------------------");
        for (int i = 0; i < 8; i++) {
            System.out.println("# " + Arrays.toString(board[i]));
        }
        System.out.println("# --------------------------------------------------------");
        System.out.println("# Engine:" + enginePieces);
        System.out.println("# Opponent: " + opponentPieces);
        System.out.println("# --------------------------------------------------------");
    }

    public boolean checkMate() {
        return false;
    }

    public void generateAllMoves(ArrayList<Move> allMoves, String side) {
        if (side.equals(getSide())) {
            for (Piece p : enginePieces) {
                //System.out.println("# generate as engine:" + p);
                allMoves.addAll(p.generateMove());
            }
        } else {
            for (Piece p : opponentPieces) {
                //System.out.println("# generate as op:" + p);
                allMoves.addAll(p.generateMove());
            }
        }
    }

    public double evaluate(String side) {
        int w2m, materialScore = 0;
        if (side.equals("black")) {
            w2m = 1;
        } else {
            w2m = -1;
        }

        ArrayList<Move> whiteMoves = new ArrayList<>();
        generateAllMoves(whiteMoves, "white");
        ArrayList<Move> blackMoves = new ArrayList<>();
        generateAllMoves(blackMoves, "black");
        int wMobility = whiteMoves.size();
        int bMobility = blackMoves.size();

        double mobilityScore = 0.1 * (bMobility - wMobility);
        for (Piece p : enginePieces) {
            if (p.type == 'K') {
                materialScore += 200;
            }
            if (p.type == 'Q') {
                materialScore += 9;
            }
            if (p.type == 'R') {
                materialScore += 5;
            }
            if (p.type == 'B' || p.type == 'H') {
                materialScore += 3;
            }
            if (p.type == 'P') {
                materialScore += 1;
            }
        }

        for (Piece p : opponentPieces) {
            if (p.type == 'K') {
                materialScore -= 200;
            }
            if (p.type == 'Q') {
                materialScore -= 9;
            }
            if (p.type == 'R') {
                materialScore -= 5;
            }
            if (p.type == 'B' || p.type == 'H') {
                materialScore -= 3;
            }
            if (p.type == 'P') {
                materialScore -= 1;
            }
        }
        return (materialScore + mobilityScore) * w2m;

    }

    public void applyMove(Move move) {
        int startY = Utils.getIndexOfLetter(move.string.charAt(0));
        int startX = Character.getNumericValue(move.string.charAt(1)) - 1;

        int finalY = Utils.getIndexOfLetter(move.string.charAt(2));
        int finalX = Character.getNumericValue(move.string.charAt(3)) - 1;

        Piece temp = board[startX][startY].piece;
        if (board[finalX][finalY].piece != null) {
            board[finalX][finalY].addPreviousPiece(board[finalX][finalY].getPiece());
        }

        board[startX][startY].setPiece(null);
        temp.setX(finalX);
        temp.setY(finalY);
        board[finalX][finalY].setPiece(temp);

        resetArray();
    }

    public void undoMove(Move move) {
        int finalY = Utils.getIndexOfLetter(move.string.charAt(0));
        int finalX = Character.getNumericValue(move.string.charAt(1)) - 1;

        int startY = Utils.getIndexOfLetter(move.string.charAt(2));
        int startX = Character.getNumericValue(move.string.charAt(3)) - 1;

        Piece temp = board[startX][startY].getPiece();
        if (board[startX][startY].previousPieceQueue.peek() != null) {
            board[startX][startY].setPiece(board[startX][startY].previousPieceQueue.poll());
        } else {
            board[startX][startY].setPiece(null);
        }

        temp.setX(finalX);
        temp.setY(finalY);
        board[finalX][finalY].setPiece(temp);

        resetArray();
    }

    public Move negamax(String side, int depth) {

        if (checkMate() || depth == 0) {
            return new Move(evaluate(side));
        }

        double max = Double.MIN_VALUE;
        Move bestMove = null;

        for (Move move : getAllCurrentMoves(side)) {
            applyMove(move);

            double score;
            if (side.equals("black")) {
                score = - (negamax("white", depth - 1).score);
            } else {
                score = - (negamax("black", depth - 1).score);
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
        move = negamax(this.side, 1);
        if (move != null) {
            printBoard();
            System.out.println("move " + move.string);
            Utils.xboardMoves(board, move.string);
            clearRemains();
            resetArray();
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

    public ArrayList<Move> getAllCurrentMoves(String side) {
        ArrayList<Move> allMoves = new ArrayList<Move>();
        resetArray();
        generateAllMoves(allMoves, side);
        System.out.println("# " + allMoves);
        return allMoves;
    }

    public void switchSides() {
        ArrayList<Piece> aux = enginePieces;
        enginePieces = opponentPieces;
        opponentPieces = aux;
    }

    public void resetArray() {
        enginePieces.clear();
        opponentPieces.clear();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    if (board[i][j].getPiece().color == getColor()) {
                        enginePieces.add(board[i][j].getPiece());
                    } else {
                        opponentPieces.add(board[i][j].getPiece());
                    }
                }
            }
        }
    }

    public void clearRemains() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                while (board[i][j].previousPieceQueue.peek() != null) {
                    board[i][j].previousPieceQueue.remove();
                }
            }
        }
    }

    public ArrayList<Piece> getEnginePieces() {
        return this.enginePieces;
    }

    public ArrayList<Piece> getOpponentPieces() {
        return this.opponentPieces;
    }
}
