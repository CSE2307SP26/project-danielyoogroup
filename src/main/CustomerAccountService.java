package main;

import java.util.Scanner;

public class CustomerAccountService {

    private Bank bank;
    private Customer customer;
    private Scanner keyboardInput;
    private int currentAccountIndex = -1;

    public CustomerAccountService(Bank bank, Customer customer, Scanner keyboardInput) {
        this.bank = bank;
        this.customer = customer;
        this.keyboardInput = keyboardInput;
    }

    public void performCreateAdditionalAccount() {
        System.out.print("What is the name of the account you would like to create? ");
        String name = keyboardInput.nextLine();

        bank.createAccount(name);

        System.out.println("Additional account created.");

        if (currentAccountIndex == -1) {
            currentAccountIndex = 0;
        }
    }

    public void performCloseAccount() {
        if (bank.getNumberOfAccounts() == 0) {
            System.out.println("No accounts exist yet.");
            return;
        }

        performListAccounts();
        System.out.print("Enter account index to close: ");
        int accountIndex = keyboardInput.nextInt();
        keyboardInput.nextLine();

        try {
            bank.closeAccount(accountIndex);
            System.out.println("Account closed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid account.");
        }
    }

    public void performTransfer() {
        if (bank.getNumberOfAccounts() < 2) {
            System.out.println("You need at least two accounts to make a transfer.");
            return;
        }

        performListAccounts();

        System.out.print("Enter source account index: ");
        int sourceIndex = keyboardInput.nextInt();
        keyboardInput.nextLine();

        System.out.print("Enter destination account index: ");
        int destinationIndex = keyboardInput.nextInt();
        keyboardInput.nextLine();

        System.out.print("How much would you like to transfer: ");
        double transferAmount = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        try {
            bank.transferMoney(sourceIndex, destinationIndex, transferAmount);
            System.out.println("Transfer successful.");
            System.out.println("Source account new balance: $" + bank.getAccount(sourceIndex).getBalance());
            System.out.println("Destination account new balance: $" + bank.getAccount(destinationIndex).getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid transfer.");
        }
    }

    public void performListAccounts() {
        bank.listAccounts();
        System.out.println();
    }

    public void performListAccountsByBalance() {
        bank.listAccountsByBalance();
        System.out.println();
    }

    public void performSelectAccount() {
        if (bank.getNumberOfAccounts() == 0) {
            System.out.println("No accounts exist yet.");
            return;
        }

        performListAccounts();
        System.out.print("Enter the index of the account you want to use: ");
        int accountIndex = keyboardInput.nextInt();
        keyboardInput.nextLine();

        try {
            BankAccount selectedAccount = bank.getAccount(accountIndex);

            if (customer.hasPin()) {
                System.out.print("Enter PIN for this customer: ");
                String enteredPin = keyboardInput.nextLine();

                if (!customer.checkPin(enteredPin)) {
                    customer.recordFailedPinAttempt();

                    if (customer.isFrozen()) {
                        System.out.println("Too many incorrect PIN attempts. Your account has been frozen.");
                    } else {
                        System.out.println("Incorrect PIN. Attempt "
                                + customer.getFailedPinAttempts() + " of 3.");
                    }
                    return;
                }

                customer.resetFailedPinAttempts();
            }

            currentAccountIndex = accountIndex;
            System.out.println("You are now using account: " + selectedAccount.getName() + ".");

            AccountMenu accountMenu = new AccountMenu(bank, keyboardInput, currentAccountIndex);
            accountMenu.runAccountMenu();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid account.");
        }
    }

    public void performRenameAccount() {
        if (bank.getNumberOfAccounts() == 0) {
            System.out.println("No accounts exist yet.");
            return;
        }

        performListAccounts();
        System.out.print("Enter the index of the account you want to rename: ");
        int accountIndex = keyboardInput.nextInt();
        keyboardInput.nextLine();

        System.out.print("Enter the new name for the account: ");
        String newName = keyboardInput.nextLine();

        try {
            bank.renameAccount(accountIndex, newName);
            System.out.println("Account renamed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid account or account name.");
        }
    }

    public void performViewTotalBalance() {
        double totalBalance = bank.getTotalBalance();
        System.out.println("Your total balance across all accounts is: $" + totalBalance);
    }

    public void performListAccountsOverValue() {
        System.out.print("Enter minimum account balance: ");
        double minDollars = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        boolean over = false;
        for (int i = 0; i < bank.getNumberOfAccounts(); i++) {
            BankAccount tempAccount = bank.getAccount(i);
            if (tempAccount.getBalance() > minDollars) {
                System.out.println("Index: " + i + " | Name: " + tempAccount.getName() + " | Balance: $" + tempAccount.getBalance());
                over = true;
            }
        }

        if (!over) {
            System.out.println("No accounts found over the specified value.");
        }
    }
}