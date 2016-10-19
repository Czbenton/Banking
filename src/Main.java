import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    static ATM atm = new ATM();

    public static void main(String[] args) throws Exception {

        while (true) {

            if (atm.isTxnFinished()) {
                atm.name = "";
                atm.homeScreen();
            }
            atm.menuSelect();
        }
    }
}