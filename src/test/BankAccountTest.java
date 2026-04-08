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
            // do nothing, test passes
        }
    }

    @Test
    public void testCollectFee() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.collectFee(25);
        assertEquals(75, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidFee() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.collectFee(-10);
            fail();
        } catch (IllegalArgumentException e) {
            // do nothing, test passes
        }
    }

    @Test
    public void testWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidWithdrawNegative() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.withdraw(-50);
            fail();
        } catch (IllegalArgumentException e) {
            // do nothing, test passes
        }
    }

    @Test
    public void testInvalidWithdrawOverdraft() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        try {
            testAccount.withdraw(10000);
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testCheckAccountBalance() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.checkAccountBalance(), 0.01);
    }

    @Test
    public void testAddInterest() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.addInterest(25);
        assertEquals(125, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidInterest() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.addInterest(-10);
            fail();
        } catch (IllegalArgumentException e) {
            // do nothing, test passes
        }
    }

    @Test
    public void testTransactionHistory() {
        BankAccount account = new BankAccount();
        account.deposit(100);
        account.viewTransactionHistory();
    }

    @Test
    public void testRenameAccount() {
        BankAccount testAccount = new BankAccount("OldName");
        testAccount.setName("NewName");
        assertEquals("NewName", testAccount.getName());
    }

    @Test
    public void testInvalidRenameEmptyName() {
        BankAccount testAccount = new BankAccount("OldName");
        try {
            testAccount.setName("");
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testInvalidRenameBlankName() {
        BankAccount testAccount = new BankAccount("OldName");
        try {
            testAccount.setName("   ");
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testSetPin() {
        BankAccount account = new BankAccount("Checking");
        account.setPin("1234");
        assertEquals(true, account.isPinProtected());
    }

    @Test
    public void testCheckPin() {
        BankAccount account = new BankAccount("Checking");
        account.setPin("1234");
        assertEquals(true, account.checkPin("1234"));
}



}
