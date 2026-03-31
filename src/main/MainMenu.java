package main;

import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 11;
    private static final int MAX_SELECTION = 11;

    private Bank bank;
    private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.bank = new Bank();
        this.userAccount = new BankAccount();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Check account balance");
        System.out.println("4. View transaction history");
        System.out.println("5. Create additional account");
        System.out.println("6. Close account");
        System.out.println("7. Transfer money");
        System.out.println("8. Collect fee");
        System.out.println("9. Add interest");
        System.out.println("10. See existing accounts");
        System.out.println("11. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while (selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
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
                userAccount.viewTransactionHistory();
                break;
            case 5:
                performCreateAdditionalAccount();
                break;
            case 6:
                performCloseAccount();
                break;
            case 7:
                performTransfer();
                break;
            case 8:
                performCollectFee();
                break;
            case 9:
                performAddInterest();
                break;
            case 10:
                performListAccounts();
                break;
            case 11:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while (depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount.deposit(depositAmount);
    }

    public void performWithdrawal() {
        System.out.print("How much would you like to withdraw: ");
        double withdrawAmount = keyboardInput.nextDouble();

        try {
            userAccount.withdraw(withdrawAmount);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void performCheckBalance() {
        System.out.println("Current balance: $" + userAccount.checkAccountBalance());
    }

    public void performCloseAccount() {
        System.out.print("Enter account index to close: ");
        int accountIndex = keyboardInput.nextInt();

        try {
            bank.closeAccount(accountIndex);
            System.out.println("Account closed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid account.");
        }
    }

    public void performTransfer() {
        System.out.print("Enter source account index: ");
        int sourceIndex = keyboardInput.nextInt();

        System.out.print("Enter destination account index: ");
        int destinationIndex = keyboardInput.nextInt();

        System.out.print("How much would you like to transfer: ");
        double transferAmount = keyboardInput.nextDouble();

        try {
            bank.transferMoney(sourceIndex, destinationIndex, transferAmount);
            System.out.println("Transfer successful.");
            System.out.println("Source account new balance: $" + bank.getAccount(sourceIndex).getBalance());
            System.out.println("Destination account new balance: $" + bank.getAccount(destinationIndex).getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid transfer.");
        }
    }

    public void performCollectFee() {
        System.out.print("Enter fee amount: ");
        double feeAmount = keyboardInput.nextDouble();

        try {
            userAccount.collectFee(feeAmount);
            System.out.println("Fee collected.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid fee amount.");
        }
    }

    public void performAddInterest() {
        System.out.print("Enter interest amount: ");
        double interestAmount = keyboardInput.nextDouble();

        try {
            userAccount.addInterest(interestAmount);
            System.out.println("Interest added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid interest amount.");
        }
    }

    public void performCreateAdditionalAccount() {
        bank.createAccount();
        System.out.println("Additional account created.");
    }

    public void performListAccounts() {
        System.out.println("\nDisplaying all accounts:");
        bank.listAccounts();
        System.out.println();
    }

    public void run() {
        int selection = -1;
        while (selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }

}
