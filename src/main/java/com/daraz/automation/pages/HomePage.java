package com.daraz.automation.pages;

import com.daraz.automation.base.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    // LOCATORS
    private final By searchBox = By.name("q");

    // ACTION METHODS
    public void searchProduct(String productName) {
        type(searchBox, productName + "\n");
    }

}