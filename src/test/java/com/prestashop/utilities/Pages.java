package com.prestashop.utilities;

import com.prestashop.pages.*;

public class Pages {

    private HomePage homePage;
    private CartSignPage cartSign;
    private LoginPage loginPage;
    private AddToCartPage addToCartPage;
    private CartPage cartPage;
    private ProductDetailsPage productDetailsPage;
    private MyAccountPage myAccountPage;
    private MyPersonalInfoPage myPersonalInfoPage;
    private MyAddressesPage myAddressesPage;
    private AddNewAddressPage addNewAddressPage;

    public AddNewAddressPage addNewAddress() {
        if (addNewAddressPage == null) {
            addNewAddressPage= new AddNewAddressPage();
        }
        return addNewAddressPage;
    }
    public MyAddressesPage myAddresses() {
        if (myAddressesPage == null) {
            myAddressesPage= new MyAddressesPage();
        }
        return myAddressesPage;
    }

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public CartSignPage cartSign() {
        if (cartSign == null) {
            cartSign = new CartSignPage();
        }
        return cartSign;
    }

    public LoginPage login() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public AddToCartPage addToCart() {
        if (addToCartPage == null) {
            addToCartPage = new AddToCartPage();
        }
        return addToCartPage;
    }

    public CartPage cart() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }

    public ProductDetailsPage productDetails() {
        if (productDetailsPage == null) {
            productDetailsPage= new ProductDetailsPage();
        }
        return productDetailsPage;
    }
    public MyAccountPage myAccount(){
        if (myAccountPage == null){
            myAccountPage = new MyAccountPage();
        }
        return myAccountPage;
    }

    public MyPersonalInfoPage myPersonalInfo(){
        if (myPersonalInfoPage==null){
            myPersonalInfoPage =new MyPersonalInfoPage();
        }
        return myPersonalInfoPage;
    }
}
