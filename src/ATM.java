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
        System.out.println("Welcome to Benton's Bank!!\nPlease enter your name.");
        name = Main.scanner.nextLine();
        accountExists = accountInfo.containsKey(name);

        if ( accountExists ) {
            System.out.println("Welcome " + name + ".");
            menuSelect();
        }
        else {
            createAccount();
        }
    }

    public void menuSelect() throws Exception {
        System.out.println("Please enter the number of your selection: \n 1. Check Balance  2. Withdraw Funds  3. Cancel");
        boolean a = true;

        option = Main.scanner.nextLine();

        switch (option) {
            case "1" : System.out.println("Your current balance is $" + accountInfo.get(name));
                menuSelect();
                break;
            case "2" :
                while (a) {
                    a = withdrawFunds();
                }
                menuSelect();
                break;
            case "3" : System.out.println("Transaction canceled. Please come again.\n");
                homeScreen(); //this takes user2 back to homescreen to run that method one more time. but at that point menuSelect has already run once in main...
                break;
            case "4" :
                // deposit funds
                break;
            case "5" :
                // end program
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
            System.out.println("Please take your money. \nYour remaining balance is $" + balance);
            a = false;
        }
        else {
            System.out.println("Insufficient Funds!!");
        }
        return a;
    }

    public void createAccount() throws Exception{
        System.out.println("That account name is not recognized.\nWould you like to create an account? [Yes/No]");
        Double numOption = new Double(0);  //this is set to zero so that the while loop automaticall starts without the user input
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
            System.out.println("Maybe next time. Please come again. \nNEW SESSION\n");
            homeScreen();
        }

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
