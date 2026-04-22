package main;

import java.util.Scanner;

public class CustomerService {

    private Bank bank;
    private Customer customer;
    private Scanner keyboardInput;

    public CustomerService(Bank bank, Customer customer, Scanner keyboardInput) {
        this.bank = bank;
        this.customer = customer;
        this.keyboardInput = keyboardInput;
    }

    public boolean isCustomerFrozen() {
        return customer.isFrozen();
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

    public void performChangePIN() {
        System.out.print("Enter current PIN: ");
        String previousPINEntry = keyboardInput.nextLine();

        if (customer.getPin() != null && customer.getPin().equals(previousPINEntry)) {
            System.out.print("Enter new PIN: ");
            String newPIN = keyboardInput.nextLine();
            customer.setPin(newPIN);
            customer.resetFailedPinAttempts();
            System.out.println("PIN updated successfully.");
        } else {
            if (!customer.checkPin(previousPINEntry)) {
                customer.recordFailedPinAttempt();

                if (customer.isFrozen()) {
                    System.out.println("Too many incorrect PIN attempts. Your account has been frozen.");
                } else {
                    System.out.println("Incorrect PIN. Attempt "
                            + customer.getFailedPinAttempts() + " of 3.");
                }
            }
        }
    }

    public void performFreezeAccount() {
        System.out.print("Are you sure you want to freeze your account? (yes or no): ");
        String input = keyboardInput.nextLine();

        if (input.equalsIgnoreCase("yes")) {
            customer.freeze();
            System.out.println(
                    "Your account has been frozen. You will not be able to make transactions until you contact customer service.");
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

            double totalBalance = bank.getTotalBalance();
            System.out.println("You are currently $" + (goalAmount - totalBalance) + " away from your goal.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid savings goal.");
        }
    }

    public void performCheckSavingsProgress() {
        double goal = customer.getSavingsGoal();
        if (goal <= 0) {
            System.out.println("No Savings Goal Set.");
            return;
        }

        double totalBalance = bank.getTotalBalance();
        double percentage = (totalBalance / goal) * 100;

        System.out.printf("Your total balance is $%.2f. Savings Goal: $%.2f.\n", totalBalance, goal);
        System.out.printf("You have achieved %.2f%% of your savings goal.\n", percentage);

        if (totalBalance >= goal) {
            System.out.println("Congratulations on reaching your savings goal!");
        } else {
            System.out.printf("You need $%.2f more to reach your goal.\n", goal - totalBalance);
        }
    }
}