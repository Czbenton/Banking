import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    static ATM atm = new ATM();

    public static void main(String[] args) throws Exception {

        while (true) {

            if (atm.option.equals("4")) {
                atm.name = ""; //clear name before running .homescreen again
                atm.homeScreen();
            }
            atm.menuSelect();
        }
    }
}