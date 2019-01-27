package com.prestashop.pages;

import com.prestashop.utilities.Config;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//ul[@id='homefeatured']//a[@class='product-name']/..//following-sibling::div[2]/a/span[.='Add to cart']")
    public List <WebElement> addPopProdsToCart;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    public WebElement hoverOverCartIcon;

    @FindBy(xpath = "//ul[@id='homefeatured']//a[@class='product_img_link']")
    public List<WebElement> popularProductLocators;

    @FindBy(xpath = "//ul[@id='blockbestsellers']//a[@class='product_img_link']")
    public List<WebElement> bestsellerProductLocators;

    @FindBy(xpath = "//ul[@id='homefeatured']//a[@class='product-name']/..//following-sibling::div/span[@class='price product-price']")
    public List<WebElement> priceOfPopularProds;

    @FindBy(xpath = "//ul[@id='homefeatured']//a[@class='product-name']")
    public List<WebElement> nameOfPopularProds;

    @FindBy(xpath = "//img[@class='logo img-responsive']")
    public WebElement yourLogo;

    @FindBy(xpath = "//a[@title='Log me out']")
    public WebElement signout;

    @FindBy(xpath = "//span[@class='ajax_cart_no_product']")
    public WebElement emptyCart;


    public void open(){
        Driver.getDriver().get(Config.getProperty("url"));
    }

    public int priceOfPopularProds(int prodOrderNum) {
        int price = 0;
        return price = Integer.parseInt(priceOfPopularProds.get(prodOrderNum - 1)
                .getText().replace(".", "").substring(1));
    }


}
