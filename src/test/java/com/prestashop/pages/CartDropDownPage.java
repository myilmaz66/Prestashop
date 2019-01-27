package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartDropDownPage {
    public CartDropDownPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//span[@class='price cart_block_total ajax_block_cart_total']")
    public WebElement DDCartTotal;

    @FindBy(xpath = "//a[@id='button_order_cart']//span")
    public WebElement DDCheckout;

    @FindBy(xpath = "//div[@class='cart-info']/span")
    public List<WebElement> priceOfDDCartProds;

    @FindBy(xpath = "//a[@class='ajax_cart_block_remove_link']")
    public List <WebElement> deleteProduct;

    @FindBy(xpath = "//a[@title='View my shopping cart']//span[@class='ajax_cart_quantity']")
    public WebElement productCount;

    public int dropDownCartTotal(){
        String total = DDCartTotal.getText();
        return Integer.parseInt(total.substring(1).replace(".",""));
    }


    public int priceOfDDCartProds(int prodOrderNum) {
        int price = 0;
        return price = Integer.parseInt(priceOfDDCartProds.get(prodOrderNum-1).getText().replace(".", "").substring(1));
    }

    public String nameOfDDCartProds(int prodOrderNum){
        List<WebElement> prodNames = Driver.getDriver().findElements(By.xpath("//div[@class='cart-info']/div/a"));
        return prodNames.get(prodOrderNum-1).getAttribute("title");
    }

}
