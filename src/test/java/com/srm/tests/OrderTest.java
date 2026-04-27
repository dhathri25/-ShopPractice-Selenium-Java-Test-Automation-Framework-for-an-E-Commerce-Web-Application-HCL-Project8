package com.srm.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.base.BaseTest;
import com.srm.pages.*;
import com.srm.utils.ConfigReader;

public class OrderTest extends BaseTest {

	@Test
    public void orderTest() {

        new LoginPage(driver)
                .login(ConfigReader.get("email"), ConfigReader.get("password"));

        DashboardPage dp = new DashboardPage(driver);
        Assert.assertTrue(dp.isDashboardLoaded(), "Dashboard not loaded");

        dp.addFirstProduct();

        CartPage cp = new CartPage(driver);
        cp.openCart();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.completeCheckout();

        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15))
                .until(org.openqa.selenium.support.ui.ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector(".hero-primary")));

        OrdersPage op = new OrdersPage(driver);
        op.openOrders();

        int count = op.getOrdersCount();
    
        driver.findElement(By.xpath("//button[normalize-space()='View']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Thank you for Shopping With Us')]")
                ));

        boolean isOrderSummaryPage = driver.getPageSource().contains("Thank you for Shopping With Us");

        Assert.assertTrue(isOrderSummaryPage, "Order Summary page not displayed");

        System.out.println("Order Summary page verified successfully");

        System.out.println("Orders count: " + count);

        Assert.assertTrue(count > 0, "Order not created");

        System.out.println("Order test passed");
           
    }
}