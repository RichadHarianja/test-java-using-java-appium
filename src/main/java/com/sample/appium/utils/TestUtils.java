package com.sample.appium.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TestUtils {
    public static final long WAIT = 30;
    private static final String RESOURCE_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
            File.separator + "resources";

    public static DesiredCapabilities parseStringJSON(String filePath) throws Exception{
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        InputStream capabilitiesStream = null;
        try {
            capabilitiesStream = new FileInputStream(filePath);
            JSONTokener capabilitiesToken = new JSONTokener(capabilitiesStream);
            JSONObject capabilities = new JSONObject(capabilitiesToken);
            JSONArray keys = capabilities.names();
            for (int i = 0; i < keys.length (); ++i) {
                String key = keys.getString(i);
                String value = capabilities.getString (key);
                if (key.equals("app"))
                    value = RESOURCE_DIR + File.separator + value;
                desiredCapabilities.setCapability(key, value);
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(capabilitiesStream != null) {
                capabilitiesStream.close();
            }
        }
        return desiredCapabilities;
    }

    public String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
