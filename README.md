# Daraz Automation Framework

Hybrid Selenium Java Automation Framework using TestNG, Page Object Model (POM), JSON Data-Driven Testing, Extent Reports, Cross Browser Support and Parallel Execution.

---

# Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- Extent Reports
- Jackson Databind
- Git & GitHub

---

# Framework Features

## Page Object Model (POM)

Separate page classes for better maintainability and reusable UI actions.

## Data-Driven Testing

Test data managed using external JSON files with TestNG DataProviders.

## Parallel Execution

Supports parallel test execution using TestNG XML configuration and ThreadLocal WebDriver.

## Cross Browser Support

Framework supports:
- Chrome
- Edge

Browser selection handled through `config.properties`.

## Screenshot Capture

Automatically captures screenshots for failed test cases.

## Extent Reports

Generates detailed HTML automation execution reports.

---

# Framework Architecture

## pages

Contains Page Object classes.

Example:
- HomePage
- SearchResultsPage
- ProductPage

Responsible for:
- locators
- page actions
- validations

---

## tests

Contains all automation test classes grouped by functionality.

Example:
- search tests
- product tests
- filter tests
- e2e tests
- pagination test

---

## utils

Reusable utility classes.

Example:
- ConfigReader
- WaitUtils
- ScreenshotUtils
- JsonReader

---

## reports

Extent Report management classes.

- ExtentManager
- ExtentTestManager

---

## models

Data models for JSON mapping.

- SearchData
- BaseDataModel

---

## factory

Driver initialization and browser management.

- Driver Factory

Handles:
- ThreadLocal WebDriver
- browser setup
- driver cleanup

---

# Project Structure

```text
src
 ├── main
 │    ├── java
 │    │     └── com.daraz.automation
 │    │            ├── base
 │    │            ├── factory
 │    │            ├── models
 │    │            ├── pages
 │    │            ├── reports
 │    │            └── utils
 │    │
 │    └── resources
 │          ├── config.properties
 │          └── testdata
 │
 └── test
      └── java
            └── com.daraz.automation.tests
                   ├── base
                   ├── e2e
                   ├── filter
                   ├── paginations
                   ├── product
                   └── search
```

---

# Test Scenarios Covered

- Product Search
- Invalid Search Validation
- Price Filter Validation
- Product Sorting
- Product Opening
- Product Details Validation
- Product Image Gallery Validation
- Pagination Validation
- End-To-End Shopping Flow

---

# How To Run

## Run Using TestNG XML

Run:

```text
testng.xml
```

from IntelliJ IDEA.

---

## Run Using Maven

```bash
mvn test
```

---

# Parallel Execution

Framework supports parallel execution using:

```xml
parallel="tests"
thread-count="2"
```

inside `testng.xml`.

---

# Cross Browser Configuration

Update browser inside:

```text
src/main/resources/config.properties
```

Example:

```properties
browser=chrome
```

or

```properties
browser=edge
```

---

# Reporting

After execution:

Extent HTML Report generated inside:

```text
/reports/
```

---

# Sample Report

<img width="1919" height="1031" alt="Screenshot_298" src="https://github.com/user-attachments/assets/63c7922c-08d4-4f0d-a2f8-b9112c1545cf" />
