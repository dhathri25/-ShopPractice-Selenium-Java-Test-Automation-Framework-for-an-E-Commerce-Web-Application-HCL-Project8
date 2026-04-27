package com.srm.pages;

import org.openqa.selenium.*;
import com.srm.base.BasePage;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    By cartBtn = By.cssSelector("button[routerlink='/dashboard/cart']");
    By items = By.cssSelector(".cartSection h3");

    public void openCart() {
    	

        WebElement cart = waitForElement(cartBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
    }

    public int getItems() {
        waitForElement(items);
        return driver.findElements(items).size();
    }
  
    By deleteBtn = By.cssSelector(".cartSection .btn-danger");
    
    public void deleteFirstItem() {

        waitForElement(deleteBtn);

        driver.findElements(deleteBtn).get(0).click();
    }
    public void waitForCartToReduce(int oldCount) {
        wait.until(driver -> {
            int newCount = driver.findElements(items).size();
            return newCount < oldCount;
        });
    }
}