package Main;

public class Move {
    public String string;
    public int score;

    public Move() {
        this.string = null;
        score = -1;
    }

    public Move(Move m) {
        this.score = m.score;
        this.string = m.string;
    }

    public Move(int x) {
        this.string = "";
        this.score = x;
    }

    public String toString() {
        return "(" + this.string + ", " + this.score + ")";
    }
}
