package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.AppConstants;
import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductInfo extends TestBase {
    String productName;
    String productPrice;

    @Test
    public void productInfoPrice() {

        extentLogger = report.createTest("Product Info Price Test");
        extentLogger.info("Click on any product");
        int randomNum = BrowserUtils.randomNumber(0, pages.homePage().popularProductLocators.size() - 1);
         productPrice = pages.homePage().priceOfPopularProds.get(randomNum).getText();
         productName = pages.homePage().nameOfPopularProds.get(randomNum).getText();

        actions.moveToElement(pages.homePage().popularProductLocators.get(randomNum)).perform();
        pages.homePage().popularProductLocators.get(randomNum).click();

        extentLogger.info("Verify that same name and price displayed as on the home page");
        Assert.assertEquals(pages.productDetails().productName.getText(), productName);
        Assert.assertEquals(pages.productDetails().productPrice.getText(), productPrice);
    }

    @Test
    public void productInfoDetails() {
        productInfoPrice();
        extentLogger = report.createTest("Product Info Details Test");
        extentLogger.info("Verify that default quantity is 1");
        Assert.assertTrue(pages.productDetails().quantity.getAttribute("value").equals("1"));

        extentLogger.info("Verify that default size is S");
        WebElement selectElement = pages.productDetails().size;
        Select select = new Select(selectElement);
        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("S"));

        extentLogger.info("Verify that size options are S, M, L");
        List<WebElement> selectList = select.getOptions();
        Assert.assertEquals(selectList.get(0).getText(), "S");
        Assert.assertEquals(selectList.get(1).getText(), "M");
        Assert.assertEquals(selectList.get(2).getText(), "L");
    }

    @Test
    public void productInfoAddToCart()  {
        productInfoDetails();
        extentLogger = report.createTest("Product Info Add to Cart Test");
        extentLogger.info("Click on Add to cart");
        pages.productDetails().addToCartButton.click();

        extentLogger.info("Verify confirmation message “Product successfully added to your shopping cart”");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='clearfix']/div/h2")));
        Assert.assertEquals(pages.addToCart().cartMsg.getText().trim(), AppConstants.ADD_TO_CART_MESSAGE);

        extentLogger.info("Verify that default quantity is 1");
        Assert.assertEquals(pages.addToCart().quantity.getText(), "1");

        extentLogger.info("Verify that default size is S");
        Assert.assertEquals(pages.addToCart().size.getText().substring(pages.addToCart().size.getText().length()-1), "S");

        extentLogger.info("Verify that same name and price displayed as on the home page");
        Assert.assertEquals(pages.addToCart().name.getText(), productName);
        Assert.assertEquals(pages.addToCart().price.getText(), productPrice);


    }
}
