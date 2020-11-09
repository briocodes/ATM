package atmapplication;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    //The first name of the user
    private String firstName;
    //The last name of the user
    private String lastName;
    //The user ID number
    private String uuid;
    //The MD5 hash of the user's pin number
    private byte pinHash[];
    //The list of accounts for this user
    private ArrayList<Account> accounts;


    /**
     * Create a New User
     * @param firstName User's first name
     * @param lastName User's last name
     * @param pin User's account pin number
     * @param theBank The bank object the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {
        //Set the user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //Store the pin's MD5 hash, rather than the original value, for security reasons
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            this.pinHash = messageDigest.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, NoSuchAlgorithmException caught");
            e.printStackTrace();
            System.exit(1);
        }

        //Get a new universal unique ID for the user, from the bank
        this.uuid = theBank.getNewUserUUID();

        //Create empty list of accounts
        this.accounts = new ArrayList<>();

        //Print out a log message
        System.out.printf("\nNew User %s, %s, with User ID %s created\n", lastName, firstName,
                this.uuid);

    }

    /**
     *Add an account for the user
     * @param anAcc Account to be added
     */
    public void addAccount(Account anAcc) {
        this.accounts.add(anAcc);
    }

    /**
     * Get the user ID (Getter method)
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Check whether a give pin matches the true user pin
     * @param pin The given pin to check
     * @return whether the pin is valid or not
     */
    public boolean validatePin(String pin) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(messageDigest.digest(pin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, NoSuchAlgorithmException caught");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * Get the first name of the User object (Getter method)
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Print summaries for the accounts of this user
     */
    public void printAccountSummary() {

        System.out.printf("\n%s's account summary\n", this.firstName);
        for (int a=0; a<this.accounts.size(); a++){
            System.out.printf("     %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
    }

    /**
     * Get the number of accounts of the user
     * @return the number of accounts
     */
    public int numOfAccounts() {
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account
     * @param accIndex The index of the account to print the transaction history
     */
    public void printAcctTransHistory(int accIndex) {
        this.accounts.get(accIndex).printTransHistory();
    }

    /**
     * Get the balance of a particular account
     * @param fromAccIndex  the index of the account to use
     * @return              the account balance
     */
    public double getAcctBalance(int fromAccIndex) {
        return this.accounts.get(fromAccIndex).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * @param accIndex  the index of the account to use
     * @return          the UUID of the account
     */
    public String getAcctUUID(int accIndex) {
        return this.accounts.get(accIndex).getUUID();
    }

    /**
     * Add a transaction to a particular account
     * @param accIndex  the index of the account
     * @param amount    the amount of the transaction
     * @param memo      the memo of the transaction
     */
    public void addAccountTransaction(int accIndex, double amount, String memo) {
        this.accounts.get(accIndex).addTransaction(amount,memo);
    }
}
