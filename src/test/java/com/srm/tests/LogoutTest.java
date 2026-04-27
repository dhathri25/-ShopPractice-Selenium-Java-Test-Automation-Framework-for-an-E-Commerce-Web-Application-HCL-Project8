package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.base.BaseTest;
import com.srm.pages.LoginPage;
import com.srm.pages.DashboardPage;
import com.srm.utils.ConfigReader;

public class LogoutTest extends BaseTest {

    @Test
    public void logoutTest() {

        LoginPage lp = new LoginPage(driver);
        lp.login(
                ConfigReader.get("email"),
                ConfigReader.get("password")
        );

        DashboardPage dp = new DashboardPage(driver);
        Assert.assertTrue(dp.isDashboardLoaded(), "Dashboard not loaded");

        dp.logout();

        Assert.assertTrue(lp.isLoginPage(), "Logout failed");

        System.out.println("Logout successful");
    }
}