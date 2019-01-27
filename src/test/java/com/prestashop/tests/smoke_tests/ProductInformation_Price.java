package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

public class ProductInformation_Price extends TestBase {

    @Test(priority = 1)
    public void productInformation() throws InterruptedException {
        //price and name
        driver.get("http://automationpractice.com/index.php");
        String productPrice = driver.findElement(By.xpath("(//span[@itemprop='price'])[2]")).getText();
        String productName = driver.findElement(By.xpath("(//a[@class='product-name'])[1]")).getText();

        driver.findElement(By.xpath("(//img[@class='replace-2x img-responsive'])[1]")).click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(iframe);

        String nameAfterClick = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
        String priceAfterClick = driver.findElement(By.id("our_price_display")).getText();
        Assert.assertEquals(productName, nameAfterClick);
        Assert.assertEquals(productPrice, priceAfterClick);

        //details

        String defaultQuantity = driver.findElement(By.id("quantity_wanted")).getAttribute("value");
        Assert.assertEquals(defaultQuantity, "1");


        WebElement selectElement = driver.findElement(By.id("group_1"));
        Select select = new Select(selectElement);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "S");


        //how to select the text of an option under select tag

        //Second and easy way
        List<WebElement> selectList = select.getOptions();
        Assert.assertEquals(selectList.get(0).getText(),"S");
        Assert.assertEquals(selectList.get(1).getText(),"M");
        Assert.assertEquals(selectList.get(2).getText(),"L");


        //add to cart

        driver.findElement(By.xpath("//span[.='Add to cart']")).click();
        Thread.sleep(3000);
        String cartMessage = driver.findElement(By.xpath("(//h2)[1]")).getText();
        Assert.assertEquals(cartMessage, "Product successfully added to your shopping cart");

        String defaultQuantity2 = driver.findElement(By.id("layer_cart_product_quantity")).getText();
        Assert.assertEquals(defaultQuantity2, "1");

        String defaultSize = driver.findElement(By.id("layer_cart_product_attributes")).getText();
        Assert.assertEquals(defaultSize.substring(defaultSize.length()-1),"S");

        String nameAfterAddCart = driver.findElement(By.id("layer_cart_product_title")).getText();
        Assert.assertEquals(nameAfterAddCart, productName );

        String priceAfterAddCart = driver.findElement(By.id("layer_cart_product_price")).getText();
        Assert.assertEquals(priceAfterAddCart, productPrice);
    }

    @Test
    public void account() throws InterruptedException {

        //Login: my account
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.className("login")).click();

        driver.findElement(By.id("email")).sendKeys("mmyilmaz66@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("12345"+ Keys.ENTER);

        Assert.assertTrue(driver.getTitle().contains("My account"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[.='Mustafa Yilmaz']")).isDisplayed());

        //Login: My Personal Information
        driver.findElement(By.xpath("//a[.='My personal info']")).click();
        Assert.assertTrue(driver.getTitle().contains("Identity"));

        String firstName = driver.findElement(By.id("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.id("lastname")).getAttribute("value");
        String fullName = driver.findElement(By.xpath("//span[.='Mustafa Yilmaz']")).getText();

        Assert.assertTrue(fullName.equals(firstName + " " + lastName));
        driver.findElement(By.xpath("//span[.='Save']")).click();
        String errorMessage = driver.findElement(By.xpath("//li[.='The password you entered is incorrect.']")).getText();

        Assert.assertEquals(errorMessage, "The password you entered is incorrect.");

        driver.findElement(By.xpath("(//a[@class='btn btn-default button button-small'])[2]")).click();
        Assert.assertTrue(driver.getTitle().contains("My account"));

        //Login: My address
          driver.findElement(By.xpath("//a[@title='Addresses']")).click();
          driver.findElement(By.xpath("//a[@title='Add an address']")).click();

         firstName = driver.findElement(By.id("firstname")).getAttribute("value");
         lastName = driver.findElement(By.id("lastname")).getAttribute("value");
        fullName = driver.findElement(By.xpath("//span[.='Mustafa Yilmaz']")).getText();
        Assert.assertTrue(fullName.equals(firstName + " " + lastName));
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("submitAddress")).click();
        String errorMessage2 =  driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();
        Assert.assertEquals(errorMessage2, "firstname is required.");

    }

}
