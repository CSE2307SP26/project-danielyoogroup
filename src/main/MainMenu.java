package main;

import java.util.Scanner;

public class MainMenu {

    private static final int BANK_EXIT_SELECTION = 12;
    private static final int BANK_MAX_SELECTION = 12;

    private static final int ACCOUNT_EXIT_SELECTION = 8;
    private static final int ACCOUNT_MAX_SELECTION = 8;

    private Bank bank;
    private Scanner keyboardInput;
    private int currentAccountIndex = -1;
    String userName;
    String userRole;
    int userBirthYear;

    public MainMenu() {
        this.bank = new Bank();
        this.keyboardInput = new Scanner(System.in);
    }
    
    public void displayBankOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        System.out.println("1. Create account");
        System.out.println("2. Close account");
        System.out.println("3. Transfer money");
        System.out.println("4. See existing accounts (in order of creation)");
        System.out.println("5. See existing accounts (in order of descending balance)");
        System.out.println("6. Go into an account");
        System.out.println("7: Rename account");
        System.out.println("8. See user details");
        System.out.println("9. Edit user name");
        System.out.println("10. Edit user role");
        System.out.println("11. Edit user birth year");
        System.out.println("12. Exit the app");
    }

    public void displayAccountOptions() {
        System.out.println("Account Menu");
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Check account balance");
        System.out.println("4. View transaction history");
        System.out.println("5. Collect fee");
        System.out.println("6. Add interest");
        System.out.println("7. Filter transaction history by type");
        System.out.println("8. Back to bank menu");
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

    public void processBankInput(int selection) {
        switch (selection) {
            case 1:
                performCreateAdditionalAccount();
                break;
            case 2:
                performCloseAccount();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                performListAccounts();
                break;
            case 5:
                performListAccountsByBalance();
                break;
            case 6:
                performSelectAccount();
                break;
            case 7:
                performRenameAccount();
                break;
            case 8:
                performSeeUserDetails();
                break;
            case 9:
                performEditUserName();
                break;
            case 10:
                performEditUserRole();
                break;
            case 11:
                performEditUserBirthYear();
                break;
            case 12:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
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
                performCollectFee();
                break;
            case 6:
                performAddInterest();
                break;
            case 7:
                performFilterTransactionHistory();
                break;
            case 8:
                System.out.println("Returning to bank menu.");
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
            depositAmount = keyboardInput.nextDouble();
            keyboardInput.nextLine();
        }

        bank.getAccount(currentAccountIndex).deposit(depositAmount);
        System.out.println("Deposit successful.");
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
            System.out.println("you need at least two accounts to make a transfer.");
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

    public void performCollectFee() {
        System.out.print("Enter fee amount: ");
        double feeAmount = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        try {
            bank.getAccount(currentAccountIndex).collectFee(feeAmount);
            System.out.println("Fee collected.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid fee amount.");
        }
    }

    public void performAddInterest() {
        System.out.print("Enter interest amount: ");
        double interestAmount = keyboardInput.nextDouble();
        keyboardInput.nextLine();

        try {
            bank.getAccount(currentAccountIndex).addInterest(interestAmount);
            System.out.println("Interest added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid interest amount.");
        }
    }

    public void performCreateAdditionalAccount() {
        System.out.println("What is the name of the account you would like to create?");
        String name = keyboardInput.nextLine();
        bank.createAccount(name);
        System.out.println("Additional account created.");

        if (currentAccountIndex == -1) {
            currentAccountIndex = 0;
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
        System.out.print("Enter the index of the account you want to use:");
        int accountIndex = keyboardInput.nextInt();
        keyboardInput.nextLine();

        try {
            bank.getAccount(accountIndex);
            currentAccountIndex = accountIndex;
            System.out.println("You are now using account: " + bank.getAccount(currentAccountIndex).getName() + ".");
            runAccountMenu();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid account.");
        }
    }

    public void runAccountMenu() {
        int selection = -1;
        while (selection != ACCOUNT_EXIT_SELECTION) {
            displayAccountOptions();
            selection = getUserSelection(ACCOUNT_MAX_SELECTION);
            processAccountInput(selection);
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

    public void performEditUserName() {
        System.out.print("Enter Name: ");
        userName = keyboardInput.nextLine();
        System.out.println("Name change confirmed.");
    }

    public void performEditUserRole() {
        System.out.print("Enter Role (customer or banker): ");
        userRole = keyboardInput.nextLine();
        System.out.println("Role change confirmed.");
    }

    public void performEditUserBirthYear() {
        System.out.print("Enter birth year: ");
        userBirthYear = keyboardInput.nextInt();
        System.out.println("Birth year change confirmed.");
    }

    public void performSeeUserDetails() {
        System.out.println("User Details: ");
        System.out.println("Name: " + userName);
        System.out.println("Role: " + userRole);
        System.out.println("Birth Year: " + userBirthYear);
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

    public void run() {
        int selection = -1;
        while (selection != BANK_EXIT_SELECTION) {
            displayBankOptions();
            selection = getUserSelection(BANK_MAX_SELECTION);
            processBankInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
        // bankApp.keyboardInput.close();
    }
}
