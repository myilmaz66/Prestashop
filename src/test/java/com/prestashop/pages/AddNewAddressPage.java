package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewAddressPage {

    public AddNewAddressPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id='firstname']")
    public WebElement name;

    @FindBy(xpath = "//input[@id='lastname']")
    public WebElement lastName;

    @FindBy(xpath = "//button[@id='submitAddress']")
    public WebElement save;

    @FindBy(xpath = "//div[@class='alert alert-danger']/ol/li")
    public WebElement frstNameRqrdMsg;
}
