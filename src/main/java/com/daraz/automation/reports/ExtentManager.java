package com.daraz.automation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extentReports;

    public static ExtentReports getExtentReports() {

        // Create report only once

        if (extentReports == null) {

            // Report file location
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/DarazAutomationReport.html");

            // Report title
            sparkReporter.config().setReportName("Daraz Automation Test Report");

            // Document title
            sparkReporter.config().setDocumentTitle("Automation Execution Report");

            // Initialize ExtentReports
            extentReports = new ExtentReports();

            // Attach reporter
            extentReports.attachReporter(sparkReporter);

            // Additional system information

            extentReports.setSystemInfo("Project", "Daraz Automation Framework");

            extentReports.setSystemInfo("Tester", "Malaka Shehan");

            extentReports.setSystemInfo("Environment", "QA");
        }

        return extentReports;
    }
}