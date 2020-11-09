package atmapplication;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {

        //initialize scanner
        Scanner scanner = new Scanner(System.in);

        //initialize Bank
        Bank theBank = new Bank("FirstBank Plc.");

        //Add a user which also creates a savings account
        User nUser = theBank.addUser("Chioma","Akpota","1234");

        //Add a checking account for our user
        Account newAccount = new Account("Checking", nUser,theBank);
        nUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        //Create a user login
        User currentUser;
        while (true){
            //Stay in the login prompt until a successful login
            currentUser = ATM.mainMenuPrompt(theBank,scanner);

            //Stay in main menu until user quits
            ATM.printUserMenu(currentUser,scanner);

        }
    }

    /**
     * Print the ATM's Login menu
     * @param theBank The Bank object whose account to use
     * @param scanner The Scanner object to use for user input
     * @return the authenticated User object
     */
    public static User mainMenuPrompt(Bank theBank, Scanner scanner) {
        String userID;
        String pin;
        User authUser;

        do {
            //Prompt the user for user ID/pin combo until a correct one is reached
            System.out.printf("\nWelcome to %s \n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = scanner.nextLine();
            System.out.print("Enter Pin: ");
            pin = scanner.nextLine();

            //Try to get the User object corresponding to the user ID & pin combo
            authUser = theBank.userLogin(userID,pin);
            if (authUser == null){
                System.out.println("Invalid userID/pin combination. Please try again");
            }

        }while (authUser==null); //Continue looping until successful login
        return authUser;
    }

    public static void printUserMenu(User currentUser, Scanner scanner) {

        //Print a summary of the user's accounts
        currentUser.printAccountSummary();

        //init
        int choice;

        //USERMENU
        do {
            System.out.printf("Welcome %s, What would you like to do?\n", currentUser.getFirstName());
            System.out.println("    1. PRINT ACCOUNT TRANSACTION HISTORY");
            System.out.println("    2. WITHDRAWAL");
            System.out.println("    3. DEPOSIT");
            System.out.println("    4. TRANSFER");
            System.out.println("    5. QUIT");
            System.out.println();
            System.out.print("Enter Choice: ");
            choice = scanner.nextInt();

            if (choice <1 || choice >5){
                System.out.println("Invalid entry. Please enter 1-5");
            }
        }while (choice <1 || choice >5);

        //Process the choice
        switch (choice){
            case 1:
                ATM.showTransactionHistory(currentUser,scanner);
                //Gobble up the rest of previous input line
                scanner.nextLine();
                break;
            case 2:
                ATM.withdrawFunds(currentUser,scanner);
                break;
            case 3:
                ATM.depositFunds(currentUser,scanner);
                break;
            case 4:
                ATM.transferFunds(currentUser,scanner);
                //Gobble up the rest of previous input line
                scanner.nextLine();
                break;
            default:
                System.out.print("Incorrect entry. Please enter again");
            //Re-display menu if except user wants to quit
            if (choice != 5){
                ATM.printUserMenu(currentUser,scanner);
            }

        }

    }

    /**
     * Show the transaction history for an account
     * @param currentUser   The logged-in user account
     * @param scanner       The scanner object used for user input
     */
    public static void showTransactionHistory(User currentUser, Scanner scanner) {
        int theAcct;
        //Get account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the accounts whose transactions you want to see: ",
                    currentUser.numOfAccounts());
            theAcct = scanner.nextInt()-1;
            if (theAcct<0 || theAcct>currentUser.numOfAccounts()){
                System.out.println("Invalid account. Please try again");
            }
        }while (theAcct<0 || theAcct>currentUser.numOfAccounts());

        //Print the transaction history
        currentUser.printAcctTransHistory(theAcct);
    }

    /**
     * Process transferring funds from one account to another
     * @param currentUser   the logged-in user object
     * @param scanner       the scanner object used for user input
     */
    public static void transferFunds(User currentUser, Scanner scanner) {
        //inits
        int fromAcct;
        int toAcct;
        double amount;
        double accBalance;

        //Get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer from: ",currentUser.numOfAccounts());
            fromAcct = scanner.nextInt()-1;
            if (fromAcct<0 || fromAcct>=currentUser.numOfAccounts()){
                System.out.printf("Invalid account. Please enter again");
            }
        }while (fromAcct<0 || fromAcct>=currentUser.numOfAccounts());

        accBalance = currentUser.getAcctBalance(fromAcct);

        //Get account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer to: ", currentUser.numOfAccounts());
            toAcct = scanner.nextInt()-1;
            if (toAcct<0 || toAcct>currentUser.numOfAccounts()){
                System.out.println("Invalid account. Please try again");
            }
        }while (toAcct<0 || toAcct>currentUser.numOfAccounts());

        //Get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max : $%.02f): $", accBalance);
            amount = scanner.nextInt();
            if (amount<0){
                System.out.println("Amount must not be less than zero");
            }else if (amount>accBalance){
                System.out.printf("Amount must not be greater than the $%.02f account balance\n", accBalance);
            }
        }while (amount<0 || amount >accBalance);

        //Finally do the transfer
        currentUser.addAccountTransaction(toAcct, amount, String.format("Credit transfer from account %s",
                currentUser.getAcctUUID(fromAcct)));
        currentUser.addAccountTransaction(fromAcct, -1*amount, String.format("Debit transfer to account %s",
                currentUser.getAcctUUID(toAcct)));
    }

    /**
     * Process a fund withdraw from an account
     * @param currentUser   the logged-in user object
     * @param scanner       the scanner object to enter user input
     */
    public static void withdrawFunds(User currentUser, Scanner scanner) {
        //inits
        int fromAcct;
        double amount;
        double accBalance;
        String memo;

        //Get the account to withdraw from
        do {
            System.out.printf("Enter the number (1-%d) of the account to withdraw from: ", currentUser.numOfAccounts());
            fromAcct = scanner.nextInt()-1;
            if (fromAcct<0 || fromAcct>=currentUser.numOfAccounts()){
                System.out.println("Invalid account. Please enter again");
            }
        }while (fromAcct<0 || fromAcct>=currentUser.numOfAccounts());

        accBalance = currentUser.getAcctBalance(fromAcct);

        //Get the amount to withdraw
        do {
            System.out.printf("Enter the amount to withdraw (max : $%.02f): $", accBalance);
            amount = scanner.nextDouble();
            if (amount<0){
                System.out.println("Amount must not be less than zero");
            }else if (amount>accBalance){
                System.out.printf("Amount must not be greater than account balance of $%.02f\n", accBalance);
            }
        }while (amount<0 || amount >accBalance);

        //Gobble up the rest of previous input line
        scanner.nextLine();

        //Get the memo
        System.out.print("Enter a memo: ");
        memo = scanner.nextLine();

        //Finally do the withdrawal
        currentUser.addAccountTransaction(fromAcct, -1*amount, memo);
    }

    /**
     * Process a fund deposit in an account
     * @param currentUser   The logged-in user object
     * @param scanner       the scanner object to enter user input
     */
    public static void depositFunds(User currentUser, Scanner scanner) {
        //init
        int toAcct;
        double amount;
        double accBalance;
        String memo;

        //Get the account to deposit to
        do {
            //TODO - 3 Check for the availability of the account in the bank list of accounts
            System.out.printf("Enter the number (1-%d) of the account to deposit to: ", currentUser.numOfAccounts());
            toAcct = scanner.nextInt()-1;
            if (toAcct<0 || toAcct> currentUser.numOfAccounts()){
                System.out.println("Invalid account. Please enter again");
            }
        }while (toAcct<0 || toAcct> currentUser.numOfAccounts());
        //TODO -4 Check the account balance of our account before depositing to a foreign account
        accBalance = currentUser.getAcctBalance(toAcct);

        //Get the amount to deposit
        do {
            System.out.printf("Enter the amount to deposit in (min: $10): $");
            amount = scanner.nextDouble();
            if (amount<0){
                System.out.println("Amount cannot be less than zero");
            } else if (amount<5){
                System.out.println("Amount is too small. Please enter a minimum deposit amount of $5\n");
            }
        }while (amount<0 || amount <10);

        //Gobble up the rest of previous input line
        scanner.nextLine();

        //Get the memo
        System.out.print("Enter a memo: ");
        memo = scanner.nextLine();

        //Finally make the deposit
        currentUser.addAccountTransaction(toAcct, amount, memo);
    }

}
