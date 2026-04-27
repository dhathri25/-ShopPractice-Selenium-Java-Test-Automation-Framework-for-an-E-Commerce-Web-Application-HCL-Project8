package com.srm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import com.srm.base.BasePage;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
        
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    By checkoutBtn = By.cssSelector(".totalRow button");
    By placeOrder = By.xpath("//a[normalize-space()='Place Order']");

    By cvv = By.xpath("//div[contains(text(),'CVV')]/following-sibling::input");
    By name = By.xpath("//div[contains(text(),'Name on Card')]/following-sibling::input");
    By country = By.xpath("//input[@placeholder='Select Country']");
    By countryList = By.cssSelector(".ta-results button");

    Wait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(15))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class);

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public void fillDetails() {

        WebElement cvvField = wait.until(ExpectedConditions.visibilityOfElementLocated(cvv));
        js.executeScript("arguments[0].value='123';", cvvField);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(name));
        js.executeScript("arguments[0].value='sravya';", nameField);

        WebElement countryBox = wait.until(ExpectedConditions.elementToBeClickable(country));

        countryBox.click();
        countryBox.clear();
        countryBox.sendKeys("ind");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(countryList));

        List<WebElement> options = driver.findElements(countryList);

        for (WebElement option : options) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase("India")) {
                option.click();
                break;
            }
        }

        String selected = countryBox.getAttribute("value");
        System.out.println("Selected Country: " + selected);
    }
   
    public void placeOrder() {

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrder));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", btn);

        wait.until(ExpectedConditions.elementToBeClickable(btn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);
    }

    public void completeCheckout() {
        clickCheckout();
        fillDetails();
        placeOrder();
    }
}