package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage {
    public AddToCartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@title='Continue shopping']//span")
    public WebElement continueShopping;

    @FindBy(xpath = "//a[@title='Proceed to checkout']//span")
    public WebElement proceedToCheckout;

    @FindBy(xpath = "//div[@class='clearfix']/div/h2")
    public WebElement cartMsg;

    @FindBy(xpath = "//span[@title='Close window']")
    public WebElement closeWindow;

    @FindBy(id = "layer_cart_product_quantity")
    public WebElement quantity;

    @FindBy(id = "layer_cart_product_attributes")
    public WebElement size;

    @FindBy(xpath = "//span[@id='layer_cart_product_price']")
    public WebElement price;

    @FindBy(xpath = "//span[@id='layer_cart_product_title']")
    public WebElement name;

}
