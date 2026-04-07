package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<String> transactionHistory;
    private String name;

    public BankAccount() {
        this("Unnamed");
    }

    public BankAccount(String name) {
        this.name = name;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + this.balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            transactionHistory.add("Deposited: $" + amount + ", New Balance: $" + this.balance);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public String getName() {
        return this.name;
    }

    // iteration 2: bank customer should be able to rename account
    public void setName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = newName;
        transactionHistory.add("Account renamed to: " + this.name);
    }

    // task 8
    public void collectFee(double feeAmount) {
        if (feeAmount > 0) {
            this.balance -= feeAmount;
            transactionHistory.add("Fee collected: $" + feeAmount + ", New Balance: $" + this.balance);
        } else {
            throw new IllegalArgumentException();
        }
    }

    // A bank customer should be able to withdraw from their account
    public void withdraw(double amount) {
        if ((amount > 0) && (this.balance >= amount)) {
            this.balance -= amount;
            transactionHistory.add("Withdrew: $" + amount + ", New Balance: $" + this.balance);
        } else {
            throw new IllegalArgumentException();
        }
    }

    // A bank customer should be able to check their account balance
    public double checkAccountBalance() {
        return this.balance;
    }

    // task 9
    public void addInterest(double interestAmount) {
        if (interestAmount > 0) {
            this.balance += interestAmount;
            transactionHistory.add("Interest added: $" + interestAmount + ", New Balance: $" + this.balance);
        } else {
            throw new IllegalArgumentException();
        }
    }

    // A bank customer should be able to view their transaction history for an
    // account
    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // bank customer should be able to see transaction history by type
    public void viewFilteredTransactionHistory(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        String lowerType = type.toLowerCase();
        boolean found = false;

        for (String transaction : transactionHistory) {
            String lowerTransaction = transaction.toLowerCase();

            if ((lowerType.equals("deposit") && lowerTransaction.contains("deposited")) ||
                    (lowerType.equals("withdrawal") && lowerTransaction.contains("withdrew")) ||
                    (lowerType.equals("fee") && lowerTransaction.contains("fee collected")) ||
                    (lowerType.equals("interest") && lowerTransaction.contains("interest added"))) {

                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No " + type + " transactions found.");
        }
    }  

}
