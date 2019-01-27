package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@class='heading-counter']")
    public WebElement cartProdCountMsg;

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    public List<WebElement> cartDeleteIcon;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    public WebElement cartMsgAftrDelete;

}
