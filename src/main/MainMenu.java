package main;

import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 3;
	private static final int MAX_SELECTION = 3;

	private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccount = new BankAccount();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        System.out.println("1. Make a deposit");
        System.out.println("2. Exit the app");
        System.out.println("3. Check account balance");
        System.out.println("4. View transaction history");
        System.out.println("5. Create additional account");
        System.out.println("6. Close account");
        System.out.println("7. Transfer money");
        System.out.println("8. Collect fee");
        System.out.println("9. Add interest");
        System.out.println("10. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
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
                userAccount.createAdditionalAccount();
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
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while(depositAmount < 0) {
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
        userAccount.closeAccount();
        System.out.println("Account closed.");
    }

    public void performTransfer() {
        System.out.print("How much would you like to transfer: ");
        double transferAmount = keyboardInput.nextDouble();

        BankAccount destinationAccount = new BankAccount();

        try {
            userAccount.transferMoney(destinationAccount, transferAmount);
            System.out.println("Transfer successful.");
            System.out.println("Your new balance: $" + userAccount.getBalance());
            System.out.println("Destination account balance: $" + destinationAccount.getBalance());
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


    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
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
