import java.util.HashMap;

/**
 * Created by Zach on 8/31/16.
 */
public class ATM {
    final static String TXN_FINISHED = "4";
    public String name = "";
    public double balance;
    public String option = TXN_FINISHED;  //default to Start
    HashMap<String, Double> accountInfo = new HashMap<>();

    public ATM() {
        accountInfo.put("Zach", 500.00);
    }

    public boolean isTxnFinished() {
        return option.equals(TXN_FINISHED);
    }

    public void homeScreen() throws Exception {
        System.out.println("Welcome to Gringotts Wizarding Bank!!\nWhat is your name?");
        while (name.isEmpty()) {
            name = Main.scanner.nextLine();

            if (name.isEmpty()) {
                System.out.println("Please enter a valid name\n");
            }
        }
        if (accountInfo.containsKey(name)) {
            System.out.println("Welcome " + name + ".");
        } else {
            createAccount();
        }
    }

    public void menuSelect() throws Exception {
        boolean cancel = false;

        while (!cancel) {
            System.out.println("What would you like to do?:" +
                    "\n 1. Check Balance  2. Withdraw Funds  3. Deposit Funds  4. Cancel  5. DELETE ACCOUNT");
            option = Main.scanner.nextLine();
            boolean a = true;
            boolean b = true;

            switch (option.toLowerCase()) {
                case "check balance":
                case "1":
                    System.out.println("Your current balance is " + accountInfo.get(name) + " Galleons.");
                    break;
                case "withdraw funds":
                case "2":
                    while (a) {
                        a = withdrawFunds();
                    }
                    break;
                case "cancel":
                case "4":
                    System.out.println("Transaction canceled. See you next time.\n");
                    cancel = true;
                    break;
                case "deposit funds":
                case "3":
                    while (b) {
                        b = depositFunds();
                    }
                    break;
                case "delete account":
                case "5":
                    deleteAccount();
                    break;
                case "/exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter valid option.");
            }
        }
    }

    public boolean withdrawFunds() {

        double amountInput;
        boolean a = true;

        System.out.println("Please enter the amount to be withdrawn.");

        amountInput = scanValidDouble();

        if (amountInput < accountInfo.get(name)) {
            balance = accountInfo.get(name) - amountInput;
            accountInfo.replace(name, balance);
            System.out.println("Here is your money. \nYour current remaining balance is " + balance + " Galleons.\n");
            a = false;

        } else {
            System.out.println("Do not attempt to fool the Gringotts Goblins. Enter a valid amount to be withdrawn.");
        }

        return a;
    }

    public void createAccount() throws Exception {

        System.out.println("That name is not in our record books.\nWould you like to create an account? [Yes/No]");
        Double numOption = new Double(0);
        boolean runSwitchAgain = true;
        option = Main.scanner.nextLine();

        while (runSwitchAgain) {
            runSwitchAgain = false;
            switch (option.toLowerCase()) {
                case "yes":
                    while (numOption <= 0) {
                        System.out.println("Your account has been created! Welcome " + name +
                                ".\nPlease enter the amount you wish to deposit into your new account.");
                        numOption = scanValidDouble();
                    }
                    accountInfo.put(name, numOption);
                    break;
                case "no":
                    System.out.println("Maybe next time... Please come again. \n\nNEW SESSION\n");
                    name = "";
                    homeScreen();
                    break;
                default:
                    System.out.println("Please enter [yes] or [no]");
                    runSwitchAgain = true;
                    option = Main.scanner.nextLine();
                    break;
            }
        }
    }

    public void deleteAccount() throws Exception {

        System.out.println("This will destroy all records of your account at Gringotts. Please make certain you have withdrawn" +
                " all your funds before continuing.\n" + "Are you sure you want to delete your account? [Yes/No]");
        boolean runSwitchAgain = true;
        option = Main.scanner.nextLine();

        while (runSwitchAgain) {
            runSwitchAgain = false;
            switch (option.toLowerCase()) {
                case "yes":
                    System.out.println("Your account has been DELETED!!\n");
                    accountInfo.remove(name);
                    name = "";
                    homeScreen();
                    break;
                case "no":
                    System.out.println("Your account has NOT been deleted.");
                    menuSelect();
                    break;
                default:
                    System.out.println("Please enter [yes] or [no]");
                    runSwitchAgain = true;
                    option = Main.scanner.nextLine();
                    break;
            }
        }
    }

    public boolean depositFunds() {

        Double amountInput = new Double(0);
        boolean b = true;

        System.out.println("Please enter the amount to be deposited.");

        while (amountInput <= 0) {
            amountInput = scanValidDouble();
        }
        if (amountInput >= 0) {
            balance = accountInfo.get(name) + amountInput;
            accountInfo.replace(name, balance);
            System.out.println("Deposit complete. \nYour new balance is $" + balance + "Galleons.\n");
            b = false;
        } else {
            System.out.println("Please enter a valid amount for the deposit.");
        }
        return b;
    }

    public Double scanValidDouble() {

        Double numOption = new Double(0);
        while (numOption <= 0) {
            try {
                numOption = Double.parseDouble(Main.scanner.nextLine());
                if (numOption <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\nPlease Enter a valid amount.");
            }
        }
        return numOption;
    }
}