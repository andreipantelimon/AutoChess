package Main;

public class Evaluation {
    int pawnValue = 100;
    int horseValue = 320;
    int bishopValue = 330;
    int rookValue = 500;
    int queenValue = 900;
    int kingValue = 20000;
    int[][] pawnEvalWhite = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    int[][] pawnEvalBlack = reverseArray(pawnEvalWhite);
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
    int [][] bishopEvalWhite = {
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,0,0,0,0,0,0,-10},
            {-10,0,5,10,10,5,0,-10},
            {-10,5,5,10,10,5,5,-10},
            {-10,0,10,10,10,10,0,-10},
            {-10,10,10,10,10,10,10,-10},
            {-10,5,0,0,0,0,5,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}

    };
    int[][] bishopEvalBlack = reverseArray(bishopEvalWhite);
    int[][] rookEvalWhite = {
            {0,  0,  0,  0,  0,  0,  0,  0},
            {5, 10, 10, 10, 10, 10, 10,  5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {-5, 0,  0,  0,  0,  0,  0, -5},
            {0,  0,  0,  5,  5,  0,  0,  0}
    };
    int[][] rookEvalBlack = reverseArray(rookEvalWhite);
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
    int[][] kingEvalWhite = {
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            {20, 20,  0,  0,  0,  0, 20, 20},
            {20, 30, 10,  0,  0, 10, 30, 20}
    };
    int[][] kingEvalBlack = reverseArray(kingEvalWhite);

    public void printBoards() {
        //System.out.println(pawnEvalBlack[1][1]);
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

    public double getResult(String side) {
        printBoards();
        int evalScore = 0;
        int w2m;
        if (side.equals(Engine.getInstance().getSide())) {
            w2m = 1;
        } else {
            w2m = -1;
        }
        if (Engine.getInstance().checkMate(side)) {
            return Double.POSITIVE_INFINITY * w2m;
        }

        evalScore += kingValue * ((countPiece('K', 'B') * kingPosition( 'B') - countPiece('K', 'W') * kingPosition('W')) * w2m)
                + queenValue * ((countPiece('Q', 'B') * queenPosition('B') - countPiece('Q', 'W') * queenPosition('W')) * w2m)
                + rookValue * ((countPiece('R', 'B') * rookPosition('B') - countPiece('R', 'W') * rookPosition('W')) * w2m)
                + horseValue * ((countPiece('H', 'B') * horsePosition('B') - countPiece('H', 'W') * horsePosition('W')) * w2m)
                + bishopValue * ((countPiece('B', 'B') * bishopPosition('B') - countPiece('B', 'W') * bishopPosition('W')) * w2m)
                + pawnValue * ((countPiece('P', 'B') * pawnPosition('B') - countPiece('P', 'W') * pawnPosition('W')) * w2m);

//        evalScore += kingValue * ((countPiece('K', 'B') - countPiece('K', 'W')) * w2m)
//                + queenValue * ((countPiece('Q', 'B')- countPiece('Q', 'W')) * w2m)
//                + rookValue * ((countPiece('R', 'B')- countPiece('R', 'W')) * w2m)
//                + horseValue * ((countPiece('H', 'B')- countPiece('H', 'W')) * w2m)
//                + bishopValue * ((countPiece('B', 'B') - countPiece('B', 'W')) * w2m)
//                + pawnValue * ((countPiece('P', 'B')- countPiece('P', 'W')) * w2m);

        //System.out.println("evalscore: " + evalScore);
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
