package atmapplication;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    //The name of the bank
    private String name;
    //The list of users holding an account in this bank
    private ArrayList<User> users;
    //The list of accounts in this bank
    private ArrayList<Account> accounts;

    /**
     * Create a new Bank object with an empty list for users and accounts
     * @param name Name of the bank
     */
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public String getNewUserUUID() {
        //TODO 1 - Write a logic for this method to generate user UUID
        String uuid;
        Random rndgen = new Random();
        int length = 6;
        boolean nonUnique;

        //Loop continues until uuid is unique
        do {
            //Generate the number
            uuid = "";
            for (int chr=0; chr<length; chr++){
                uuid += ((Integer)rndgen.nextInt(10)).toString();
            }
            //Check if uuid is unique
            nonUnique = false;
            for (User u : this.users){
                if (uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        }while (nonUnique);

        return uuid;
    }

    public String getNewAccountUUID() {
        //TODO 2 - Write a logic for this method to generate account UUID
        String uuid;
        Random rndgen = new Random();
        int length = 10;
        boolean nonUnique;

        do {
            //Generate the number
            uuid = "";
            for (int chr=0; chr<length; chr++){
                uuid += ((Integer) rndgen.nextInt(10)).toString();
            }

            //Check if account uuid is unique
            nonUnique = false;
            for (Account a : accounts){
                if (uuid.compareTo(a.getUUID())==0){
                    nonUnique = true;
                    break;
                }
            }
        }while (nonUnique);
        return uuid;
    }

    /**
     * Add a user account
     * @param anAcc Account to be added
     */
    public void addAccount(Account anAcc){
        this.accounts.add(anAcc);
    }

    /**
     * Create a new user and add to bank list of users
     * @param firstName User's first name
     * @param lastName User's last name
     * @param pin User's account pin number
     * @return return the new User object
     */
    public User addUser(String firstName, String lastName, String pin){
        //Create a new user object and add it to our list
        User newUser = new User(firstName,lastName,pin,this);
        this.users.add(newUser);

        //Create a savings account for the user
        Account newAccount = new Account("Savings", newUser,this);
        //Add new account to holder and bank lists
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);
        return newUser;
    }

    /**
     * Get the User object associated with a particular user ID and pin if they are valid
     * @param userID The UUID of the user to login
     * @param pin The pin of the user
     * @return the User object if the login is successful, and null if it isn't
     */
    public User userLogin(String userID, String pin){
        //Search through list of users to see if user already exists
        for (User u : this.users){

            //Check if user ID is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }
        //If we hadn't found the user, or have an incorrect pin
        return null;
    }

    /**
     * Get the bank name (Getter method)
     * @return the bank name
     */
    public String getName() {
        return this.name;
    }
}
