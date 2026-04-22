package main;

import java.util.Scanner;

public class CustomerMenu {

    private static final int CUSTOMER_EXIT_SELECTION = 16;
    private static final int CUSTOMER_MAX_SELECTION = 16;

    private CustomerService customerService;
    private CustomerAccountService customerAccountService;
    private Scanner keyboardInput;

    public CustomerMenu(Bank bank, Customer customer, Scanner keyboardInput) {
        this.customerService = new CustomerService(bank, customer, keyboardInput);
        this.customerAccountService = new CustomerAccountService(bank, customer, keyboardInput);
        this.keyboardInput = keyboardInput;
    }

    public void displayCustomerOptions() {
        System.out.println("Customer Menu");
        System.out.println("1. Create account");
        System.out.println("2. Close account");
        System.out.println("3. Transfer money");
        System.out.println("4. See existing accounts (in order of creation)");
        System.out.println("5. See existing accounts (in order of descending balance)");
        System.out.println("6. Go into an account");
        System.out.println("7. Rename account");
        System.out.println("8. See user details");
        System.out.println("9. Edit user details");
        System.out.println("10. Change PIN");
        System.out.println("11. Freeze account");
        System.out.println("12. Set savings goal");
        System.out.println("13. View total balance across all accounts");
        System.out.println("14. Check savings progress");
        System.out.println("15. List accounts over a certain value");
        System.out.println("16. Back");
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

    public void processCustomerInput(int selection) {
        if (customerService.isCustomerFrozen()) {
            System.out.println("Your account is frozen. Please contact customer service to unfreeze your account.");
            return;
        }

        switch (selection) {
            case 1:
                customerAccountService.performCreateAdditionalAccount();
                break;
            case 2:
                customerAccountService.performCloseAccount();
                break;
            case 3:
                customerAccountService.performTransfer();
                break;
            case 4:
                customerAccountService.performListAccounts();
                break;
            case 5:
                customerAccountService.performListAccountsByBalance();
                break;
            case 6:
                customerAccountService.performSelectAccount();
                break;
            case 7:
                customerAccountService.performRenameAccount();
                break;
            case 8:
                customerService.performSeeUserDetails();
                break;
            case 9:
                customerService.performEditUserDetails();
                break;
            case 10:
                customerService.performChangePIN();
                break;
            case 11:
                customerService.performFreezeAccount();
                break;
            case 12:
                customerService.performSavingsGoal();
                break;
            case 13:
                customerAccountService.performViewTotalBalance();
                break;
            case 14:
                customerService.performCheckSavingsProgress();
                break;
            case 15:
                customerAccountService.performListAccountsOverValue();
                break;
            case 16:
                System.out.println("Returning to main menu.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    public void runCustomerMenu() {
        int selection = -1;
        while (selection != CUSTOMER_EXIT_SELECTION) {
            displayCustomerOptions();
            selection = getUserSelection(CUSTOMER_MAX_SELECTION);
            processCustomerInput(selection);
        }
    }
}