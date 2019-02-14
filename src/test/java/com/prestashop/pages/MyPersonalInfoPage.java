package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyPersonalInfoPage {
    public MyPersonalInfoPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//input[@id='firstname']")
    public WebElement name;

    @FindBy(xpath = "//input[@id='lastname']")
    public WebElement lastName;

    @FindBy(xpath = "//button[@name='submitIdentity']")
    public WebElement saveButton;

    @FindBy(xpath = "//li[.='The password you entered is incorrect.']")
    public WebElement wrongPswrdMsg;

    @FindBy(xpath = "(//a[@class='btn btn-default button button-small'])[2]")
    public WebElement backToAccnt;
}

