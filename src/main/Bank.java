package main;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<BankAccount> accounts; //banks list of all accounts

    //constructor (when a bank is created it starts with no accounts)

    public Bank() {
    this.accounts = new ArrayList<>();
    }

    //want this method for the test method
    public int getNumberOfAccounts() {
    return accounts.size();
    }

    //create account and store it in the bank
    // //A bank customer should be able to create an additional account with the bank.
    public BankAccount createAccount() {
        BankAccount newAccount = new BankAccount();
        accounts.add(newAccount);
     return newAccount;
    }

    //need this to be able to access a single account for the rest of the methods
    public BankAccount getAccount(int index) {
    if (index < 0 || index >= accounts.size()) {
        throw new IllegalArgumentException();
    }
    return accounts.get(index);
    }
    
    //I think this should just be remove the account from the list not make the balance zero** "accounts.remove(index);" also real world the account has to have 0 balance before you close so maybe we could add that....
    public void closeAccount(int index) {
        accounts.remove(index);
    }

    public void transferMoney(int fromIndex, int toIndex, double amount) {
        if (fromIndex >= 0 && fromIndex < accounts.size()
                && toIndex >= 0 && toIndex < accounts.size()
                && fromIndex != toIndex) {

            BankAccount sourceAccount = accounts.get(fromIndex);
            BankAccount destinationAccount = accounts.get(toIndex);

            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
        } else {
            throw new IllegalArgumentException();
        }
    }



}
