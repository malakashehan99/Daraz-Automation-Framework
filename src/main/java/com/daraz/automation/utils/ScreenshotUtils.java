package com.daraz.automation.utils;

import com.daraz.automation.factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {

        // Generate timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Screenshot file name
        String screenshotName = testName + "_" + timestamp + ".png";

        // Destination path
        String destinationPath = "screenshots/" + screenshotName;

        try {
            // Capture screenshot
            File source = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            // Save screenshot
            FileUtils.copyFile(source, new File(destinationPath));

        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot: " + e.getMessage());
        }

        return destinationPath;
    }
}