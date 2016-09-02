import java.util.HashMap;

/**
 * Created by Zach on 8/31/16.
 */
public class ATM {
    public String name;
    public double balance;
    public String menuInput;
    public String option;
    boolean accountExists;
    HashMap<String, Double> accountInfo = new HashMap<>();


    public ATM() {
        accountInfo.put("Zach" , 500.00);
    }

    public void homeScreen() throws Exception{
        System.out.println("Welcome to Gringotts Wizarding Bank!!\nWhat is your name?");
        name = Main.scanner.nextLine();
        if(name.length() > 0) {
        accountExists = accountInfo.containsKey(name);

            if ( accountExists ) {
            System.out.println("Welcome " + name + ".");
            menuSelect();
            }
            else {
                createAccount();
            }
        }
        else {
            System.out.println("Please enter a valid name\n");
            homeScreen();
        }
    }

    public void menuSelect() throws Exception {
        System.out.println("What would you like to do?:" +
                "\n 1. Check Balance  2. Withdraw Funds  3. Deposit Funds  4. Cancel  5. DELETE ACCOUNT");
        boolean a = true;
        boolean b = true;

        option = Main.scanner.nextLine();

        switch (option) {
            case "check balance" :
            case "1" : System.out.println("Your current balance is " + accountInfo.get(name) + " Galleons.");
                menuSelect();
                break;
            case "withdraw funds" :
            case "2" :
                while (a) {
                    a = withdrawFunds();
                }
                menuSelect();
                break;
            case "cancel" :
            case "4" : System.out.println("Transaction canceled. See you next time.\n");
                homeScreen();
                break;
            case "deposit" :
            case "3" :
                while (b) {
                   b = depositFunds();
                }
                menuSelect();
                break;
            case "delete account" :
            case "5" :
                deleteAccount();
                break;
            default: menuSelect();
        }
    }

    public boolean withdrawFunds() {
        Double amountInput = new Double(0);
        boolean a = true;
        System.out.println("Please enter the amount to be withdrawn.");
        while (amountInput <= 0) {
            amountInput = validatePositiveDouble();
        }
        if(amountInput < accountInfo.get(name)){
            balance = accountInfo.get(name) - amountInput;
            accountInfo.replace(name, balance);
            System.out.println("Here is your money. \nYour current remaining balance is " + balance + " Galleons.");
            a = false;
        }
        else {
            System.out.println("Do not attempt to fool the Gringotts Goblins. Enter a valid amount to be withdrawn.");
        }
        return a;
    }

    public void createAccount() throws Exception{
        System.out.println("That name is not in our records.\nWould you like to create an account? [Yes/No]");
        Double numOption = new Double(0);
        option = Main.scanner.nextLine();
        if(option.equalsIgnoreCase("yes")){
            while (numOption <= 0) {
                System.out.println("Your account has been created! Welcome " + name +
                        ".\nPlease enter the amount you wish to deposit into your new account.");
                numOption = validatePositiveDouble();
            }
            accountInfo.put(name, numOption);
        }
        else{
            System.out.println("Maybe next time... Please come again. \n\nNEW SESSION\n");
            homeScreen();
        }
    }

    public void deleteAccount() throws Exception{
        System.out.println("This will destroy all records of your account at Gringotts. Please make certain you have withdrawn all your funds before continuing.\n" +
                "Are you sure you want to delete your account? [Yes/No]");
        option = Main.scanner.nextLine();
        if(option.equalsIgnoreCase("yes")){
            System.out.println("Your account has been DELETED!!");
            accountInfo.remove(name);
            homeScreen();
        }
        else{
            System.out.println("Your account has NOT been deleted.");
            menuSelect();
        }
    }

    public boolean depositFunds() {
        Double amountInput = new Double(0);
        boolean b = true;
        System.out.println("Please enter the amount to be deposited.");
        while (amountInput <= 0) {
            amountInput = validatePositiveDouble();
        }
        if(amountInput >=0) {
            balance = accountInfo.get(name) + amountInput;
            accountInfo.replace(name, balance);
            System.out.println("Deposit complete. \nYour new balance is $" + balance);
            b = false;
        }
        else {
            System.out.println("Please enter a valid amount for the deposit.");
        }
        return b;
    }

    public Double validatePositiveDouble() {
        Double numOption = new Double(0);
        try {
            numOption = Double.parseDouble(Main.scanner.nextLine());
        } catch (Exception wrongNumber) {
            System.out.println("Please Enter a valid amount.");
        }
        return numOption;
    }

}
