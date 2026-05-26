package com.daraz.automation.pages;

import com.daraz.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage extends BasePage {

    // LOCATORS
    private final By productTitle = By.cssSelector("h1");

    private final By productPrice = By.cssSelector("span.pdp-price_type_normal");

    private final By mainProductImage = By.cssSelector("img.pdp-mod-common-image.gallery-preview-panel__image");

    private final By thumbnailImages = By.cssSelector("img.item-gallery__thumbnail-image");

    // VALIDATION METHODS
    public boolean isProductTitleDisplayed() {
        return isDisplayed(productTitle);
    }

    public boolean isProductPriceDisplayed() {
        return isDisplayed(productPrice);
    }

    public String getProductTitle() {
        return getText(productTitle);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public boolean areThumbnailImagesPresent() {
        return driver.findElements(thumbnailImages)
                .size() > 1;
    }


    // IMAGE GALLERY METHODS
    public String getMainImageSource() {
        return driver.findElement(mainProductImage)
                .getAttribute("src");
    }

    public void clickThumbnailByIndex(int index) {
        List<WebElement> thumbnails = driver.findElements(thumbnailImages);
        thumbnails.get(index).click();
    }
}