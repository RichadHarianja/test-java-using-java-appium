package com.sample.appium.pages;

import com.sample.appium.utils.AppiumService;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    @iOSXCUITFindBy(id = "buy-now")
    private MobileElement addToCart;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"add-select-address\"`][2]")
    private MobileElement address;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"add-new-address\"`][9]")
    private MobileElement addNewAddress;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"name\"`]")
    private MobileElement name;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"mobile-number\"`]")
    private MobileElement mobile;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"pincode\"`]")
    private MobileElement pincode;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"house-no\"`]")
    private MobileElement house;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"road-name\"`]")
    private MobileElement road;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"city\"`]")
    private MobileElement city;

    @iOSXCUITFindBy(id = "save-update-address")
    private MobileElement saveUpdateAddress;

    @iOSXCUITFindBy(id = "Order Summary, back")
    private MobileElement backButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"place-order\"`]")
    private MobileElement placeOrder;

    private AppiumService appium;

    public CheckoutPage(AppiumService appium) {
        this.appium = appium;
        PageFactory.initElements(new AppiumFieldDecorator(appium.getDriver()), this);
    }

    public void clickItem(String name) {
        MobileElement item = appium.findElementContainText(name);
        appium.click(item);
    }

    public void clickAdddToCart() { appium.click(addToCart); }

    public void clickAddress() { appium.click(address); }

    public void clickAddNewAddress() { appium.click(addNewAddress); }

    public void setName(String name2) {
        appium.sendKeys(name, name2);
    }

    public void setPincode(String pincode2) {
        appium.sendKeys(pincode, pincode2);
    }

    public void setMobile(String mobile2) {
        appium.sendKeys(mobile, mobile2);
    }

    public void setHouse(String houseNo) { appium.sendKeys(house, houseNo); }

    public void setRoad(String road2) { appium.hideIOSKeyboard(); appium.sendKeys(road, road2); }

    public void setCity(String city2) { appium.hideIOSKeyboard(); appium.sendKeys(city, city2); }

    public void saveUpdateAddress() {
        appium.hideIOSKeyboard();
        appium.click(saveUpdateAddress);
    }

    public Boolean checkAddressExists (String name) {
        MobileElement address = appium.findElementContainText(name);
        if (address != null)
            return true;
        return false;
    }

    public void selectAddress(String selectAddress) {
        MobileElement address = appium.findElementContainText(selectAddress);
        appium.click(address);
    }

    public void goBack() { appium.click(backButton); }

    public void checkout() { appium.click(placeOrder); }
}
