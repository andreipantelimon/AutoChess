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
    private String lastSide = "";

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
        String str = "0";
        if (x == 0) {
            str = "a";
        } else {
            if (x == 1) {
                str = "b";
            } else {
                if (x == 2) {
                    str = "c";
                } else {
                    if (x == 3) {
                        str = "d";
                    } else {
                        if (x == 4) {
                            str = "e";
                        } else {
                            if (x == 5) {
                                str = "f";
                            } else {
                                if (x == 6) {
                                    str = "g";
                                } else {
                                    if (x == 7) {
                                        str = "h";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return str;
    }

    public void generateMove() {
        String move = "0";
        String color = getSide();
        char side = '0';
        if (color == "black") {
            side = 'B';
        } else {
            if (color == "white") {
                side = 'W';
            }
        }
        boolean moveDone = false;
        for (int i = 1; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece temp = board[i][j].piece;
                if (board[i][j].piece instanceof Pawn) {
                    if (board[i][j].piece.color == side) {
                        if (side == 'B') {
                                if (board[i - 1][j].piece == null) {
                                    board[i - 1][j].setPiece(temp);
                                    board[i][j].setPiece(null);
                                    move = toXboard(j) + (i + 1) + toXboard(j) + (i - 1 + 1);
                                    moveDone = true;
                                    break;
                                } else {
                                    if (j < 7) {
                                        if (board[i - 1][j + 1].piece != null) {
                                            if (board[i - 1][j + 1].piece.color != side) {
                                                board[i - 1][j + 1].setPiece(temp);
                                                board[i][j].setPiece(null);
                                                move = toXboard(j) + (i + 1) + toXboard(j + 1) + (i - 1 + 1);
                                                moveDone = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (j > 0) {
                                        if (board[i - 1][j - 1].piece != null) {
                                            if (board[i - 1][j - 1].piece.color != side) {
                                                board[i - 1][j - 1].setPiece(temp);
                                                board[i][j].setPiece(null);
                                                move = toXboard(j) + (i + 1) + toXboard(j - 1) + (i - 1 + 1);
                                                moveDone = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                        } else {
                            if (side == 'W') {
                                if (i < 7) {
                                    if (board[i + 1][j].piece == null) {
                                        board[i + 1][j].setPiece(temp);
                                        board[i][j].setPiece(null);
                                        move = toXboard(j) + (i + 1) + toXboard(j) + (i + 1 + 1);
                                        moveDone = true;
                                        break;
                                    } else {
                                        if (j < 7) {
                                            if (board[i + 1][j + 1].piece != null) {
                                                if (board[i + 1][j + 1].piece.color != side) {
                                                    board[i + 1][j + 1].setPiece(temp);
                                                    board[i][j].setPiece(null);
                                                    move = toXboard(j) + (i + 1) + toXboard(j + 1) + (i + 1 + 1);
                                                    moveDone = true;
                                                    break;
                                                }
                                            }
                                        }
                                        if (j > 0) {
                                            if (board[i + 1][j - 1].piece != null) {
                                                if (board[i + 1][j - 1].piece.color != side) {
                                                    board[i + 1][j - 1].setPiece(temp);
                                                    board[i][j].setPiece(null);
                                                    move = toXboard(j) + (i + 1) + toXboard(j - 1) + (i + 1 + 1);
                                                    moveDone = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
            if (moveDone) {
                System.out.println("move " + move);
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

        System.out.println("# " + startXPos + " " + startYPos + " " + finishXPos + " " + finishYPos);

        if (board[startXPos][startYPos].piece != null) {
            Piece tempPiece = board[startXPos][startYPos].piece;
            board[startXPos][startYPos].piece = null;
            board[finishXPos][finishYPos].piece = tempPiece;
        }
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSide() {
        return this.side;
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

    public String getLastSide() {
        return this.lastSide;
    }

    public void setLastSide(String side) {
        this.lastSide = side;
    }
}
