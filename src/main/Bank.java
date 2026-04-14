package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bank {

    private List<BankAccount> accounts; // banks list of all accounts
    
    // constructor (when a bank is created it starts with no accounts)
    public Bank() {
        this.accounts = new ArrayList<>();
    }

    // want this method for the test method
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    // create account and store it in the bank
    // //A bank customer should be able to create an additional account with the
    // bank.
    public BankAccount createAccount(String name) {
        BankAccount newAccount = new BankAccount(name);
        accounts.add(newAccount);
        return newAccount;
    }

    // need this to be able to access a single account for the rest of the methods
    public BankAccount getAccount(int index) {
        if (index < 0 || index >= accounts.size()) {
            throw new IllegalArgumentException();
        }
        return accounts.get(index);
    }

    public void closeAccount(int index) {
        if (index < 0 || index >= accounts.size()) {
            throw new IllegalArgumentException();
        }
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

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts exist yet.");
            return;
        }

        System.out.println("List of accounts:");

        for (int i = 0; i < accounts.size(); i++) {
            BankAccount acc = accounts.get(i);
            System.out.println("Index: " + i + " | Name: " + acc.getName() + " | Balance: $" + acc.getBalance());
        }

    }

    // iteration 2: renaming account
    public void renameAccount(int index, String newName) {
        if (index < 0 || index >= accounts.size()) {
            throw new IllegalArgumentException();
        }
        accounts.get(index).setName(newName);
    }

    //listAccountsByBalance - Iteration 2
    public void listAccountsByBalance() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts exist yet.");
            return;
        }

        accounts.sort(Comparator.comparingDouble(BankAccount::getBalance).reversed());

        System.out.println("List of accounts:");

        for (int i = 0; i < accounts.size(); i++) {
            BankAccount acc = accounts.get(i);
            System.out.println("Index: " + i + " | Name: " + acc.getName() + " | Balance: $" + acc.getBalance());
        }
    }

}
