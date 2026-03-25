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




