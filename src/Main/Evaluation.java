package Main;

import java.util.ArrayList;

public class Evaluation {
    int pawnValue = 100;
    int horseValue = 320;
    int bishopValue = 330;
    int rookValue = 500;
    int queenValue = 900;
    int kingValue = 20000;
    int[][] pawnEvalBlack = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    int[][] pawnEvalWhite = reverseArray(pawnEvalBlack);
    int[][] horseEval = {
            {-50, -40, -30, -30, -30, -30, -40, -50},
            {-40, -20, 0, 0, 0, 0, -20, -40},
            {-30, 0, 10, 15, 15, 10, 0, -30},
            {-30, 5, 15, 20, 20, 15, 5, -30},
            {-30, 0, 15, 20, 20, 15, 0, -30},
            {-30, 5, 10, 15, 15, 10, 5, -30},
            {-40, -20, 0, 5, 5, 0, -20, -40},
            {-50, -40, -30, -30, -30, -30, -40, -50}
    };
    int [][] bishopEvalBlack = {
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,0,0,0,0,0,0,-10},
            {-10,0,5,10,10,5,0,-10},
            {-10,5,5,10,10,5,5,-10},
            {-10,0,10,10,10,10,0,-10},
            {-10,10,10,10,10,10,10,-10},
            {-10,5,0,0,0,0,5,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}

    };
    int[][] bishopEvalWhite = reverseArray(bishopEvalBlack);
    int[][] rookEvalBlack = {
            {0,  0,  0,  0,  0,  0,  0,  0},
            {5, 10, 10, 10, 10, 10, 10,  5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {0,  0,  0,  5,  5,  0,  0,  0}
    };
    int[][] rookEvalWhite = reverseArray(rookEvalBlack);
    int[][] evalQueen = {
            {-20, -10, -10, -5, -5, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 0, 5, 5, 5, 5, 0, -10},
            {-5, 0, 5, 5, 5, 5, 0, -5},
            {0, 0, 5, 5, 5, 5, 0, -5},
            {-10, 5, 5, 5, 5, 5, 0, -10},
            {-10, 0, 5, 0, 0, 0, 0, -10},
            {-20, -10, -10, -5, -5, -10, -10, -20}
    };
    int[][] kingEvalBlack = {
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            {20, 20,  0,  0,  0,  0, 20, 20},
            {20, 30, 10,  0,  0, 10, 30, 20}
    };
    int[][] kingEvalWhite = reverseArray(kingEvalBlack);

    public Evaluation() {

    }

    public int countPiece(char type, char color) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Engine.getInstance().getBoard()[i][j].getPiece() != null) {
                    if (Engine.getInstance().getBoard()[i][j].getPiece().type == type &&
                            Engine.getInstance().getBoard()[i][j].getPiece().color == color) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    int kingPosition(char color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Engine.getInstance().getBoard()[i][j].getPiece() != null) {
                    if (Engine.getInstance().getBoard()[i][j].getPiece().type == 'K' &&
                            Engine.getInstance().getBoard()[i][j].getPiece().color == color) {
                        if (color == 'W') {
                            return kingEvalWhite[i][j];
                        } else {
                            return kingEvalBlack[i][j];
                        }
                    }
                }
            }
        }
        return 1;
    }

    int queenPosition(char color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Engine.getInstance().getBoard()[i][j].getPiece() != null) {
                    if (Engine.getInstance().getBoard()[i][j].getPiece().type == 'Q' &&
                            Engine.getInstance().getBoard()[i][j].getPiece().color == color) {
                        return evalQueen[i][j];
                    }
                }
            }
        }
        return 1;
    }

    int rookPosition(char color) {
        int position = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Engine.getInstance().getBoard()[i][j].getPiece() != null) {
                    if (Engine.getInstance().getBoard()[i][j].getPiece().type == 'R' &&
                            Engine.getInstance().getBoard()[i][j].getPiece().color == color) {
                        if (color == 'W') {
                            position += rookEvalWhite[i][j];
                        } else {
                            position += rookEvalBlack[i][j];
                        }
                    }
                }
            }
        }
        return position;
    }

    int horsePosition(char color) {
        int position = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Engine.getInstance().getBoard()[i][j].getPiece() != null) {
                    if (Engine.getInstance().getBoard()[i][j].getPiece().type == 'H' &&
                            Engine.getInstance().getBoard()[i][j].getPiece().color == color) {
                        position += horseEval[i][j];
                    }
                }
            }
        }
        return position;
    }

    int bishopPosition(char color) {
        int position = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Engine.getInstance().getBoard()[i][j].getPiece() != null) {
                    if (Engine.getInstance().getBoard()[i][j].getPiece().type == 'R' &&
                            Engine.getInstance().getBoard()[i][j].getPiece().color == color) {
                        if (color == 'W') {
                            position += bishopEvalWhite[i][j];
                        } else {
                            position += bishopEvalBlack[i][j];
                        }
                    }
                }
            }
        }
        return position;
    }

    int pawnPosition(char color) {
        int position = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Engine.getInstance().getBoard()[i][j].getPiece() != null) {
                    if (Engine.getInstance().getBoard()[i][j].getPiece().type == 'R' &&
                            Engine.getInstance().getBoard()[i][j].getPiece().color == color) {
                        if (color == 'W') {
                            position += pawnEvalWhite[i][j];
                        } else {
                            position += pawnEvalBlack[i][j];
                        }
                    }
                }
            }
        }
        return position;
    }

    public double getResult(String side, ArrayList<Move> moves) {
        int evalScore;
        int w2m;
        if (Engine.getInstance().getSide().equals("black")) {
            w2m = -1;
        } else {
            w2m = 1;
        }
        if (Engine.getInstance().checkMate(moves)) {
            return Double.POSITIVE_INFINITY * w2m;
        }

        if (Engine.getInstance().checkBoard() != 0) {
            return 9999 * w2m;
        }

        int materialScore = 0;

        materialScore += kingValue * (countPiece('K', 'B') - countPiece('K', 'W'))
                + queenValue * (countPiece('Q', 'B')- countPiece('Q', 'W'))
                + rookValue * (countPiece('R', 'B')- countPiece('R', 'W'))
                + horseValue * (countPiece('H', 'B')- countPiece('H', 'W'))
                + bishopValue * (countPiece('B', 'B') - countPiece('B', 'W'))
                + pawnValue * (countPiece('P', 'B')- countPiece('P', 'W'));

        evalScore = materialScore + kingPosition('B') - kingPosition('W')
                    + queenPosition('B') - queenPosition('W')
                    + rookPosition('B') - rookPosition('W')
                    + horsePosition('B') - horsePosition('W')
                    + bishopPosition('B') - bishopPosition('W')
                    + pawnPosition('B') - pawnPosition('W');

        evalScore = evalScore * w2m;

        return evalScore;
    }

    public int[][] reverseArray(int[][] array) {
        int[][] temp;
        temp = array.clone();

        for (int i = 0, k = 7; i < 4; i++, k--) {
            int[] aux = temp[k];
            temp[k] = temp[i];
            temp[i] = aux;
        }

        return temp;
    }
}
