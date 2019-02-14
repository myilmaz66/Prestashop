package com.prestashop.tests.functional_tests.cart;

import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends TestBase {



    @Test
    public void cartIconDeleteTest() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        extentLogger = report.createTest("Cart Icon Delete Test");

        extentLogger.info("Add any product in the homepage to the cart");
        jse.executeScript("window.scrollBy(0,900)", "");
        Thread.sleep(2000);
        int randomNum = BrowserUtils.randomNumber(0, pages.homePage().popularProductLocators.size() - 1);
        actions.moveToElement(pages.homePage().popularProductLocators.get(randomNum)).perform();
        pages.homePage().addPopProdsToCart.get(randomNum).click();

        extentLogger.info("Click on Continue shopping");
        Thread.sleep(1000);
        pages.addToCart().continueShopping.click();

        extentLogger.info("Hover over the cart icon");
        jse.executeScript("window.scrollBy(0,-900)", "");
        Thread.sleep(1000);
        actions.moveToElement(pages.homePage().hoverOverCartIcon).perform();

        extentLogger.info("Click on \"x\" to delete the product");
        Thread.sleep(1000);
        pages.cartSign().deleteProduct.get(0).click();

        extentLogger.info("Verify word empty is displayed in the Cart icon");
        Thread.sleep(1000);
        Assert.assertTrue(pages.homePage().emptyCart.isDisplayed());
    }

    @Test
    public void cartCheckoutDeleteTest() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        extentLogger = report.createTest("Cart Icon Delete Test");

        extentLogger.info("Add any product in the homepage to the cart");
        jse.executeScript("window.scrollBy(0,900)", "");
        Thread.sleep(2000);
        int randomNum = BrowserUtils.randomNumber(0, pages.homePage().popularProductLocators.size() - 1);
        actions.moveToElement(pages.homePage().popularProductLocators.get(randomNum)).perform();
        pages.homePage().addPopProdsToCart.get(randomNum).click();

        extentLogger.info("Click on Continue shopping");
        Thread.sleep(1000);
        pages.addToCart().continueShopping.click();

        extentLogger.info("Add another product in the homepage to the cart");
        int randomNum2 = BrowserUtils.randomNumber(0, pages.homePage().popularProductLocators.size() - 1);
        actions.moveToElement(pages.homePage().popularProductLocators.get(randomNum2)).perform();
        Thread.sleep(1000);
        pages.homePage().addPopProdsToCart.get(randomNum2).click();

        extentLogger.info("Click on Proceed to checkout");
        Thread.sleep(1000);
        pages.addToCart().proceedToCheckout.click();

        extentLogger.info("Verify message Your shopping cart contains: 2 Products");
        Thread.sleep(2000);
        Assert.assertEquals(pages.cart().cartProdCountMsg.getText(), "Your shopping cart contains: 2 Products");

        extentLogger.info("Click the delete icon to delete one of the products");
        pages.cart().cartDeleteIcon.get(0).click();

        extentLogger.info("Verify message Your shopping cart contains: 1 Product");
        Thread.sleep(3000);
        Assert.assertEquals(pages.cart().cartProdCountMsg.getText(), "Your shopping cart contains: 1 Product");

        extentLogger.info("Click the delete icon to delete the second product");
        Thread.sleep(1000);
        pages.cart().cartDeleteIcon.get(0).click();

        extentLogger.info("Verify message Your shopping cart is empty");
        Thread.sleep(1000);
        Assert.assertTrue(pages.cart().cartMsgAftrDelete.getText().equals("Your shopping cart is empty."));
    }
}
