package Main;

public class Move {
    public String string;
    public double score;

    public Move() {
        this.string = null;
        score = -1;
    }

    public Move(String s, double x) {
        this.string = s;
        this.score = x;
    }

    public Move(Move m) {
        this.score = m.score;
        this.string = m.string;
    }

    public Move(double x) {
        this.string = "";
        this.score = x;
    }

    public String toString() {
        return "(" + this.string + ", " + this.score + ")";
    }
}
