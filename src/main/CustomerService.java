package main;

import java.util.Scanner;

public class CustomerService {

    private Bank bank;
    private Customer customer;
    private Scanner keyboardInput;
    private int currentAccountIndex = -1;

    public CustomerService(Bank bank, Customer customer, Scanner keyboardInput) {
        this.bank = bank;
        this.customer = customer;
        this.keyboardInput = keyboardInput;
    }

    public boolean isCustomerFrozen() {
        return customer.isFrozen();
    }

    public void performCreateAdditionalAccount() {
        System.out.print("What is the name of the account you would like to create? ");
        String name = keyboardInput.nextLine();

        bank.createAccount(name);

        if (!customer.hasPin()) {
            System.out.print("Would you like to protect this customer with a PIN? (yes or no): ");
            String input = keyboardInput.nextLine();

            if (input.equalsIgnoreCase("yes")) {
                System.out.print("Enter a 4 digit PIN for this customer: ");
                String customerPin = keyboardInput.nextLine();

                try {
                    customer.setPin(customerPin);
                    System.out.println("PIN protection added.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid PIN. Account created without PIN protection.");
                }
            }
        }

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
                    System.out.println("Incorrect PIN.");
                    return;
                }
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

    public void performSeeUserDetails() {
        System.out.println("User Details:");
        System.out.println("Username: " + customer.getUserName());
        System.out.println("Birth Year: " + customer.getUserBirthYear());
        System.out.println("Savings Goal: $" + customer.getSavingsGoal());
    }

    public void performEditUserDetails() {
        System.out.print("Enter new username: ");
        String newUserName = keyboardInput.nextLine();

        System.out.print("Enter new birth year: ");
        int newBirthYear = keyboardInput.nextInt();
        keyboardInput.nextLine();

        customer.setUserName(newUserName);
        customer.setUserBirthYear(newBirthYear);

        System.out.println("User details updated.");
    }

    public void performFreezeAccount() {
        System.out.print("Are you sure you want to freeze your account? (yes or no): ");
        String input = keyboardInput.nextLine();

        if (input.equalsIgnoreCase("yes")) {
            customer.freeze();
            System.out.println("Your account has been frozen. You will not be able to make transactions until you contact customer service.");
        } else {
            System.out.println("Account freeze cancelled.");
        }
    }

    public void performSavingsGoal() {
        System.out.print("Enter your savings goal amount: ");
        double goalAmount = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        try {
            customer.setSavingsGoal(goalAmount);
            System.out.println("Your savings goal of $" + goalAmount + " has been set.");

            if (currentAccountIndex >= 0 && currentAccountIndex < bank.getNumberOfAccounts()) {
                double currentBalance = bank.getAccount(currentAccountIndex).checkAccountBalance();
                System.out.println("You are currently $" + (goalAmount - currentBalance) + " away from your goal.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid savings goal.");
        }
    }

    public void performViewTotalBalance() {
        double totalBalance = bank.getTotalBalance();
        System.out.println("Your total balance across all accounts is: $" + totalBalance);
    }
}