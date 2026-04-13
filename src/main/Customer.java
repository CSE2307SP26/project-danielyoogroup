package main;

public class Customer {

    private String userName;
    private int userBirthYear;
    private String pin;
    private boolean frozen;
    private double savingsGoal;

    public Customer() {
        this.userName = "Default User";
        this.userBirthYear = 0;
        this.pin = null;
        this.frozen = false;
        this.savingsGoal = 0.0;
    }

    public Customer(String userName, int userBirthYear) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.userName = userName;
        this.userBirthYear = userBirthYear;
        this.pin = null;
        this.frozen = false;
        this.savingsGoal = 0.0;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserBirthYear() {
        return userBirthYear;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.userName = userName;
    }

    public void setUserBirthYear(int userBirthYear) {
        this.userBirthYear = userBirthYear;
    }

    public void setPin(String pin) {
        if (pin == null || pin.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.pin = pin;
    }

    public boolean hasPin() {
        return pin != null;
    }

    public boolean checkPin(String enteredPin) {
        if (pin == null) {
            return true;
        }
        return pin.equals(enteredPin);
    }

    public void freeze() {
        frozen = true;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setSavingsGoal(double savingsGoal) {
        if (savingsGoal < 0) {
            throw new IllegalArgumentException();
        }
        this.savingsGoal = savingsGoal;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }
}
