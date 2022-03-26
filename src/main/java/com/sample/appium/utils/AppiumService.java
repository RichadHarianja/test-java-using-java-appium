package com.sample.appium.utils;

import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumService {
    protected static AppiumDriver driver;
    protected static String platformName;

    private static final String CAPABILITIES_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "suites";

    public AppiumService(String capabilitiesFile, String appiumUrl, String platform) throws Exception {
        DesiredCapabilities desiredCapabilities = TestUtils.parseStringJSON(CAPABILITIES_DIR + File.separator + capabilitiesFile);
        platformName = platform;
        URL url = new URL(appiumUrl);
        switch(platformName) {
            case "iOS":
                driver = new IOSDriver(url, desiredCapabilities);
                break;
            default:
                throw new Exception("Invalid platform! - " + platformName);
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        int a = 0;
    }

    public AppiumDriver getDriver() { return driver; }
    public String getPlatformName() { return platformName; }

    public void setDriver(AppiumDriver driver2) { driver = driver2; }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(MobileElement e) {
        if (getDriver().getKeyboard() != null)
            getDriver().hideKeyboard();
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(MobileElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void closeApp() {
        ((InteractsWithApps) getDriver()).closeApp();
    }

    public void launchApp() {
        ((InteractsWithApps) getDriver()).launchApp();
    }

    public void stopDriver() {
        ((InteractsWithApps) getDriver()).closeApp();
        getDriver().quit();
    }

    public void closePopup() {
        String id="";
        switch(platformName) {
            case "iOS":
                id = "Ok";
                break;
        }
        MobileElement okBtn = findElement(MobileBy.id(id));
        click(okBtn);
    }

    public Boolean checkPopupWithTitleExists(String title) {
        switch(platformName) {
            case "iOS":
                MobileElement popup =  findElementContainText(title);
                if (popup != null)
                    return true;
                break;
        }
        return false;
    }

    public MobileElement findElement(By by) {
        return (MobileElement) driver.findElement(by);
    }

    public void hideIOSKeyboard() {
        if(platformName.equals("iOS")) {
            MobileElement element = findElement(MobileBy.id("Return"));
            if (element != null)
                element.click();
        }
    }

    public MobileElement findElementContainText(String text) {
        String by="";
        WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
        if (platformName.equals("iOS"))
            by =String.format("(//*[contains(@label, '%s')])[last()]",  text);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(MobileBy.xpath(by), text));
        return findElement(MobileBy.xpath(by));
    }
}
