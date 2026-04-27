package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.base.BaseTest;
import com.srm.pages.*;
import com.srm.utils.ScreenshotUtil;

public class RegisterTest extends BaseTest {

    static String generatedEmail;

    @Test(priority = 1)
    public void invalidRegistrationTest() {

        RegisterPage rp = new RegisterPage(driver);
        rp.openRegister();

        rp.fillForm("Neha", "shetty", "neha@test.com", "9876543210", "neha123");
        rp.clickRegister();

        boolean success = rp.isRegistrationSuccess();

        ScreenshotUtil.capture(driver, "Invalid_Register");

        if (!success) {
            System.out.println("Registration failed because password is invalid");
            Assert.fail("Invalid registration - password does not meet requirements");
        } else {
            System.out.println("Registration succeeded");
        }
    }

    @Test(priority = 2)
    public void validRegistrationTest() {

        RegisterPage rp = new RegisterPage(driver);
        rp.openRegister();

        generatedEmail = "Sravya"  + "@gmail.com";

        rp.fillForm("Sravya", "Alahari", generatedEmail, "9876543210", "Test@123");

        rp.clickRegister();

        boolean status = rp.isRegistrationSuccess();

        Assert.assertTrue(status, "Registration Failed");

        System.out.println("Registered successfully: " + generatedEmail);
    }

}