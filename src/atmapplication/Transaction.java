package atmapplication;

import java.util.Date;

public class Transaction {
    //The amount of this transaction
    private double amount;
    //The time and date of this transaction
    private Date timestamp;
    //A memo for this transaction
    private String memo;
    //The account where this transaction occured
    private Account inAccount;

    /**
     * Create a new transaction
     * @param amount The amount transacted
     * @param inAccount The account where the transaction took place
     */
    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";

    }

    /**
     * Create a new transaction
     * @param amount The amount transacted
     * @param memo The memo of the transaction
     * @param inAccount The account where the transaction took place
     */
    public Transaction(double amount, String memo, Account inAccount) {
        this(amount,inAccount);
        this.memo = memo;
    }

    /**
     * Gwt the amount of this transaction (Getter method)
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get a string summarizing the transaction
     * @return the summary string
     */
    public String getSummaryLine() {
        if (this.amount>0){
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo + "\n");
        }else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo + "\n");
        }
    }
}
