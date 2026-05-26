package com.daraz.automation.tests.product;

import com.daraz.automation.models.SearchData;
import com.daraz.automation.pages.HomePage;
import com.daraz.automation.pages.ProductPage;
import com.daraz.automation.pages.SearchResultsPage;
import com.daraz.automation.tests.base.BaseTest;
import com.daraz.automation.tests.base.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageGalleryTest extends BaseTest {

    @Test(description = "TC-007", dataProvider = "commonDataProvider", dataProviderClass = TestDataProvider.class)

    public void verifyThumbnailImageChangesMainImage(SearchData data) {

        // HOME PAGE
        HomePage homePage = new HomePage();
        homePage.searchProduct(data.getSearchKeyword());

        // SEARCH RESULTS PAGE
        SearchResultsPage resultsPage = new SearchResultsPage();

        // Open first product
        resultsPage.openFirstProduct();

        // PRODUCT PAGE
        ProductPage productPage = new ProductPage();

        // VALIDATE THUMBNAILS EXIST
        Assert.assertTrue(productPage.areThumbnailImagesPresent(), "Thumbnail images are not displayed");

        // GET MAIN IMAGE BEFORE CLICK
        String beforeImage = productPage.getMainImageSource();

        // CLICK SECOND THUMBNAIL
        productPage.clickThumbnailByIndex(1);

        // GET MAIN IMAGE AFTER CLICK
        String afterImage = productPage.getMainImageSource();

        // VALIDATE IMAGE CHANGED
        Assert.assertNotEquals(beforeImage, afterImage, "Main image did not change after thumbnail click");
    }
}