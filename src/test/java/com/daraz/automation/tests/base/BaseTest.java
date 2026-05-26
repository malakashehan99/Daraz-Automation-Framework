package com.daraz.automation.tests.base;

import com.aventstack.extentreports.Status;
import com.daraz.automation.factory.DriverFactory;
import com.daraz.automation.reports.ExtentManager;
import com.daraz.automation.reports.ExtentTestManager;
import com.daraz.automation.utils.ConfigReader;
import com.daraz.automation.utils.ScreenshotUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup(ITestResult result) {

        // Initialize browser
        DriverFactory.initDriver();

        // Launch application
        DriverFactory.getDriver().get(ConfigReader.getProperty("base.url"));

        // Create Extent Report test entry
        ExtentTestManager.setTest(ExtentManager.getExtentReports().createTest(getClass().getSimpleName()));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        // TEST PASSED
        if (result.getStatus() == ITestResult.SUCCESS) {

            ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
        }

        // TEST FAILED
        else if (result.getStatus() == ITestResult.FAILURE) {

            // Capture screenshot
            String screenshotPath = ScreenshotUtils.captureScreenshot(result.getName());

            // Log failure
            ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());

            // Attach screenshot
            try {
                ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // TEST SKIPPED
        else if (result.getStatus() == ITestResult.SKIP) {

            ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
        }

        // Flush report
        ExtentManager.getExtentReports().flush();

        // Remove thread local test
        ExtentTestManager.unload();

        // Close browser
        DriverFactory.quitDriver();
    }
}