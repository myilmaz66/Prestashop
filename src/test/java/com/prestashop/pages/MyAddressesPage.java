package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAddressesPage {
    public MyAddressesPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[@title='Addresses']")
    public WebElement myAdresses;

    @FindBy(xpath = "//a[@title='Add an address']")
    public WebElement addNewAdresses;




}
