package com.prestashop.tests.functional_tests.login;

import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.Config;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @Test
    public void cartLoginTest() throws InterruptedException {
        extentLogger = report.createTest(" Cart Login Test");

        extentLogger.info("Add any product in the homepage to the cart");
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(1000);
        actions.moveToElement(pages.homePage().popularProductLocators.get(2)).perform();
        Thread.sleep(2000);
        pages.homePage().addPopProdsToCart.get(2).click();
        Thread.sleep(2000);
        pages.addToCart().closeWindow.click();

        extentLogger.info("Hover over the cart icon");
        actions.sendKeys(Keys.PAGE_UP).perform();
        Thread.sleep(1000);
        actions.moveToElement(pages.homePage().hoverOverCartIcon).perform();

        extentLogger.info("Verify that cart contains the product");
        Thread.sleep(1000);
        int productPriceInCart = pages.cartSign().priceOfDDCartProds(1);
        Thread.sleep(1000);
        int prodsPriceInHomePage = pages.homePage().priceOfPopularProds(3);
        Thread.sleep(2000);
        Assert.assertEquals(productPriceInCart, prodsPriceInHomePage);

        String productNameInCart = pages.cartSign().nameOfDDCartProds(1);
        String productNameInHomepPage = pages.homePage().nameOfPopularProds.get(2).getText();
        Thread.sleep(2000);
        Assert.assertEquals(productNameInHomepPage, productNameInCart);

        extentLogger.info("Login as any valid user");
        pages.homePage().signIn.click();
        pages.login().login(Config.getProperty("email"),Config.getProperty("password"));

        extentLogger.info("Hover over the cart icon");
        Thread.sleep(1000);
        actions.moveToElement(pages.homePage().hoverOverCartIcon).perform();

        extentLogger.info("Verify that cart information is same as step 5");
        Thread.sleep(1000);
        Assert.assertEquals(productPriceInCart, pages.cartSign().priceOfDDCartProds(1));
        Thread.sleep(1000);
        Assert.assertEquals(productNameInCart, pages.cartSign().nameOfDDCartProds(1));
    }


    @Test
    public void cartLogoutTest() throws InterruptedException {
        extentLogger = report.createTest("Cart Logout Test");

        extentLogger.info("Login as any valid user");
        pages.homePage().signIn.click();
        pages.login().login(Config.getProperty("email"),Config.getProperty("password"));

        extentLogger.info("Go back to home page");
        pages.homePage().yourLogo.click();

        extentLogger.info("Add any product in the homepage to the cart");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,900)", "");
        Thread.sleep(2000);
        int randomNum = BrowserUtils.randomNumber(0, pages.homePage().popularProductLocators.size() - 1);
        actions.moveToElement(pages.homePage().popularProductLocators.get(randomNum)).perform();
        pages.homePage().addPopProdsToCart.get(randomNum).click();
        Thread.sleep(1000);
        pages.addToCart().closeWindow.click();

        extentLogger.info("Hover over the cart icon");
        jse.executeScript("window.scrollBy(0,-900)", "");
        Thread.sleep(1000);
        actions.moveToElement(pages.homePage().hoverOverCartIcon).perform();

        extentLogger.info("Verify that cart contains the product");
        Assert.assertEquals(pages.cartSign().nameOfDDCartProds(1), pages.homePage().nameOfPopularProds.get(randomNum).getText());
        Assert.assertEquals(pages.cartSign().priceOfDDCartProds(1), pages.homePage().priceOfPopularProds(randomNum + 1));

        extentLogger.info("Log out");
        pages.homePage().signout.click();

        extentLogger.info("Verify the cart contains the word empty");
        Assert.assertTrue(pages.homePage().emptyCart.isDisplayed());
    }
}
