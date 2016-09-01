import java.util.HashMap;

/**
 * Created by Zach on 8/31/16.
 */
public class ATM {
    public String name;
    public double balance = 100;
    public String menuInput;
    public String option;
    HashMap<String, Double> accountInfo = new HashMap<>();


    public ATM() {
        accountInfo.put("Zach" , 500.00);
        accountInfo.put("Ryan" , 250.00);
    }

    public void homeScreen() throws Exception{
        System.out.println("Please enter your name.");
        name = Main.scanner.nextLine();

        if ( ! name.isEmpty()) {
            System.out.println("Thank you");
        }
        else {
            throw new Exception("Error: Please enter name.");
        }
    }

    public void menuSelect() throws Exception {
        System.out.println("Please enter the number of your selection: \n 1. Check Balance  2. Withdraw Funds  3. Cancel");

        option = Main.scanner.nextLine();

        switch (option) {
            case "1" : System.out.println("Your current balance is $" + balance);
                break;
            case "2" :
                try {
                    withdrawFunds();
                } catch (Exception e) {
                    System.out.println("Input Error: " + e.getMessage());
                }
                break;
            case "3" : System.out.println("Please come again.");
                break;
            default: throw new Exception("Please enter the number of your selection: \n1. Check Balance  2. Withdraw Funds  3. Cancel");
        }

    }

    public void withdrawFunds() throws Exception {
        double amountInput;
        System.out.println("Please enter the amount to be withdrawn.");
        amountInput = Double.parseDouble(Main.scanner.nextLine());
        if(amountInput < balance){
            balance = balance - amountInput;
            System.out.println("Please take your money. \nYour remaining balance is $" + balance);
        }
        else {
            throw new Exception("Insufficient Funds!!");
        }


    }
}
