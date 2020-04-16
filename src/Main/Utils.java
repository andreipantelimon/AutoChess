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

        if (board[startXPos][startYPos].getPiece() != null) {
            Piece tempPiece = board[startXPos][startYPos].getPiece();

            board[startXPos][startYPos].setPiece(null);
            tempPiece.setX(finishXPos);
            tempPiece.setY(finishYPos);
            board[finishXPos][finishYPos].setPiece(tempPiece);
        }
    }

    public static String toXboard(int x) {
        String board = "abcdefgh";
        return String.valueOf(board.charAt(x));
    }

//    public static boolean checkLines(int i, int ik, int j, int jk, King king, BoardCell[][] board) {
//        boolean OK = false;
//        for (int k = 1; k <= 7; k++) {
//            if (i + ik * k >= 0 && i + ik * k <= 7 && j + jk * k >= 0 && j + jk * k <= 7) {
//                if (board[i + ik * k][j + jk * k].piece != null) {
//                    if (board[i + ik * k][j + jk * k].piece.color != king.color && ((board[i + ik * k][j + jk * k].piece instanceof Rook) ||
//                            (board[i + ik * k][j + jk * k].piece instanceof Queen))) {
//                        OK = true;
//                        //infoBox("Sah Linie", "SAL");
//                    } else {
//                        OK = false;
//                        return OK;
//                    }
//                }
//            }
//        }
//        return OK;
//    }
//
//    public static boolean checkDiag(int i, int ik, int j, int jk, King king, BoardCell[][] board) {
//        boolean OK = false;
//        for (int k = 1; k <= 7; k++) {
//            if (i + ik * k >= 0 && i + ik * k <= 7 && j + jk * k >= 0 && j + jk * k <= 7) {
//                if (board[i + ik * k][j + jk * k].piece != null) {
//                    if (board[i + ik * k][j + jk * k].piece.color != king.color && ((board[i + ik * k][j + jk * k].piece instanceof Queen) ||
//                            (board[i + ik * k][j + jk * k].piece instanceof Bishop))) {
//                        OK = true;
////                        infoBox("Sah Diagonala " + (i + ik * k) + " " +  (j + jk * k), " " + (board[i + ik * k][j + jk * k].piece.color != king.color)
////                                + " " + ((board[i + ik * k][j + jk * k].piece instanceof Queen) ||
////                                (board[i + ik * k][j + jk * k].piece instanceof Bishop)));
//                    } else {
//                        OK = false;
//                        return OK;
//                    }
//                }
//            }
//        }
//        return OK;
//    }
//
//    public static boolean checkPawn(int i, int j, King king, BoardCell[][] board, int k) {
//        if (j + 1 <= 7 && i + k <= 7 && i + k >= 0) {
//            if (board[i + k][j + 1].piece != null) {
//                if (board[i + k][j + 1].piece.color != king.color && (board[i + k][j + 1].piece instanceof Pawn)) {
//                    //infoBox("Sah Pion", "SAL");
//                    return true;
//                }
//            }
//        }
//        if (j - 1 >= 0 && i + k <= 7 && i + k >= 0) {
//            if (board[i + k][j - 1].piece != null) {
//                if (board[i + k][j - 1].piece.color != king.color && (board[i + k][j - 1].piece instanceof Pawn)) {
//                    //infoBox("Sah Pion", "SAL");
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static boolean kingHorse(BoardCell[][] board, char color, int x, int y) {
//        if (x <= 7 && x >= 0 && y <= 7 && y >= 0) {
//            if (board[x][y].piece != null) {
//                return board[x][y].piece instanceof King && board[x][y].piece.color == color;
//            }
//        }
//        return false;
//    }
//
//    public static boolean checkHorse(BoardCell[][] board, char color) {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (board[i][j].piece != null) {
//                    if ((board[i][j].piece instanceof Horse) && board[i][j].piece.color != color) {
//                        if (kingHorse(board, color, i - 1, j + 2)) {
//                            //infoBox("Sah Cal", "SAL");
//                            return true;
//                        }
//                        if (kingHorse(board, color, i - 1, j - 2)) {
//                            return true;
//                        }
//                        if (kingHorse(board, color, i + 1, j + 2)) {
//                            return true;
//                        }
//                        if (kingHorse(board, color, i + 1, j - 2)) {
//                            return true;
//                        }
//                        if (kingHorse(board, color, i + 2, j + 1)) {
//                            return true;
//                        }
//                        if (kingHorse(board, color, i + 2, j - 1)) {
//                            return true;
//                        }
//                        if (kingHorse(board, color, i - 2, j + 1)) {
//                            return true;
//                        }
//                        if (kingHorse(board, color, i - 2, j - 1)) {
//                            return true;
//                        }
//
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public static boolean check(BoardCell[][] board) {
//        char color;
//        if (Engine.getInstance().getSide().equals("black")) {
//            color = 'B';
//        } else {
//            color = 'W';
//        }
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (board[i][j].piece != null) {
//                    if ((board[i][j].piece instanceof King) && board[i][j].piece.color == color) {
//                        King king = (King) board[i][j].piece;
//                        if (color == 'B') {
//                            if (checkPawn(i + 1, j, king, board, 1))
//                                return true;
//                        } else {
//                            if (checkPawn(i - 1, j, king, board, -1))
//                                return true;
//                        }
//                        if (checkHorse(board, color)) {
//                            return true;
//                        }
//                        if (checkLines(i, 1, j, 0, king, board)) {
//                            return true;
//                        }
//                        if (checkLines(i, -1, j, 0, king, board)) {
//                            return true;
//                        }
//                        if (checkLines(i, 0, j, 1, king, board)) {
//                            return true;
//                        }
//                        if (checkLines(i, 0, j, -1, king, board)) {
//                            return true;
//                        }
//                        if (checkDiag(i , 1, j, 1, king, board)) {
//                            return true;
//                        }
//                        if (checkDiag(i, 1, j, -1, king, board)) {
//                            return true;
//                        }
//                        if (checkDiag(i, -1, j, 1, king, board)) {
//                            return true;
//                        }
//                        if (checkDiag(i, -1, j, -1, king, board)) {
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
}
