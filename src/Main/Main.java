package Main;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean analyzeMode = false;

        while(true) {
            input = scanner.nextLine();

            if (input.equals("xboard")) {
                Engine.getInstance().initializeBoard();

            } else if (input.equals("quit")) {
                System.exit(0);

            } else if (input.equals("exit")) {
                analyzeMode = false;

            } else if (input.equals("force")) {
                Engine.getInstance().setSide("both");

            } else if (input.equals("new")) {
                Engine.getInstance().initializeBoard();
                Engine.getInstance().setSide("black");

            } else if (input.equals("go")) {
                Engine.getInstance().generateMove();

            } else if(input.equals("black")) {
                Engine.getInstance().setSide("black");

            } else if(input.equals("white")) {
                Engine.getInstance().setSide("white");

            } else if (input.equals("print")) {
                Engine.getInstance().printBoard();

            } else if (input.startsWith("time")) {
                Engine.getInstance().setEngineTime(Integer.parseInt(input.split(" ")[1]));

            }  else if (input.startsWith("otim")) {
                Engine.getInstance().setOpponentTime(Integer.parseInt(input.split(" ")[1]));

            } else if (input.startsWith("protover")) {
                System.out.println("feature done=1 usermove=1 sigint=0");

            }  else if (input.startsWith("accepted")) {
                System.out.println("accepted");

            } else if(input.equals("random")) {
                //sets random mode

            } else if (input.startsWith("level")) {
                Engine.getInstance().setMovesPerTime(Integer.parseInt(input.split(" ")[1]));
                Engine.getInstance().setTimeInControl(Integer.parseInt(input.split(" ")[2]));

            }  else if (input.equals("post")) {
                //posts thinking message

            }  else if (input.equals("hard")) {
                //turns on thinking in opponents time

            } else if (input.equals("analyze")) {
                Engine.getInstance().setSide("both");
                analyzeMode = true;

            } else if (input.startsWith("usermove") && analyzeMode) {
                String move = input.split(" ")[1];
                Utils.xboardMoves(Engine.getInstance().getBoard(), move);

            } else if (input.startsWith("usermove") && !Engine.getInstance().getSide().equals("both")) {
                String move = input.split(" ")[1];
                //Engine.getInstance().xboardMoves(move);
                Utils.xboardMoves(Engine.getInstance().getBoard(), move);
                Engine.getInstance().generateMove();
                Engine.getInstance().printBoard();

            } else if (input.equals(".")) {
                continue;
            } else if (input.equals("generateMove")) {
                Engine.getInstance().generateAllMoves("black");
                System.out.println(Engine.getInstance().getAllMoves());

            } else {
                System.out.println("Error (unknown): " + input);
            }
        }
    }
}
