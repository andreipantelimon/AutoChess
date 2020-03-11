import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String side = "black";
        Scanner scanner = new Scanner(System.in);
        String input;
        int time = 0;
        int opponentTime = 0;
        int movesPerTime = 0;
        int minutes = 0;
        int pondering = 0;
        int x = 1;

        while(true) {
            //infoBox("to read", "still works");
            input = "";
            input = scanner.nextLine();

            //infoBox("input" + input, "still works");

            if (input.equals("xboard")) {
                Engine.getInstance().initializeBoard();

                //infoBox("xboard hit", "still works");

            } else if (input.equals("quit")) {
                //infoBox("quit hit", "still works");

                System.exit(0);
            } else if (input.equals("force")) {
                side = "both";

                //infoBox("force hit", "still works");

            } else if (input.equals("new")) {
                side = "black";

                Engine.getInstance().initializeBoard();

                //infoBox("new hit", "still works");

            } else if (input.equals("go")) {

                infoBox("go hit", "still works");

                //continue with corresponding side

            } else if (input.equals("resign")) {

                infoBox("resign hit", "still works");

                break;

            } else if(input.equals("black")) {

                infoBox("black hit", "still works");

            } else if(input.equals("white")) {

                infoBox("white hit", "still works");

            } else if (side.equals("both")){
                Engine.getInstance().xboardMoves(input);

            } else if (input.equals("print")) {
                Engine.getInstance().printBoard();
            } else if (input.startsWith("time")) {
                time = Integer.parseInt(input.split(" ")[1]);
            }  else if (input.startsWith("otim")) {
                opponentTime = Integer.parseInt(input.split(" ")[1]);
            } else if (input.startsWith("protover")) {
                System.out.println("feature done=1 usermove=1 sigint=0");
            }  else if (input.startsWith("accepted")) {
                System.out.println("accepted");
            } else if(input.equals("random")) {
                continue;
            } else if (input.startsWith("level")) {
                movesPerTime = Integer.parseInt(input.split(" ")[1]);
                minutes = time = Integer.parseInt(input.split(" ")[2]);
            }  else if (input.equals("post")) {
                System.out.println("1 2 3 4 e1b2");
            }  else if (input.equals("hard")) {
                pondering = 1;
            } else if (input.startsWith("usermove") && side != "both") {
                //System.out.println("resign");
                String move = input.split(" ")[1];
                //infoBox("to move piece", "still works");
                Engine.getInstance().xboardMoves(move);
                //infoBox("white piece moved", "still works");
                Engine.getInstance().generateMove();
                //infoBox("black piece moved", "still works");
            } else {
                System.out.println("Error (unknown): " + input);

            }
        }
    }
}
