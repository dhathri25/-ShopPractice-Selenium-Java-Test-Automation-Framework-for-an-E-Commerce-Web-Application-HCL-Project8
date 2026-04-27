package com.srm.pages;

import org.openqa.selenium.*;

import com.srm.base.BasePage;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    By products = By.cssSelector(".card-body");
    By addToCartBtns = By.cssSelector(".card-body button:last-of-type");
    By dashboardText = By.xpath("//*[contains(text(),'Showing')]");
    
    By cartCount = By.cssSelector("button[routerlink='/dashboard/cart'] label");

    By logoutBtn = By.xpath("//button[normalize-space()='Sign Out']");

    public void logout() {
        waitClickable(logoutBtn).click();
    }
    
    public int getCartCount() {
        try {
            String count = waitForElement(cartCount).getText();
            return Integer.parseInt(count);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getProductCount() {
        return driver.findElements(products).size();
    }

    public boolean isDashboardLoaded() {
        try {
            return waitForElement(dashboardText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void addFirstProduct() {
        waitForElement(addToCartBtns);
        driver.findElements(addToCartBtns).get(0).click();
    }
    public void waitForCartUpdate(int oldCount) {

        wait.until(driver -> {
            try {
                String text = driver.findElement(cartCount).getText();
                int newCount = Integer.parseInt(text);
                return newCount > oldCount;
            } catch (Exception e) {
                return false;
            }
        });
    }
}