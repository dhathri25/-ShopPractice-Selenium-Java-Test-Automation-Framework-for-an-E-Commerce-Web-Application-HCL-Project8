package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.base.BaseTest;
import com.srm.pages.*;
import com.srm.utils.ConfigReader;

public class ProductTest extends BaseTest {

	@Test
	public void productTest() {

	    new LoginPage(driver)
	        .login(ConfigReader.get("email"), ConfigReader.get("password"));

	    DashboardPage dp = new DashboardPage(driver);

	    Assert.assertTrue(dp.isDashboardLoaded(), "Dashboard not loaded");

	    int count = dp.getProductCount();

	    Assert.assertTrue(count > 0, "No products displayed");

	    System.out.println("Product test passed - Products: " + count);
	}
}