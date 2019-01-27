package com.prestashop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestBase {

    protected WebDriver driver;
    protected Actions actions;
    protected SoftAssert softAssert;



    @BeforeMethod
    public void setup() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
        softAssert.assertAll();
    }

    //random number generator
    public static int randomNumber(int start, int end) {
        return start + (int) (Math.random() * (end - start + 1));
    }
}
