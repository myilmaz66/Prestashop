package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FunctionalTestCases extends TestBase {
    @Test
    public void registrationTest() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.findElement(By.id("email_create")).sendKeys("mmyilmaz66@outlook.com");
        driver.findElement(By.id("SubmitCreate")).click();
//        String email = driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value");
//        Assert.assertEquals(email, "mmyilmaz66@outlook.com");

        //fill out the required fields
        Thread.sleep(2000);
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Mustafa");
        driver.findElement(By.id("customer_lastname")).sendKeys("Yilmaz");
        driver.findElement(By.id("passwd")).sendKeys("12345");
        Thread.sleep(2000);
        Select days = new Select(driver.findElement(By.id("days")));
        Select months = new Select(driver.findElement(By.id("months")));
        Select years = new Select(driver.findElement(By.id("years")));
        days.selectByIndex(1);
        months.selectByValue("5");
        years.selectByIndex(29);

        driver.findElement(By.id("firstname")).sendKeys("Mustafa");
        driver.findElement(By.id("lastname")).sendKeys("Yilmaz");
        driver.findElement(By.id("address1")).sendKeys("7836 Kitty Hawk Apt 2");
        driver.findElement(By.id("city")).sendKeys("Live Oak");
        driver.findElement(By.id("id_state")).sendKeys("Texas");
        driver.findElement(By.id("postcode")).sendKeys("78107");
        Select country = new Select(driver.findElement(By.id("id_country")));
        country.selectByVisibleText("United States");
        driver.findElement(By.id("phone_mobile")).sendKeys("2107654356");
        driver.findElement(By.id("alias")).sendKeys("alias");
        driver.findElement(By.id("submitAccount")).click();

        //verify that correct first and last name is displayed correctly on the top right
        String firstName = driver.findElement(By.id("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.id("lastname")).getAttribute("value");
        String fullName = driver.findElement(By.xpath("//span[.='Mustafa Yilmaz']")).getText();

        Assert.assertTrue(fullName.equals(firstName + " " + lastName));
    }

        @Test
                public void registrationTestContinues() {
            driver.get("http://automationpractice.com/index.php");
            driver.findElement(By.className("login")).click();

            driver.findElement(By.id("email")).sendKeys("mmyilmaz66@outlook.com");
            driver.findElement(By.id("passwd")).sendKeys("12345"+ Keys.ENTER);

            //10-> Click on my personal information
            driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=identity']")).click();

            //11->Verify that personal information is displayed correctly
            Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), "Mustafa");
            Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), "Yilmaz");
            Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), "mmyilmaz66@outlook.com");
            Select days = new Select(driver.findElement(By.id("days")));
            Select months = new Select(driver.findElement(By.id("months")));
            Select years = new Select(driver.findElement(By.id("years")));
