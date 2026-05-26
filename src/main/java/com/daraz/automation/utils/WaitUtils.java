package com.daraz.automation.utils;

import com.daraz.automation.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait getWait() {

        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Integer.parseInt(
                ConfigReader.getProperty("explicit.wait"))));
    }

    public static WebElement waitForVisibility(By locator) {

        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {

        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }
}