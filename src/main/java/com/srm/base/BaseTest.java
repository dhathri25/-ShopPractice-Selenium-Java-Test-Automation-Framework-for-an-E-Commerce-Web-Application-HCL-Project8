package com.srm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.srm.utils.ConfigReader;

public class BaseTest {

    public static WebDriver driver;

    @BeforeMethod
    public void setup() {

    	
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl")); 
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
      
    }
}