package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.srm.base.BaseTest;
import com.srm.pages.*;

public class LoginTest extends BaseTest {

	@DataProvider
	public Object[][] data() {
	    return new Object[][] {
	        {"Sravya@gmail.com", "Test@123", true},   
	        {"wrong@gmail.com", "123", true},
	        {"","",true},
	    };
    }

	@Test(dataProvider = "data")
	public void loginTest(String user, String pass, boolean expected) {

	    LoginPage lp = new LoginPage(driver);
	    lp.login(user, pass);

	    DashboardPage dp = new DashboardPage(driver);

	    boolean isDashboard = dp.isDashboardLoaded();

	    if (expected) {
	        Assert.assertTrue(isDashboard);
	        System.out.println("Logged in successfully");
	    } else {
	        Assert.assertFalse(isDashboard);
	        System.out.println("Login failed");
	    }
	}
}