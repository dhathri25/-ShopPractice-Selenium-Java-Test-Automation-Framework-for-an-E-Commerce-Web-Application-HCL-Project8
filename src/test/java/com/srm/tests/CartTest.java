package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.base.BaseTest;
import com.srm.pages.*;
import com.srm.utils.ConfigReader;

public class CartTest extends BaseTest {

	@Test
	public void cartTest() {

	    LoginPage lp = new LoginPage(driver);
	    lp.login(ConfigReader.get("email"), ConfigReader.get("password"));

	    DashboardPage dp = new DashboardPage(driver);
	    Assert.assertTrue(dp.isDashboardLoaded(), " Dashboard not loaded");

	    int before = dp.getCartCount();

	    dp.addFirstProduct();
	    dp.waitForCartUpdate(before);

	    int after = dp.getCartCount();

	    if (after > before) {
	        System.out.println("Cart test passed");
	        Assert.assertTrue(true);
	    } else {
	        System.out.println("Cart tests failed");
	        Assert.fail();
	    }
	}
	@Test
	public void deleteFromCartTest() {

	    LoginPage lp = new LoginPage(driver);
	    lp.login(ConfigReader.get("email"), ConfigReader.get("password"));

	    DashboardPage dp = new DashboardPage(driver);
	    Assert.assertTrue(dp.isDashboardLoaded());

	    dp.addFirstProduct();

	    CartPage cp = new CartPage(driver);
	    cp.openCart();

	    int before = cp.getItems();

	    cp.deleteFirstItem();             

	    cp.waitForCartToReduce(before);    
	
	    boolean isEmptyCart = driver.getPageSource().contains("No Products in Your Cart");

	    Assert.assertTrue(isEmptyCart, "Cart is not empty after delete");

	    System.out.println("Cart is empty - delete successful");

	}
}