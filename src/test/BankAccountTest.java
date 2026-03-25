package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testTransactionHistory() {
    BankAccount account = new BankAccount();
    account.deposit(100);
    account.viewTransactionHistory();
}

    @Test
    public void testCreateAdditionalAccount() {
        BankAccount account = new BankAccount();
        account.createAdditionalAccount();
        assertEquals(1, account.getOtherAccounts().size());
    }
}