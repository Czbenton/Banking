import java.util.Scanner;
public class Main {

    public static Scanner scanner = new Scanner(System.in);

    static ATM atm = new ATM();

    public static void main(String[] args) throws Exception {

        atm.homeScreen();
        while ( cancelIsNotSelected() ) {
            atm.menuSelect();
        }

    }

    private static boolean cancelIsNotSelected() {
        return atm.option == null || ! atm.option.equals("3");
    }
}
