package com.daraz.automation.pages;

import com.daraz.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage extends BasePage {

    // LOCATORS
    private final By firstProduct = By.cssSelector("div[data-qa-locator='product-item']");

    private final By minPriceInput = By.xpath("//input[@placeholder='Min']");

    private final By maxPriceInput = By.xpath("//input[@placeholder='Max']");

    private final By applyFilterButton = By.xpath("//div[contains(@class,'gJ98q')]//button[contains(@class,'ant-btn-icon-only')]");

    private final By sortDropdown = By.cssSelector("div.ant-select.ant-select-lg");

    private final By lowToHighOption = By.xpath("//div[contains(text(),'Price low to high')]");

    private final By nextPageButton = By.cssSelector("li.ant-pagination-next");

    private final By activePageNumber = By.cssSelector("li.ant-pagination-item-active");

    private final By paginationContainer = By.cssSelector("ul.ant-pagination");

    private final By noResultsMessage = By.xpath("//*[contains(text(),'Search No Result')]");


    // VALIDATION METHODS
    public boolean isSearchResultPageDisplayed(String keyword) {
        return getPageTitle().toLowerCase().contains(keyword.toLowerCase());
    }

    public boolean isPriceFilterApplied(String minPrice, String maxPrice) {
        String currentUrl = getCurrentUrl();
        return currentUrl.contains(minPrice) && currentUrl.contains(maxPrice);
    }

    public boolean isSortingApplied() {
        return getCurrentUrl().contains("priceasc");
    }

    public boolean isNoResultsMessageDisplayed() {
        return driver.findElements(noResultsMessage).size() > 0;
    }

    public String getNoResultsMessage() {
        return getText(noResultsMessage);
    }

    // ACTION METHODS
    public void openFirstProduct() {
        click(firstProduct);
    }

    public void applyPriceFilter(String minPrice, String maxPrice) {

        scrollBy(0, 600);
        type(minPriceInput, minPrice);
        type(maxPriceInput, maxPrice);
        click(applyFilterButton);
        sleep(3000);
    }


    // SORTING METHODS
    public void sortByPriceLowToHigh() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Open dropdown
        WebElement dropdown = (WebElement) js.executeScript(
                        "return document.querySelector('div.ant-select.ant-select-lg').shadowRoot || " +
                                "document.querySelector('div.ant-select.ant-select-lg');");
        dropdown.click();

        // Select option
        WebElement lowToHigh = wait.until(ExpectedConditions.elementToBeClickable(lowToHighOption));
        lowToHigh.click();
        sleep(2000);
    }

    // PAGINATION METHODS
    public void goToNextPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait pagination visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(paginationContainer));

        // Remove overlays
        hideObstructingElements();
        int beforePage = getCurrentPageNumber();
        WebElement nextBtn = driver.findElement(nextPageButton);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);

        // Remove overlays again
        hideObstructingElements();

        // JS click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);

        // Wait page number update
        wait.until(ExpectedConditions.not(
                        ExpectedConditions.textToBe(
                                activePageNumber,
                                String.valueOf(beforePage)
                        )
                )
        );
    }

    public int getCurrentPageNumber() {
        String currentPage = getText(activePageNumber);
        return Integer.parseInt(currentPage);
    }

    // OVERLAY HANDLING METHODS
    private void hideObstructingElements() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Hide top banner
        try {
            WebElement banner = driver.findElement(By.id("topActionLiveUpBanner"));
            js.executeScript("arguments[0].style.display='none';", banner);

        } catch (Exception ignored) {}
        // Close chat popup

        try {WebElement chat = driver.findElement(By.cssSelector("button.im-app__cont-minimize"));
            chat.click();
        } catch (Exception ignored) {}

        // Hide side message popup
        try {WebElement msg = driver.findElement(By.cssSelector("div.message-view"));
            js.executeScript("arguments[0].style.display='none';", msg);

        } catch (Exception ignored) {}

        // Remove overlays
        try {js.executeScript(
                    "document.querySelectorAll('*').forEach(el => {" +
                            " if (window.getComputedStyle(el).zIndex > 1000) {" +
                            "     el.style.display='none';" +
                            " }" +
                            "});"
            );
        } catch (Exception ignored) {}
    }
}