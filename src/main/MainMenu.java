package main;

import java.util.Scanner;

public class MainMenu {

    private static final int ROLE_EXIT_SELECTION = 3;
    private static final int ROLE_MAX_SELECTION = 3;

    private Bank bank;
    private Customer customer;
    private Scanner keyboardInput;

    public MainMenu() {
        this.bank = new Bank();
        this.customer = new Customer();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayRoleOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        System.out.println("1. Bank customer");
        System.out.println("2. Bank administrator");
        System.out.println("3. Exit the app");
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

    public void makeCustomerDetails() {
        System.out.print("Enter username: ");
        String userName = keyboardInput.nextLine();

        System.out.print("Enter birth year: ");
        int userBirthYear = keyboardInput.nextInt();
        keyboardInput.nextLine();

        customer.setUserName(userName);
        customer.setUserBirthYear(userBirthYear);
    }

    public void run() {
        int selection = -1;

        while (selection != ROLE_EXIT_SELECTION) {
            displayRoleOptions();
            selection = getUserSelection(ROLE_MAX_SELECTION);

            switch (selection) {
                case 1:
                    makeCustomerDetails();
                    CustomerMenu customerMenu = new CustomerMenu(bank, customer, keyboardInput);
                    customerMenu.runCustomerMenu();
                    break;
                case 2:
                    AdminMenu adminMenu = new AdminMenu(bank, keyboardInput);
                    adminMenu.runAdminMenu();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }
        }
    }
    

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
        // bankApp.keyboardInput.close();
    }
}

