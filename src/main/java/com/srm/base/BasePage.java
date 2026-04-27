package com.srm.base;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.srm.utils.ConfigReader;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public FluentWait<WebDriver> fwait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        

        wait = new WebDriverWait(driver,
                Duration.ofSeconds(Integer.parseInt(ConfigReader.get("timeout"))));

        fwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }

    public WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement fluentWait(By locator) {
        return fwait.until(driver -> driver.findElement(locator));
    }
}