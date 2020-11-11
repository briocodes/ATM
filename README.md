# ATM
This is a program that implements a bank ATM interface, and an online banking system with the possibilities of making 
deposits, withdrawals, transfers between accounts, and checking the list of concluded transactions upon login.

### ATM USER INTERACTION EXAMPLE ###
New User Orwell, George, with User ID 926576 created

Welcome to Universal Bank Plc. 
Enter user ID: 926576
Enter Pin: 3019

George's account summary
     1) 4798775206: $0.00: Savings
     2) 7182590567: $0.00: Checking
Welcome George, What would you like to do?
    1) PRINT ACCOUNT TRANSACTION HISTORY
    2) WITHDRAWAL
    3) DEPOSIT
    4) TRANSFER
    5) QUIT

Enter Choice: 3
Enter the number (1-2) of the account to deposit in: 1
Enter the amount to deposit (min: $5): $45000
Enter a memo: Cash funding

Welcome to Universal Bank Plc. 
Enter user ID: 926576
Enter Pin: 3019

George's account summary
     1) 4798775206: $45000.00: Savings
     2) 7182590567: $0.00: Checking
Welcome George, What would you like to do?
    1) PRINT ACCOUNT TRANSACTION HISTORY
    2) WITHDRAWAL
    3) DEPOSIT
    4) TRANSFER
    5) QUIT

Enter Choice: 4
Enter the number (1-2) of the account to transfer from: 1
Enter the number (1-2) of the account to transfer to: 2
Enter the amount to transfer (max : $45000.00): $34000

Welcome to Universal Bank Plc. 
Enter user ID: 926576
Enter Pin: 3019

George's account summary
     1) 4798775206: $11000.00: Savings
     2) 7182590567: $34000.00: Checking
Welcome George, What would you like to do?
    1) PRINT ACCOUNT TRANSACTION HISTORY
    2) WITHDRAWAL
    3) DEPOSIT
    4) TRANSFER
    5) QUIT

Enter Choice: 2
Enter the number (1-2) of the account to withdraw from: 2
Enter the amount to withdraw (max : $34000.00): $32000
Enter a memo: Purchase of technical equipments

Welcome to Universal Bank Plc. 
Enter user ID: 926576
Enter Pin: 3019

George's account summary
     1) 4798775206: $11000.00: Savings
     2) 7182590567: $2000.00: Checking
Welcome George, What would you like to do?
    1) PRINT ACCOUNT TRANSACTION HISTORY
    2) WITHDRAWAL
    3) DEPOSIT
    4) TRANSFER
    5) QUIT

Enter Choice: 2
Enter the number (1-2) of the account to withdraw from: 1
Enter the amount to withdraw (max : $11000.00): $500
Enter a memo: Utility bill

Welcome to Universal Bank Plc. 
Enter user ID: 926576
Enter Pin: 3019

George's account summary
     1) 4798775206: $10500.00: Savings
     2) 7182590567: $2000.00: Checking
Welcome George, What would you like to do?
    1) PRINT ACCOUNT TRANSACTION HISTORY
    2) WITHDRAWAL
    3) DEPOSIT
    4) TRANSFER
    5) QUIT

Enter Choice: 1
Enter the number (1-2) of the accounts whose transactions you want to see: 1

Transaction History for account 4798775206
Wed Nov 11 01:07:22 WAT 2020 : $(-500.00) : Utility bill
Wed Nov 11 00:56:43 WAT 2020 : $(-34000.00) : Debit transfer to account 7182590567
Wed Nov 11 00:55:01 WAT 2020 : $45000.00 : Cash funding

Welcome to Universal Bank Plc. 
Enter user ID: 926576
Enter Pin: 3019

George's account summary
     1) 4798775206: $10500.00: Savings
     2) 7182590567: $2000.00: Checking
Welcome George, What would you like to do?
    1) PRINT ACCOUNT TRANSACTION HISTORY
    2) WITHDRAWAL
    3) DEPOSIT
    4) TRANSFER
    5) QUIT

Enter Choice: 1
Enter the number (1-2) of the accounts whose transactions you want to see: 2

Transaction History for account 7182590567
Wed Nov 11 01:03:52 WAT 2020 : $(-32000.00) : Purchase of technical equipments
Wed Nov 11 00:56:43 WAT 2020 : $34000.00 : Credit transfer from account 4798775206

Welcome to Universal Bank Plc. 
Enter user ID: 926576
Enter Pin: 3019

George's account summary
     1) 4798775206: $10500.00: Savings
     2) 7182590567: $2000.00: Checking
Welcome George, What would you like to do?
    1) PRINT ACCOUNT TRANSACTION HISTORY
    2) WITHDRAWAL
    3) DEPOSIT
    4) TRANSFER
    5) QUIT

Enter Choice: 5
Thanks for banking with us, George.
