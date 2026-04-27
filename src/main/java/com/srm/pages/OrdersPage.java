package com.srm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

import com.srm.base.BasePage;

public class OrdersPage extends BasePage {

    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    By ordersLink = By.cssSelector("[routerlink='/dashboard/myorders']");
    By orders = By.cssSelector("tbody tr");

    public void openOrders() {
        wait.until(ExpectedConditions.elementToBeClickable(ordersLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(orders));
    }

    public int getOrdersCount() {
        List<WebElement> list = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(orders)
        );
        return list.size();
    }
}