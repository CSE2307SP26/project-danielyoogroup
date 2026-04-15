package main;

import java.util.Scanner;

public class AdminMenu {

    private static final int ADMIN_EXIT_SELECTION = 7;
    private static final int ADMIN_MAX_SELECTION = 7;

    private Bank bank;
    private Customer customer;
    private Scanner keyboardInput;

    public AdminMenu(Bank bank, Customer customer, Scanner keyboardInput) {
        this.bank = bank;
        this.customer = customer;
        this.keyboardInput = keyboardInput;
    }

    public void displayAdminOptions() {
        System.out.println("Administrator Menu");
        System.out.println("1. See existing accounts (in order of creation)");
        System.out.println("2. See existing accounts (in order of descending balance)");
        System.out.println("3. Collect fee");
        System.out.println("4. Add interest");
        System.out.println("5. Rename account");
        System.out.println("6: Unfreeze customer account");
        System.out.println("7. Back");
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

    public void processAdminInput(int selection) {
        switch (selection) {
            case 1:
                performListAccounts();
                break;
            case 2:
                performListAccountsByBalance();
                break;
            case 3:
                performCollectFee();
                break;
            case 4:
                performAddInterest();
                break;
            case 5:
                performRenameAccount();
                break;
            case 6:
                performUnfreezeCustomerAccount();
                break;
            case 7:
                System.out.println("Returning to main menu.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
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

    public void performCollectFee() {
        if (bank.getNumberOfAccounts() == 0) {
            System.out.println("No accounts exist yet.");
            return;
        }

        performListAccounts();
        System.out.print("Enter account index: ");
        int accountIndex = keyboardInput.nextInt();
        keyboardInput.nextLine();

        System.out.print("Enter fee amount: ");
        double feeAmount = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        try {
            bank.getAccount(accountIndex).collectFee(feeAmount);
            System.out.println("Fee collected.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid fee amount or account.");
        }
    }

    public void performAddInterest() {
        if (bank.getNumberOfAccounts() == 0) {
            System.out.println("No accounts exist yet.");
            return;
        }

        performListAccounts();
        System.out.print("Enter account index: ");
        int accountIndex = keyboardInput.nextInt();
        keyboardInput.nextLine();

        System.out.print("Enter interest amount: ");
        double interestAmount = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        try {
            bank.getAccount(accountIndex).addInterest(interestAmount);
            System.out.println("Interest added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid interest amount or account.");
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

   public void performUnfreezeCustomerAccount() {
        if (!customer.isFrozen()) {
            System.out.println("Customer account is not currently frozen.");
            return;
        }

        System.out.print("Enter customer username: ");
        String enteredName = keyboardInput.nextLine();

        System.out.print("Enter customer birth year: ");
        int enteredYear = keyboardInput.nextInt();
        keyboardInput.nextLine();

        if (customer.verifyIdentity(enteredName, enteredYear)) {
            customer.unfreeze();
            System.out.println("Identity verified. Customer account has been unfrozen.");
        } else {
            System.out.println("Verification failed. Customer account remains frozen.");
        }
    }

    public void runAdminMenu() {
        int selection = -1;
        while (selection != ADMIN_EXIT_SELECTION) {
            displayAdminOptions();
            selection = getUserSelection(ADMIN_MAX_SELECTION);
            processAdminInput(selection);
        }
    }
}