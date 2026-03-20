package main;

public class BankAccount {

    private double balance;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
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
       
    }




