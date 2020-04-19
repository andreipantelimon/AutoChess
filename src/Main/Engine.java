package Main;

import Pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Engine {
    private static Engine engine = null;
    public boolean inCheck = false;
    public BoardCell[][] board;
    private String side;
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
    }

    public boolean checkMate(String side) {
        ArrayList<Move> moves = new ArrayList<>();
        generateAllMoves(moves, side);
        if (moves.size() == 0) {
            System.out.println("SAH MAT ATEILOR");
            return true;
        }
        return false;
    }

    public void generateAllMoves(ArrayList<Move> allMoves, String side) {
        if (side.equals(getSide())) {
            for (Piece p : enginePieces) {
                allMoves.addAll(p.generateMove());
            }
        } else {
            for (Piece p : opponentPieces) {
                allMoves.addAll(p.generateMove());
            }
        }
    }

    public double evaluate(String side) {
        int w2m, materialScore = 0;
        if (side.equals(getSide())) {
            w2m = -1;
        } else {
            w2m = 1;
        }

        if (checkMate(side)) {
            return Double.POSITIVE_INFINITY * w2m;
        }

        ArrayList<Move> player1Moves = new ArrayList<>();
        generateAllMoves(player1Moves, side);
        ArrayList<Move> player2Moves = new ArrayList<>();
        if (side.equals("black")) {
            generateAllMoves(player2Moves, "white");
        } else {
            generateAllMoves(player2Moves, "black");
        }
        int p1Mobility = player1Moves.size();
        int p2Mobility = player2Moves.size();

        double mobilityScore = 0.1 * (p1Mobility - p2Mobility);
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


        Piece temp = board[startX][startY].getPiece();

        if (board[finalX][finalY].getPiece() != null) {
            board[finalX][finalY].addPreviousPiece(board[finalX][finalY].getPiece());
        } else {
            board[finalX][finalY].addPreviousPiece(new Placeholder('X', -1, -1));
        }

        temp.setX(finalX);
        temp.setY(finalY);
        board[finalX][finalY].setPiece(temp);
        board[startX][startY].setPiece(null);

        resetArray(board, enginePieces, opponentPieces);
    }

    public void undoMove(Move move) {
        int finalY = Utils.getIndexOfLetter(move.string.charAt(0));
        int finalX = Character.getNumericValue(move.string.charAt(1)) - 1;

        int startY = Utils.getIndexOfLetter(move.string.charAt(2));
        int startX = Character.getNumericValue(move.string.charAt(3)) - 1;

        Piece temp = board[startX][startY].getPiece();
        temp.setX(finalX);
        temp.setY(finalY);

        board[finalX][finalY].setPiece(temp);

        if (board[startX][startY].previousPieceStack.peek() != null) {
            if (board[startX][startY].previousPieceStack.peek().color != 'X') {
                board[startX][startY].setPiece(board[startX][startY].previousPieceStack.poll());
            } else {
                board[startX][startY].setPiece(null);
                board[startX][startY].previousPieceStack.pop();
            }
        }

        resetArray(board, enginePieces, opponentPieces);
    }

    public double negamax(String nSide, int depth) {

        if (depth == 0) {
            return Utils.evaluate(nSide);
        }

        double max = Double.NEGATIVE_INFINITY;

        ArrayList<Move> moves = getAllCurrentMoves(nSide);
        //System.out.println("# side: " + nSide + " moves: " + moves);
        for (Move move : moves) {
            applyMove(move);

            double score = Double.NEGATIVE_INFINITY;
            if (nSide.equals("black")) {
                score = - negamax("white", depth - 1);
            }
            if (nSide.equals("white")) {
                score = - negamax("black", depth - 1);
            }

            undoMove(move);

            if (score > max) {
                max = score;
            }
        }

        return max;
    }

    public char getColor() {
        return (getSide().equals("black")? 'B':'W');
    }

    public Move generateMove(int depth) {
        String bestMove = null;
        double score = Double.NEGATIVE_INFINITY;

        ArrayList<Move> moves = getAllCurrentMoves(getSide());

        double max = Double.NEGATIVE_INFINITY;

        //System.out.println("# side: " + getSide() + " moves: " + moves);

        for (Move move : moves) {
            applyMove(move);

            if (getSide().equals("black")) {
                score = - negamax("white", depth - 1);
            }
            if (getSide().equals("white")) {
                score = - negamax("black", depth - 1);
            }

            undoMove(move);

            if (score > max) {
                max = score;
                bestMove = move.string;
            }
        }
        return new Move(bestMove, max);
    }

    public void startSearch() {
        Move bestMove = generateMove(3);
        if (bestMove.string != null) {
            System.out.println("move " + bestMove.string);
            Utils.xboardMoves(board, bestMove.string);
            System.out.println("# Max: " + bestMove.score);
            //printBoard();
            clearRemains();
            resetArray(this.board, this.enginePieces, this.opponentPieces);
        } else {
            //System.out.println("# RESIGN MAP: ");
            printBoard();
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
        resetArray(this.board, this.enginePieces, this.opponentPieces);
        generateAllMoves(allMoves, side);
        return allMoves;
    }

    public void resetArray(BoardCell[][] board, ArrayList<Piece> enginePieces, ArrayList<Piece> opponentPieces) {
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
                while (board[i][j].previousPieceStack.peek() != null) {
                    board[i][j].previousPieceStack.pop();
                }
            }
        }
    }

    public int checkBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    if ((board[i][j].getPiece().color == getColor()) && board[i][j].getPiece().check(board)) {
                        return 1;
                    }
                    if ((board[i][j].getPiece().color != getColor()) && board[i][j].getPiece().check(board)) {
                        //System.out.println("Piesa care ar da sah este :" + board[i][j].getPiece());
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    public void printMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        generateAllMoves(moves, getSide());
        System.out.println("# Moves: " + moves);
    }

    public ArrayList<Piece> getEnginePieces() {
        return this.enginePieces;
    }

    public ArrayList<Piece> getOpponentPieces() {
        return this.opponentPieces;
    }
}