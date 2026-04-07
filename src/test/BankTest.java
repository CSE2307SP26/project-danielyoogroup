package test;

import main.Bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class BankTest {

    @Test
    public void testCreateAccount() {
        Bank bank = new Bank();
        bank.createAccount("Savings");
        assertEquals(1, bank.getNumberOfAccounts());
    }

    @Test
    public void testCloseAccount() {
        Bank bank = new Bank();
        bank.createAccount("Savings");
        bank.closeAccount(0);
        assertEquals(0, bank.getNumberOfAccounts());
    }

    @Test
    public void testTransferBetweenAccounts() {
        Bank bank = new Bank();
        bank.createAccount("Savings");
        bank.createAccount("Savings");

        bank.getAccount(0).deposit(100);
        bank.transferMoney(0, 1, 40);

        assertEquals(60, bank.getAccount(0).getBalance(), 0.01);
        assertEquals(40, bank.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testInvalidTransferSameAccount() {
        Bank bank = new Bank();
        bank.createAccount("Savings");

        try {
            bank.transferMoney(0, 0, 10);
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testInvalidTransferOverdraft() {
        Bank bank = new Bank();
        bank.createAccount("Savings");
        bank.createAccount("Savings");

        bank.getAccount(0).deposit(20);

        try {
            bank.transferMoney(0, 1, 50);
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }



}
