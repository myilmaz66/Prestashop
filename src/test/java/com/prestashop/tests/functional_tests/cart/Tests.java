package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.Config;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends TestBase {
    HomePage homePage = new HomePage();
    CartMessagePage cartMessagePage = new CartMessagePage();
    CartDropDownPage cartDropDownPage = new CartDropDownPage();
    LoginPage loginPage = new LoginPage();
    AfterAddToCartPage afterAddToCartPage = new AfterAddToCartPage();
    CartPage cartPage = new CartPage();

    @Test
    public void cartLoginTest() throws InterruptedException {
        //2. Go to http://automationpractice.com/index.php
        homePage.open();

        //3. Add any product in the homepage to the cart
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(1000);
        actions.moveToElement(homePage.popularProductLocators.get(2)).perform();
        Thread.sleep(2000);
        homePage.addPopProdsToCart.get(2).click();
        Thread.sleep(2000);
        cartMessagePage.closeWindow.click();

        //4. Hover over the cart icon
        actions.sendKeys(Keys.PAGE_UP).perform();
        Thread.sleep(1000);
        actions.moveToElement(homePage.hoverOverCartIcon).perform();

        //5. Verify that cart contains the product
        Thread.sleep(1000);
        int productPriceInCart = cartDropDownPage.priceOfDDCartProds(1);
        Thread.sleep(1000);
        int prodsPriceInHomePage = homePage.priceOfPopularProds(3);
        Thread.sleep(2000);
        Assert.assertEquals(productPriceInCart, prodsPriceInHomePage);

        String productNameInCart = cartDropDownPage.nameOfDDCartProds(1);
        String productNameInHomepPage = homePage.nameOfPopularProds.get(2).getText();
        Thread.sleep(2000);
        Assert.assertEquals(productNameInHomepPage, productNameInCart);

        //6. Login as any valid user
        loginPage.signInLink.click();
        loginPage.login(Config.getProperty("email"),Config.getProperty("password"));

        //7. Hover over the cart icon
        Thread.sleep(1000);
        actions.moveToElement(homePage.hoverOverCartIcon).perform();

        //8. Verify that cart information is same as step 5
        Thread.sleep(1000);
        Assert.assertEquals(productPriceInCart, cartDropDownPage.priceOfDDCartProds(1));
        Thread.sleep(1000);
        Assert.assertEquals(productNameInCart, cartDropDownPage.nameOfDDCartProds(1));
    }


    @Test
    public void CartLogoutTest() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //2. Go to http://automationpractice.com/index.php
        homePage.open();

        //3. Login as any valid user
        loginPage.signInLink.click();
        loginPage.login(Config.getProperty("email"),Config.getProperty("password"));

        //4. Go back to home page
        homePage.yourLogo.click();

        //5. Add any product in the homepage to the cart
        jse.executeScript("window.scrollBy(0,900)", "");
        Thread.sleep(2000);
        int randomNum = randomNumber(0, homePage.popularProductLocators.size() - 1);
        actions.moveToElement(homePage.popularProductLocators.get(randomNum)).perform();
        homePage.addPopProdsToCart.get(randomNum).click();
        Thread.sleep(1000);
        cartMessagePage.closeWindow.click();

        //6. Hover over the cart icon
        jse.executeScript("window.scrollBy(0,-900)", "");
        Thread.sleep(1000);
        actions.moveToElement(homePage.hoverOverCartIcon).perform();

        //7. Verify that cart contains the product
        Assert.assertEquals(cartDropDownPage.nameOfDDCartProds(1), homePage.nameOfPopularProds.get(randomNum).getText());
        Assert.assertEquals(cartDropDownPage.priceOfDDCartProds(1), homePage.priceOfPopularProds(randomNum + 1));

        //8. Log out
        homePage.signout.click();

        //9. Verify the cart contains the word empty
        Assert.assertTrue(homePage.emptyCart.isDisplayed());
    }

    @Test
    public void cartIconDeleteTest() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //2. Go to http://automationpractice.com/index.php
        homePage.open();

        //3. Add any product in the homepage to the cart
        jse.executeScript("window.scrollBy(0,900)", "");
        Thread.sleep(2000);
        int randomNum = randomNumber(0, homePage.popularProductLocators.size() - 1);
        actions.moveToElement(homePage.popularProductLocators.get(randomNum)).perform();
        homePage.addPopProdsToCart.get(randomNum).click();

        //4. Click on Continue shopping
        Thread.sleep(1000);
        afterAddToCartPage.continueShopping.click();

        //5. Hover over the cart icon
        jse.executeScript("window.scrollBy(0,-900)", "");
        Thread.sleep(1000);
        actions.moveToElement(homePage.hoverOverCartIcon).perform();

        //6. Click the x to delete the product
        Thread.sleep(1000);
        cartDropDownPage.deleteProduct.get(0).click();

        //7. Verify word empty is displayed in the Cart icon
        Thread.sleep(1000);
        Assert.assertTrue(homePage.emptyCart.isDisplayed());
    }

    @Test
    public void CartCheckoutDeleteTest() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //2. Go to http://automationpractice.com/index.php
        homePage.open();

        //3. Add any product in the homepage to the cart
        jse.executeScript("window.scrollBy(0,900)", "");
        Thread.sleep(2000);
        int randomNum = randomNumber(0, homePage.popularProductLocators.size() - 1);
        actions.moveToElement(homePage.popularProductLocators.get(randomNum)).perform();
        homePage.addPopProdsToCart.get(randomNum).click();

        //4. Click on Continue shopping
        Thread.sleep(1000);
        afterAddToCartPage.continueShopping.click();

        //5. Add another product in the homepage to the cart
        int randomNum2 = randomNumber(0, homePage.popularProductLocators.size() - 1);
        actions.moveToElement(homePage.popularProductLocators.get(randomNum2)).perform();
        Thread.sleep(1000);
        homePage.addPopProdsToCart.get(randomNum2).click();

        //6. Click on Proceed to checkout
        Thread.sleep(1000);
        afterAddToCartPage.proceedToCheckout.click();

        //7. Verify message Your shopping cart contains: 2 Products
        Thread.sleep(2000);

        Assert.assertEquals(cartPage.cartProdCountMsg.getText(), "Your shopping cart contains: 2 Products");

        //8. Click the delete icon to delete one of the products
        cartPage.cartDeleteIcon.get(0).click();

        //9. Verify message Your shopping cart contains: 1 Product
        Thread.sleep(3000);
        Assert.assertEquals(cartPage.cartProdCountMsg.getText(), "Your shopping cart contains: 1 Product");

        //10. Click the delete icon to delete the second product
        Thread.sleep(1000);
        cartPage.cartDeleteIcon.get(0).click();

        //11. Verify message Your shopping cart is empty.
        Thread.sleep(1000);
        Assert.assertTrue(cartPage.cartMsgAftrDelete.getText().equals("Your shopping cart is empty."));
    }
}