//            Assert.assertEquals(days.getFirstSelectedOption().getText(), "2");
//            Assert.assertEquals(months.getFirstSelectedOption().getText(), "May");
//            Assert.assertEquals(years.getFirstSelectedOption().getText(), "1991");

            //12-> Click on Back to your account
            driver.findElement(By.xpath("(//a[@class='btn btn-default button button-small'])[2]")).click();

            //12-> Click on My addresses verify that address information is displayed correctly
            driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=addresses']")).click();
            String address = driver.findElement(By.xpath("//span[@class='address_address1']")).getText() +" "+
                    driver.findElement(By.xpath("//div/ul/li[5]/span[1]")).getText()+" "+ driver.findElement(By.xpath("//div/ul/li[5]/span[2]")).getText()
                    +" "+ driver.findElement(By.xpath("//div/ul/li[5]/span[3]")).getText()+" "+ driver.findElement(By.xpath("//div/ul/li[6]")).getText();
            Assert.assertEquals(address,"7836 Kitty Hawk Apt 2 Live Oak, Texas 78107 United States");

            //13-> click on sign out button
            driver.findElement(By.xpath("//a[@class='logout']")).click();



        }

        @Test
         public void messageValidation() throws InterruptedException {
             //1-> Go to the website
             driver.get("http://automationpractice.com/index.php");
             //2-> Click Sign in link
             driver.findElement(By.xpath("//a[@class='login']")).click();
             //3-> Enter new valid email to the email field
             driver.findElement(By.id("email_create")).sendKeys("mmyilmaz6@outlook.com");
             //4-> Click on "create account"
            driver.findElement(By.id("SubmitCreate")).click();

            //5-> Fill all the required steps except for first name
            Thread.sleep(2000);
            driver.findElement(By.id("id_gender1")).click();
            driver.findElement(By.id("customer_firstname")).sendKeys("");
            driver.findElement(By.id("customer_lastname")).sendKeys("Yilmaz");
            driver.findElement(By.id("passwd")).sendKeys("12345");
            Thread.sleep(2000);
            Select days = new Select(driver.findElement(By.id("days")));
            Select months = new Select(driver.findElement(By.id("months")));
            Select years = new Select(driver.findElement(By.id("years")));
            days.selectByIndex(1);
            months.selectByValue("5");
            years.selectByIndex(29);

            driver.findElement(By.id("firstname")).sendKeys("Mustafa");
            driver.findElement(By.id("lastname")).sendKeys("Yilmaz");
            driver.findElement(By.id("address1")).sendKeys("7836 Kitty Hawk Apt 2");
            driver.findElement(By.id("city")).sendKeys("Live Oak");
            driver.findElement(By.id("id_state")).sendKeys("Texas");
            driver.findElement(By.id("postcode")).sendKeys("78107");
            Select country = new Select(driver.findElement(By.id("id_country")));
            country.selectByVisibleText("United States");
            driver.findElement(By.id("phone_mobile")).sendKeys("2107654356");
            driver.findElement(By.id("alias")).sendKeys("alias");

            //6-> Click on Register
            driver.findElement(By.id("submitAccount")).click();

            //7-> verify that error message "firstname is required" is displayed
            Assert.assertTrue(driver.findElement(By.xpath("//div/ol/li/b")).isDisplayed());



    }

    @Test
    public void cartDetails() throws InterruptedException {
        //1-> Go to the website
        driver.get("http://automationpractice.com/index.php");

        //2-> Click on any product that is not on sale
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//img[@title='Printed Summer Dress'])[2]")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]")));

        //3-> Enter a random quantity between 2 and 5
          int randomNumber = (int)(Math.random()*4+2);

        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys(""+randomNumber);

        //4-> Select a different size
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByValue("3");
        //5-> Click on add to cart
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        //6-> Verify confirmation message "Product successfully added to your shopping cart"
        Thread.sleep(2000);
        String cartMessage = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
        Assert.assertEquals(cartMessage, "Product successfully added to your shopping cart");

        //7-> Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@title='Close window']")).click();

        //8-> Click on the company logo
        driver.findElement(By.xpath("//a[@title='My Store']")).click();

        //9-> Click on any product that is on sale
        driver.findElement(By.xpath("(//a[@title='Printed Chiffon Dress']/img)[1]")).click();

        //10-> Enter a random quantity between 2 and 5
        int randomNumber1 = (int)(Math.random()*4+2);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]")));
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys(""+randomNumber1);

        //11-> Select a different size
        Select size1 = new Select(driver.findElement(By.id("group_1")));
        size1.selectByValue("2");

        //12-> Click on add to cart
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        //13-> Verify confirmation message "Product successfully added to your shopping cart"
        Thread.sleep(2000);
        String cartMessage2 = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
        Assert.assertEquals(cartMessage, "Product successfully added to your shopping cart");

        //14-> Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@title='Close window']")).click();

        //15-> Hover over the Cart icon
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='View my shopping cart']"))).perform();

        //16-> Verify that correct total is displayed

        Thread.sleep(1000);
        String cartTotal = driver.findElement(By.xpath("//div/span[@class='price cart_block_total ajax_block_cart_total']")).getText().trim().substring(1);

        Assert.assertEquals(""+(getTheTotalInCart()+2)+"0", cartTotal);
        Assert.assertTrue(driver.findElement(By.xpath("//div/span[@class='price cart_block_total ajax_block_cart_total']")).isDisplayed());

        //17-> Verify that total is correct based on the price and item count of the products you added to cart.(Shipping is always $2)

        System.out.println(totalPriceOfItemsInCart());
    }

    private double getTheTotalInCart() {
        double total = 0.0;
        List<WebElement> pricesInCart = driver.findElements(By.xpath("//span[@class='price']"));

        for (WebElement price : pricesInCart){
            total+= Double.parseDouble(price.getText().substring(1));

        }
        return total;
    }
    private double totalPriceOfItemsInCart() throws InterruptedException {
        double totalPrice=0.0;

        List<WebElement> productsInCart = driver.findElements(By.xpath("//td[@class='cart_product']"));
        List<WebElement> pricesBasedOnItemCountInCart = driver.findElements(By.xpath("//span[contains(@id, \"total_product_price\")]"));
//        List<WebElement> unitPrices = driver.findElements(By.xpath(""));
        List<WebElement> quantities = driver.findElements(By.xpath("//input[@class='cart_quantity_input form-control grey']"));
            driver.findElement(By.xpath("//a[@title='View my shopping cart']")).click();

        for(int i = 0; i< productsInCart.size(); i++){

        }
        return totalPrice;
    }
}
