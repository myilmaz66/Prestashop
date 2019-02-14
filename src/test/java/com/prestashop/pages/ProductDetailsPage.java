package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    public ProductDetailsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@id='center_column']//h1")
    public WebElement productName;

    @FindBy(xpath = "//span[@id='our_price_display']")
    public WebElement productPrice;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    public WebElement quantity;

    @FindBy(xpath = "//select[@id='group_1']")
    public WebElement size;

    @FindBy(xpath = "//span[.='Add to cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//h2)[1]")
    public WebElement addToCartMessage;

}
