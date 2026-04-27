package com.srm.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.base.BaseTest;
import com.srm.pages.*;
import com.srm.utils.ConfigReader;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkoutTest() {

        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.get("email"),
                ConfigReader.get("password")
        );

        DashboardPage dp = new DashboardPage(driver);
        Assert.assertTrue(dp.isDashboardLoaded());

        dp.addFirstProduct();

        CartPage cart = new CartPage(driver);
        cart.openCart();

        Assert.assertTrue(cart.getItems() > 0);

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.completeCheckout();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

     WebElement successMsg = wait.until(
             ExpectedConditions.visibilityOfElementLocated(
                     By.cssSelector(".hero-primary")
             )
     );

     Assert.assertTrue(successMsg.getText().contains("THANKYOU FOR THE ORDER"));

     System.out.println("Checkout successful");
    }
}