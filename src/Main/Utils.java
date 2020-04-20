package Main;

import Pieces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Utils {

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int getIndexOfLetter(char letter) {
        int tempInt = 96;
        if (letter <= 122 && letter >= 97) {
            return (int) letter - tempInt - 1;
        }
        return -1;
    }

    public static void xboardMoves(BoardCell[][] board, String move) {
        int startYPos = Utils.getIndexOfLetter(move.charAt(0));
        int startXPos = move.charAt(1) - '0' - 1;
        int finishYPos = Utils.getIndexOfLetter(move.charAt(2));
        int finishXPos = move.charAt(3) - '0' - 1;

        if (move.equals("e8c8") || move.equals("e8g8") || move.equals("e1c1") || move.equals("e1g1")) {
            if (board[startXPos][startYPos].getPiece() != null) {
                board[startXPos][startYPos].getPiece().setHasMoved();
                Piece tempPiece = board[startXPos][startYPos].getPiece();
                if (move.charAt(2) == 'c') {
                    if (board[startXPos][finishYPos - 2].getPiece() != null){
                        Piece tempPiece2 = board[startXPos][finishYPos - 2].getPiece();
                        tempPiece.setX(finishXPos);
                        tempPiece.setY(finishYPos);
                        board[finishXPos][finishYPos].setPiece(tempPiece);
                        if (board[finishXPos][finishYPos + 1].getPiece() == null) {
                            tempPiece2.setX(finishXPos);
                            tempPiece2.setY(finishYPos + 1);
                            board[finishXPos][finishYPos + 1].setPiece(tempPiece2);
                            board[startXPos][startYPos].setPiece(null);
                            board[startXPos][finishYPos - 2].setPiece(null);
                        }
                    }
                }
                if (move.charAt(2) == 'g') {
                    if (board[startXPos][finishYPos + 1].getPiece() != null){
                        Piece tempPiece2 = board[startXPos][finishYPos + 1].getPiece();
                        tempPiece.setX(finishXPos);
                        tempPiece.setY(finishYPos);
                        board[finishXPos][finishYPos].setPiece(tempPiece);
                        if (board[finishXPos][finishYPos - 1].getPiece() == null) {
                            tempPiece2.setX(finishXPos);
                            tempPiece2.setY(finishYPos - 1);
                            board[finishXPos][finishYPos - 1].setPiece(tempPiece2);
                            board[startXPos][startYPos].setPiece(null);
                            board[startXPos][finishYPos + 1].setPiece(null);
                        }
                    }
                }

            }
        }

        if (move.length() == 4) {
            if (board[startXPos][startYPos].getPiece() != null) {
                board[startXPos][startYPos].getPiece().setHasMoved();
                Piece tempPiece = board[startXPos][startYPos].getPiece();
                if (tempPiece instanceof Pawn && startYPos != finishYPos) {
                    if (board[finishXPos][finishYPos].getPiece() == null) {
                        board[startXPos][startYPos].setPiece(null);
                        tempPiece.setX(finishXPos);
                        tempPiece.setY(finishYPos);
                        board[finishXPos][finishYPos].setPiece(tempPiece);
                        board[startXPos][finishYPos].setPiece(null);
                    } else {
                        board[startXPos][startYPos].setPiece(null);
                        tempPiece.setX(finishXPos);
                        tempPiece.setY(finishYPos);
                        board[finishXPos][finishYPos].setPiece(tempPiece);
                    }
                } else {
                    board[startXPos][startYPos].setPiece(null);
                    tempPiece.setX(finishXPos);
                    tempPiece.setY(finishYPos);
                    board[finishXPos][finishYPos].setPiece(tempPiece);
                }
            }
        }

        if (move.length() == 5) {
            if (board[startXPos][startYPos].getPiece() != null) {
                Queen tempPiece = new Queen(board[startXPos][startYPos].getPiece().color, finishXPos, finishYPos);

                board[startXPos][startYPos].setPiece(null);
                board[finishXPos][finishYPos].setPiece(tempPiece);

                Engine.getInstance().resetArray(board, Engine.getInstance().getEnginePieces(), Engine.getInstance().getOpponentPieces());
            }
        }
    }

    public static String toXboard(int x) {
        String board = "abcdefgh";
        return String.valueOf(board.charAt(x));
    }


    public static double evaluate(String side, ArrayList<Move> moves) {
        Evaluation eval = new Evaluation();
        return eval.getResult(side, moves);
    }
}
