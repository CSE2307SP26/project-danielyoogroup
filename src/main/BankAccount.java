package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<String> transactionHistory;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
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

    //task 8
    public void collectFee(double feeAmount){
        if(feeAmount > 0){
            this.balance -= feeAmount;
            transactionHistory.add("Fee collected: $" + feeAmount + ", New Balance: $" + this.balance);
        } else{
            throw new IllegalArgumentException();
        }
    }


    //A bank customer should be able to withdraw from their account
    public void withdraw(double amount) {
        if ((amount > 0) && (this.balance >= amount)) {
            this.balance -= amount;
            transactionHistory.add("Withdrew: $" + amount + ", New Balance: $" + this.balance);
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
            transactionHistory.add("Interest added: $" + interestAmount + ", New Balance: $" + this.balance);
        } else {
            throw new IllegalArgumentException();
        }
    }

    
        //A bank customer should be able to view their transaction history for an account
    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()){
            System.out.println("No transactions");
        }else{
            for(String transaction : transactionHistory){
                System.out.println(transaction);
            }
        }
    }
    
}
    
