package com.daraz.automation.base;

import com.daraz.automation.factory.DriverFactory;
import com.daraz.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    // COMMON ACTION METHODS
    protected void click(By locator) {
        WaitUtils.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    // COMMON VALIDATION METHODS
    protected String getText(By locator) {
        return WaitUtils.waitForVisibility(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        return WaitUtils.waitForVisibility(locator).isDisplayed();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    // SCROLL METHODS
    protected void scrollBy(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
    }

    // STABILIZATION METHODS
    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}