package com.srm.pages;

import org.openqa.selenium.*;
import com.srm.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By email = By.id("userEmail");
    By password = By.id("userPassword");
    By loginBtn = By.id("login");

    public void login(String user, String pass) {
        waitForElement(email).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
    
    public boolean isLoginPage() {
        return waitForElement(By.id("login")).isDisplayed();
    }
}