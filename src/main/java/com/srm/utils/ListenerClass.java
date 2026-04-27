package com.srm.utils;

import org.testng.*;
import com.aventstack.extentreports.*;

import com.srm.base.BaseTest;

public class ListenerClass implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
        System.out.println("PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail("Test Failed");

        String path = ScreenshotUtil.capture(BaseTest.driver, result.getName());

        try {
            test.addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("FAILED: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}