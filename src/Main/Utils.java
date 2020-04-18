package Main;

import Pieces.*;

import javax.swing.*;
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

        if (move.length() == 4) {
            if (board[startXPos][startYPos].getPiece() != null) {
                board[startXPos][startYPos].getPiece().setHasMoved();
                Piece tempPiece = board[startXPos][startYPos].getPiece();

                board[startXPos][startYPos].setPiece(null);
                tempPiece.setX(finishXPos);
                tempPiece.setY(finishYPos);
                board[finishXPos][finishYPos].setPiece(tempPiece);
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


    public static double evaluate(String side) {
        Evaluation eval = new Evaluation();
        return eval.getResult(side);
    }
}
