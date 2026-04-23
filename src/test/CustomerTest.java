package test;

import main.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void testDefaultCustomer() {
        Customer customer = new Customer();
        assertEquals("Default User", customer.getUserName());
        assertEquals(0, customer.getUserBirthYear());
        assertEquals(0.0, customer.getSavingsGoal(), 0.01);
    }

    @Test
    public void testCustomerConstructor() {
        Customer customer = new Customer("Shelby", 2005);
        assertEquals("Shelby", customer.getUserName());
        assertEquals(2005, customer.getUserBirthYear());
    }

    @Test
    public void testSetUserName() {
        Customer customer = new Customer();
        customer.setUserName("Shelby");
        assertEquals("Shelby", customer.getUserName());
    }

    @Test
    public void testInvalidSetUserNameEmpty() {
        Customer customer = new Customer();
        try {
            customer.setUserName("");
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testInvalidSetUserNameBlank() {
        Customer customer = new Customer();
        try {
            customer.setUserName("   ");
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testSetUserBirthYear() {
        Customer customer = new Customer();
        customer.setUserBirthYear(2005);
        assertEquals(2005, customer.getUserBirthYear());
    }

    @Test
    public void testSetPin() {
        Customer customer = new Customer();
        customer.setPin("1234");
        assertTrue(customer.hasPin());
    }

    @Test
    public void testCheckPin() {
        Customer customer = new Customer();
        customer.setPin("1234");
        assertTrue(customer.checkPin("1234"));
    }

    @Test
    public void testIncorrectPin() {
        Customer customer = new Customer();
        customer.setPin("1234");
        assertEquals(false, customer.checkPin("0000"));
    }

    @Test
    public void testInvalidPinEmpty() {
        Customer customer = new Customer();
        try {
            customer.setPin("");
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testInvalidPinBlank() {
        Customer customer = new Customer();
        try {
            customer.setPin("   ");
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testFreezeCustomer() {
        Customer customer = new Customer();
        customer.freeze();
        assertTrue(customer.isFrozen());
    }

    @Test
    public void testSetSavingsGoal() {
        Customer customer = new Customer();
        customer.setSavingsGoal(500.0);
        assertEquals(500.0, customer.getSavingsGoal(), 0.01);
    }

    @Test
    public void testInvalidSavingsGoal() {
        Customer customer = new Customer();
        try {
            customer.setSavingsGoal(-100.0);
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testVerifyIdentityCorrect() {
        Customer customer = new Customer("Shelby", 2005);
        assertEquals(true, customer.verifyIdentity("Shelby", 2005));
    }

    @Test
    public void testVerifyIdentityWrongName() {
        Customer customer = new Customer("Shelby", 2005);
        assertEquals(false, customer.verifyIdentity("Anna", 2005));
    }

    @Test
    public void testVerifyIdentityWrongBirthYear() {
        Customer customer = new Customer("Shelby", 2005);
        assertEquals(false, customer.verifyIdentity("Shelby", 2004));
    }

    @Test
    public void testUnfreezeCustomer() {
        Customer customer = new Customer();
        customer.freeze();
        customer.unfreeze();
        assertEquals(false, customer.isFrozen());
    }

    @Test
    public void testFailedPinAttemptsIncrease() {
        Customer customer = new Customer("Daniel", 2003);
        customer.setPin("1234");

        customer.recordFailedPinAttempt();
        assertEquals(1, customer.getFailedPinAttempts());
        assertFalse(customer.isFrozen());
    }

    @Test
    public void testCustomerFreezesAfterThreeFailedPinAttempts() {
        Customer customer = new Customer("Daniel", 2003);
        customer.setPin("1234");

        customer.recordFailedPinAttempt();
        customer.recordFailedPinAttempt();
        customer.recordFailedPinAttempt();

        assertEquals(3, customer.getFailedPinAttempts());
        assertTrue(customer.isFrozen());
    }

    @Test
    public void testResetFailedPinAttempts() {
        Customer customer = new Customer("Daniel", 2003);
        customer.setPin("1234");

        customer.recordFailedPinAttempt();
        customer.recordFailedPinAttempt();
        customer.resetFailedPinAttempts();

        assertEquals(0, customer.getFailedPinAttempts());
    }

    @Test
    public void testUnfreezeResetsFailedPinAttempts() {
        Customer customer = new Customer("Daniel", 2003);
        customer.setPin("1234");

        customer.recordFailedPinAttempt();
        customer.recordFailedPinAttempt();
        customer.recordFailedPinAttempt();

        customer.unfreeze();

        assertEquals(0, customer.getFailedPinAttempts());
        assertFalse(customer.isFrozen());
    }

}