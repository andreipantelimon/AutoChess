public class Utils {
    public static int getIndexOfLetter(char letter) {
        int tempInt = 96;
        if (letter <= 122 && letter >= 97) {
            return (int) letter - tempInt - 1;
        }
        return -1;
    }
}
