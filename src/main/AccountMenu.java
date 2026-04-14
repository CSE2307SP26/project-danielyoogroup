package main;

import java.util.Scanner;

public class AccountMenu {

    private static final int ACCOUNT_EXIT_SELECTION = 6;
    private static final int ACCOUNT_MAX_SELECTION = 6;

    private Bank bank;
    private Scanner keyboardInput;
    private int currentAccountIndex;

    public AccountMenu(Bank bank, Scanner keyboardInput, int currentAccountIndex) {
        this.bank = bank;
        this.keyboardInput = keyboardInput;
        this.currentAccountIndex = currentAccountIndex;
    }

    public void displayAccountOptions() {
        System.out.println("Account Menu");
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Check account balance");
        System.out.println("4. View transaction history");
        System.out.println("5. Filter transaction history by type");
        System.out.println("6. Back");
    }

    public int getUserSelection(int max) {
        int selection = -1;
        while (selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
            keyboardInput.nextLine();
        }
        return selection;
    }

    public void processAccountInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdrawal();
                break;
            case 3:
                performCheckBalance();
                break;
            case 4:
                bank.getAccount(currentAccountIndex).viewTransactionHistory();
                break;
            case 5:
                performFilterTransactionHistory();
                break;
            case 6:
                System.out.println("Returning to customer menu.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    public void performDeposit() {
        System.out.print("How much would you like to deposit: ");
        double depositAmount = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        try {
            bank.getAccount(currentAccountIndex).deposit(depositAmount);
            System.out.println("Deposit successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void performWithdrawal() {
        System.out.print("How much would you like to withdraw: ");
        double withdrawAmount = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        try {
            bank.getAccount(currentAccountIndex).withdraw(withdrawAmount);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void performCheckBalance() {
        System.out.println("Current balance: $" + bank.getAccount(currentAccountIndex).checkAccountBalance());
    }

    public void performFilterTransactionHistory() {
        System.out.println("Choose transaction type to view:");
        System.out.println("1. Deposits");
        System.out.println("2. Withdrawals");
        System.out.println("3. Fees");
        System.out.println("4. Interest");
        System.out.print("Please make a selection: ");

        int selection = keyboardInput.nextInt();
        keyboardInput.nextLine();

        String type;

        switch (selection) {
            case 1:
                type = "deposit";
                break;
            case 2:
                type = "withdrawal";
                break;
            case 3:
                type = "fee";
                break;
            case 4:
                type = "interest";
                break;
            default:
                System.out.println("Invalid selection.");
                return;
        }

        bank.getAccount(currentAccountIndex).viewFilteredTransactionHistory(type);
    }

    public void runAccountMenu() {
        int selection = -1;
        while (selection != ACCOUNT_EXIT_SELECTION) {
            displayAccountOptions();
            selection = getUserSelection(ACCOUNT_MAX_SELECTION);
            processAccountInput(selection);
        }
    }
}