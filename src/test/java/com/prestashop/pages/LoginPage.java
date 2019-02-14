package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }



    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailAddressBox;

    @FindBy(xpath = "//input[@id='passwd']")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@id='SubmitLogin']//span")
    public WebElement signInButton;


    public void login(String email, String password){
        emailAddressBox.sendKeys(email);
        passwordBox.sendKeys(password);
        signInButton.click();
    }
}
