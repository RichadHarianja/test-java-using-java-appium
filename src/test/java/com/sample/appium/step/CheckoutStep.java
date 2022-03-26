package com.sample.appium.step;

import com.sample.appium.test.AbstractTest;

import com.sample.appium.pages.CheckoutPage;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class CheckoutStep extends AbstractTest {
    protected static AppiumDriver driver;

    @Test
    public void addNewAddress(){
        CheckoutPage checkoutPage = new CheckoutPage(service);

        checkoutPage.clickItem("Cotton Black Cap");
        checkoutPage.clickAdddToCart();
        checkoutPage.clickAddress();
        checkoutPage.clickAddNewAddress();
        checkoutPage.setName("Testing Data Name");
        checkoutPage.setMobile("081111111111");
        checkoutPage.setPincode("211221");
        checkoutPage.setHouse("Testing Data House");
        checkoutPage.setRoad("Testing Data Road");
        checkoutPage.setCity("Testing Data City");
        checkoutPage.saveUpdateAddress();

        assertTrue(checkoutPage.checkAddressExists("Testing Data Name"));

        checkoutPage.selectAddress("Testing Data Name");
        checkoutPage.goBack();
        checkoutPage.checkout();

        assertTrue(service.checkPopupWithTitleExists("Success!"));
        service.closePopup();

    }

    @Override
    public void testSetup() {
    }


}
