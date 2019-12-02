package testdome;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
    private double epsilon = 1e-6;

    @Test
    public void accountCannotHaveNegativeOverdraftLimit() {
        Account account = new Account(-20);
        
        Assert.assertEquals(0d, account.getOverdraftLimit(), epsilon);
    }
    
    @Test
    public void testNegativeDeposit() {
    	Account account = new Account(20);
    	double originalBalance = account.getBalance();
    	//The deposit methods will not accept negative numbers.
    	Assert.assertFalse(account.deposit(-1));
    	Assert.assertEquals(originalBalance, account.getBalance(), epsilon);
    	
    }
    
    @Test
    public void testNegativeWithdraw() {
    	Account account = new Account(20);
    	double originalBalance = account.getBalance();
    	//The withdraw methods will not accept negative numbers.
    	Assert.assertFalse(account.withdraw(-1));
    	Assert.assertEquals(originalBalance, account.getBalance(), epsilon);
    }
    
    @Test
    public void testOverStep() {
    	Account account = new Account(20);
    	double originalBalance = account.getBalance();
    	//Account cannot overstep its overdraft limit.
    	Assert.assertFalse(account.withdraw(originalBalance + account.getOverdraftLimit() + 1));
    	Assert.assertEquals(originalBalance, account.getBalance(), epsilon);
    }

    //The deposit and withdraw methods will deposit or withdraw the correct amount, respectively.
    @Test
    public void testDeposit() {
    	Account account = new Account(20);
    	//The deposit methods will not accept negative numbers.
    	Assert.assertTrue(account.deposit(10));
    	Assert.assertEquals(10, account.getBalance(), epsilon);
    	Assert.assertTrue(account.deposit(20));
    	Assert.assertEquals(30, account.getBalance(), epsilon);
    	
    }
    
    @Test
    public void testWithdraw() {
    	Account account = new Account(20);
    	//The withdraw methods will not accept negative numbers.
    	Assert.assertTrue(account.withdraw(10));
    	Assert.assertEquals(-10, account.getBalance(), epsilon);
    }

    //The withdraw and deposit methods return the correct results.
    
}