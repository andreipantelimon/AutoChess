import Pieces.*;

import java.util.Arrays;

public class Engine {
    private static Engine engine = null;
    public BoardCell[][] board;
    private String side = "black";
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

    public String toXboard(int x) {
        String board = "abcdefgh";
        return String.valueOf(board.charAt(x));
    }

    public boolean check() {
        char color = getColor();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].piece instanceof King) {
                    if (color == 'B') {
                        if (i > 0) {
                            if (board[i - 1][j - 1].piece != null) {
                                if (board[i - 1][j - 1].piece.color != color && board[i - 1][j - 1].piece instanceof Pawn) {
                                    return true;
                                }
                            }
                            if (j < 7) {
                                if (board[i - 1][j + 1].piece != null) {
                                    if (board[i - 1][j + 1].piece.color != color && board[i - 1][j + 1].piece instanceof Pawn) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    if (color == 'W') {
                        if (i < 7) {
                            if (board[i + 1][j - 1].piece != null) {
                                if (board[i + 1][j - 1].piece.color != color && board[i + 1][j - 1].piece instanceof Pawn) {
                                    return true;
                                }
                            }
                            if (j < 7) {
                                if (board[i + 1][j + 1].piece != null) {
                                    if (board[i + 1][j + 1].piece.color != color && board[i + 1][j + 1].piece instanceof Pawn) {
                                        return true;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return false;
    }

    public boolean eatPawn(int firstx, int firsty, int lastx, int lasty) {
        String move;
        char color = getColor();
        Piece temp = board[firstx][firsty].piece;
        if (board[lastx][lasty].piece != null) {
            if (board[lastx][lasty].piece.color != color) {
                move = toXboard(firsty) + (firstx + 1) + toXboard(lasty) + (lastx + 1);
                System.out.println("move " + move);
                board[firstx][firsty].setPiece(null);
                board[lastx][lasty].setPiece(temp);
                return true;
            }
        }
        return false;
    }


    public boolean movePawn(int firstx, int firsty, int lastx, int lasty) {
        String move;
        Piece temp = board[firstx][firsty].piece;
        if (board[lastx][lasty].piece == null) {
            move = toXboard(firsty) + (firstx + 1) + toXboard(lasty) + (lastx + 1);
            System.out.println("move " + move);
            board[firstx][firsty].setPiece(null);
            board[lastx][lasty].setPiece(temp);
            return true;
        }
        return false;
    }

    public char getColor() {
        return (getSide().equals("black")? 'B':'W');
    }

    public void generateMove() {
        boolean moveDone = false;
        for (int i = 1; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (check()) {
                    break;
                }
                if (board[i][j].piece != null) {
                    if (board[i][j].piece instanceof Pawn && board[i][j].piece.color == getColor()) {
                        if (getColor() == 'B') {
                            moveDone = movePawn(i, j, i - 1, j);
                            if (!moveDone && j < 7) {
                                moveDone = eatPawn(i, j, i - 1, j + 1);
                            }
                            if (!moveDone && j > 0) {
                                moveDone = eatPawn(i, j, i - 1, j - 1);
                            }
                            if (moveDone) {
                                break;
                            }
                        }
                        if (getColor() == 'W') {
                            if (i < 7) {
                                moveDone = movePawn(i, j, i + 1, j);
                                if (!moveDone && j < 7) {
                                    moveDone = eatPawn(i, j, i + 1, j + 1);
                                }
                                if (!moveDone && j > 0) {
                                    moveDone = eatPawn(i, j, i + 1, j - 1);
                                }
                                if (moveDone) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (moveDone) {
                break;
            }
        }
        if (!moveDone) {
            System.out.println("resign");
        }
    }

    public void xboardMoves(String move) {
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
}
