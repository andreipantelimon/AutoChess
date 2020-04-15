package Main;

import Pieces.Piece;

public class Utils {
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

        if (board[startXPos][startYPos].piece != null) {
            Piece tempPiece = board[startXPos][startYPos].piece;
            board[startXPos][startYPos].piece = null;
            board[finishXPos][finishYPos].piece = tempPiece;
        }
    }

    public static String toXboard(int x) {
        String board = "abcdefgh";
        return String.valueOf(board.charAt(x));
    }
}
