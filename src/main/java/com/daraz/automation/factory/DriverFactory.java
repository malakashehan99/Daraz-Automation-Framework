package com.daraz.automation.factory;

import com.daraz.automation.utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class DriverFactory {

    // THREAD LOCAL DRIVER
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // INITIALIZE DRIVER
    public static void initDriver() {

        // Read browser from config.properties
        String browser = ConfigReader.getProperty("browser");
        WebDriver driver;

        // BROWSER SELECTION
        switch (browser.toLowerCase()) {

            case "edge":
                driver = new EdgeDriver();
                break;

            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        // Store driver in ThreadLocal
        driverThreadLocal.set(driver);

        // BROWSER CONFIGURATIONS
        // Maximize browser window
        getDriver().manage().window().maximize();

        // Implicit wait
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(
                                ConfigReader.getProperty("implicit.wait"))));

        // Page load timeout
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(
                                ConfigReader.getProperty("page.load.timeout"))));
    }

    // GET DRIVER
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // QUIT DRIVER
    public static void quitDriver() {

        if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
        }
    }
}