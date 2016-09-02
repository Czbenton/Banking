import java.util.HashMap;

/**
 * Created by Zach on 8/31/16.
 */
public class ATM {
    public String name;
    public double balance = 100;
    public String menuInput;
    public String option;
    boolean accountExists;
    HashMap<String, Double> accountInfo = new HashMap<>();


    public ATM() {
        accountInfo.put("Zach" , 500.00);
    }

    public void homeScreen() throws Exception{
        System.out.println("Please enter your name.");
        name = Main.scanner.nextLine();
        accountExists = accountInfo.containsKey(name);

        if ( accountExists ) {
            System.out.println("Thank you");
            menuSelect();
        }
        else {
            createAccount();
        }
    }

    public void menuSelect() throws Exception {
        System.out.println("Please enter the number of your selection: \n 1. Check Balance  2. Withdraw Funds  3. Cancel");

        option = Main.scanner.nextLine();

        switch (option) {
            case "1" : System.out.println("Your current balance is $" + accountInfo.get(name));
                menuSelect();
                break;
            case "2" :
                try {
                    withdrawFunds();
                } catch (Exception e) {
                    System.out.println("Input Error: " + e.getMessage());
                }
                withdrawFunds();
                break;
            case "3" : System.out.println("Please come again.");
                homeScreen();
                break;
            default: menuSelect();
        }

    }


    public void withdrawFunds() throws Exception {
        double amountInput;
        System.out.println("Please enter the amount to be withdrawn.");
        amountInput = Double.parseDouble(Main.scanner.nextLine());
        if(amountInput < accountInfo.get(name)){
            balance = accountInfo.get(name) - amountInput;
            accountInfo.replace(name, balance);
            System.out.println("Please take your money. \nYour remaining balance is $" + balance);
            menuSelect();
        }
        else {
            throw new Exception("Insufficient Funds!!");
        }
    }

    public void createAccount() throws Exception{
        System.out.println("That account name is not recognized. Would you like to create an account? [Yes/No]");
        option = Main.scanner.nextLine();
        if(option.equalsIgnoreCase("yes")){
            //add option to accountInfo hashmap
            accountInfo.put(name,0.0);
            System.out.println("Please enter the amount you want to deposit, including the decimal.\nExample: 25.00");
            double numOption = Double.parseDouble(Main.scanner.nextLine());
            accountInfo.put(name, numOption);

        }
        else{
            System.out.println("Maybe next time. Please come again. \nNEW SESSION\n");
            homeScreen();
        }

    }




}
