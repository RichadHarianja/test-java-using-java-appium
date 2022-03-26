package com.sample.appium.test;

import com.sample.appium.utils.AppiumService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class AbstractTest {
    protected AppiumService service;

    @BeforeClass(alwaysRun = true, description = "Start the Appium Driver")
    @Parameters({"os", "appium-url", "ios-capabilities"})
    public void startApplication(String os, String appiumUrl, @Optional String iOSCapabilitiesFile) throws Exception {
        if (os.equals("ios"))
            service = new AppiumService(iOSCapabilitiesFile, appiumUrl, "iOS");
        testSetup();
    }

    @AfterClass(description = "Stop the Application", alwaysRun = true)
    public void stopApplication() {
        service.stopDriver();
    }

    public abstract void testSetup();
}
