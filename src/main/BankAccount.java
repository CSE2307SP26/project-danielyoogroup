package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<String> transactionHistory;
    private List<BankAccount> otherAccounts;


    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
        this.otherAccounts = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + this.balance);
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            transactionHistory.add("Deposited: $" + amount + ", New Balance: $" + this.balance);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void closeAccount() {
    this.balance = 0;
}
    //task 8
    public void collectFee(double feeAmount){
        if(feeAmount > 0){
            this.balance -= feeAmount;
        } else{
            throw new IllegalArgumentException();
        }
    }


    //A bank customer should be able to withdraw from their account
    public void withdraw(double amount) {
        if ((amount > 0) && (this.balance >= amount)) {
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException();
            }
        }

    //A bank customer should be able to check their account balance
    public double checkAccountBalance() {
        return this.balance;
    }

    //task 9
    public void addInterest(double interestAmount) {
    if (interestAmount > 0) {
        this.balance += interestAmount;
    } else {
        throw new IllegalArgumentException();
    }
    }

    public void transferMoney (BankAccount destinationAccount, double transferAmount) {
        if (destinationAccount == null){
            throw new IllegalArgumentException(); //the destination account not valid
        }
        this.withdraw(transferAmount);  //does the validation
        destinationAccount.deposit(transferAmount);

    }

    

    }




    //A bank customer should be able to view their transaction history for an account
    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()){
            System.out.println("No transactions");
        }
        else{
            for(String transaction : transactionHistory){
                System.out.println(transaction);
            }
        }
    }
    
     //A bank customer should be able to create an additional account with the bank.
     public void createAdditionalAccount(){
        BankAccount newAccount = new BankAccount();
        otherAccounts.add(newAccount);
        System.out.println("New account with balance: $" + newAccount.getBalance() + " created.");
     }

     //check extra accounts 
     public List<BankAccount> getOtherAccounts() {
        return otherAccounts;
     }  

    }
