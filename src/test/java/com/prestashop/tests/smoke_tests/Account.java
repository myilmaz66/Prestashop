package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.AppConstants;
import com.prestashop.utilities.Config;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Account extends TestBase {

    @Test(priority = 1)
    public void myAccount() {
        extentLogger = report.createTest("My Account Test");
        extentLogger.info("Click Sign in link");
        pages.homePage().signIn.click();

        extentLogger.info("Login using valid username and password");
        pages.login().login(Config.getProperty("email"), Config.getProperty("password"));

        extentLogger.info("Verify that title contains My account");
        Assert.assertTrue(Driver.getDriver().getTitle().contains("My account"));

        extentLogger.info("Verify that account holder full name is displayed next to the Sign out link");
        Assert.assertTrue(pages.homePage().accHolderFullName.isDisplayed());
    }

    @Test(priority = 2)
    public void myPersonalInfo() {
        myAccount();
        extentLogger = report.createTest("My Personal Information Test");
        extentLogger.info("Click on My personal information button");
        pages.myAccount().myPersonalInfo.click();

        extentLogger.info("Verify title contains Identity");
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Identity"));

        extentLogger.info("Verify that first name and last name matches the full name on top");
        Assert.assertEquals(pages.myPersonalInfo().name.getAttribute("value") + " " +
        pages.myPersonalInfo().lastName.getAttribute("value"), pages.homePage().accHolderFullName.getText());

        extentLogger.info("Click on Save button");
        pages.myPersonalInfo().saveButton.click();

        extentLogger.info("Verify error message “The password you entered is incorrect.”");
        Assert.assertEquals(pages.myPersonalInfo().wrongPswrdMsg.getText(), AppConstants.WRON_PSWRD_MSG);

        extentLogger.info("Click on Back to your account");
        pages.myPersonalInfo().backToAccnt.click();

        extentLogger.info("Verify that title contains My account");
        Assert.assertTrue(Driver.getDriver().getTitle().contains("My account"));
    }


    @Test(priority = 3)
    public void myAdresses() throws InterruptedException {
        myPersonalInfo();
        extentLogger = report.createTest("My Addresses Test");
        extentLogger.info("Click on My addresses");
        pages.myAddresses().myAdresses.click();

        extentLogger.info("Click on Add a new address");
        pages.myAddresses().addNewAdresses.click();

        extentLogger.info("Verify that first name and last name matches the full name on top");
        Assert.assertEquals(pages.addNewAddress().name.getAttribute("value")
                + " " + pages.addNewAddress().lastName.getAttribute("value"), pages.homePage().accHolderFullName.getText());

        extentLogger.info("Delete the first name");
        pages.addNewAddress().name.clear();

        extentLogger.info("Click save");
        pages.addNewAddress().save.click();

        extentLogger.info("Verify error message “firstname is required.”");
        Assert.assertEquals(pages.addNewAddress().frstNameRqrdMsg.getText(),
                AppConstants.FIRSTNAME_REQUIRED_MSG);
    }
}
