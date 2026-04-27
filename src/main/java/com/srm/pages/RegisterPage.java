package com.srm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.srm.base.BasePage;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    By registerLink = By.linkText("Register");

    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By email = By.id("userEmail");
    By phone = By.id("userMobile");

    By occupation = By.cssSelector("select[formcontrolname='occupation']");
    By femaleRadio = By.xpath("//input[@value='Female']");

    By password = By.id("userPassword");
    By confirmPassword = By.id("confirmPassword");

    By checkbox = By.cssSelector("input[type='checkbox']");
    By registerBtn = By.id("login");

    By successMsg = By.xpath("//*[contains(text(),'Account Created Successfully')]");
    By errorMsg = By.cssSelector(".toast-message");
    
    public void openRegister() {
        waitClickable(registerLink).click();
    }

    public void fillForm(String f, String l, String mail, String ph, String pass) {

        waitForElement(firstName).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(phone).sendKeys(ph);

        Select dropdown = new Select(waitForElement(occupation));
        dropdown.selectByVisibleText("Student");

        WebElement female = waitClickable(femaleRadio);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", female);

        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmPassword).sendKeys(pass);

        WebElement check = waitClickable(checkbox);
        if (!check.isSelected()) {
            check.click();
        }
    }

    public void clickRegister() {

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        waitClickable(registerBtn).click();
    }

    public boolean isRegistrationSuccess() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return waitForElement(errorMsg).getText();
    }
}