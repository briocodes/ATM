package atmapplication;

import java.util.ArrayList;

public class Account {
    //The name of the account
    private String name;
    //The account ID number
    private String uuid;
    //The User object that owns this account
    private User holder;
    //The list of transactions for this account
    private ArrayList<Transaction> transactons;

    public Account(String name, User holder, Bank theBank) {
        //Set account name and holder
        this.name = name;
        this.holder = holder;

        //Generate a UUID for the new account
        this.uuid = theBank.getNewAccountUUID();

        //Initialize transactions
        this.transactons = new ArrayList<>();

    }

    /**
     * Get the account Id (Getter method)
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Get summary line for the account
     * @return the String summary
     */
    public String getSummaryLine() {
        //Get the account's balance
        double balance = this.getBalance();

        //Format the summary line depending on whether the balance is negative
        if (balance >= 0){
            return String.format("%s: $%.02f: %s", this.uuid, balance, this.name);
        }else {
            return String.format("%s: $(%.02f): %s", this.uuid, balance, this.name);
        }
    }

    /**
     * Get the balance of this account by adding the amounts of the transactions
     * @return the total balance value
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction tr : this.transactons){
            balance += tr.getAmount();
        }
        return balance;
    }

    /**
     * Print the transaction history of the account
     */
    public void printTransHistory() {
        System.out.printf("\nTransaction History for account %s\n", this.uuid);
        for (int t = this.transactons.size()-1; t>=0; t--){
            System.out.printf(this.transactons.get(t).getSummaryLine());
        }
    }

    /**
     * Add a new transaction in this account
     * @param amount the amount transacted
     * @param memo the transaction memo
     */
    public void addTransaction(double amount, String memo) {
        //Create a new transaction and add it to our list
        Transaction newTransaction = new Transaction(amount,memo,this);
        this.transactons.add(newTransaction);
    }
}
