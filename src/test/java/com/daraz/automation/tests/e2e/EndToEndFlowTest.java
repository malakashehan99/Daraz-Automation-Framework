package com.daraz.automation.tests.e2e;

import com.daraz.automation.models.SearchData;
import com.daraz.automation.pages.HomePage;
import com.daraz.automation.pages.ProductPage;
import com.daraz.automation.pages.SearchResultsPage;
import com.daraz.automation.tests.base.BaseTest;
import com.daraz.automation.tests.base.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndFlowTest extends BaseTest {

    @Test(description = "TC-009", dataProvider = "commonDataProvider", dataProviderClass = TestDataProvider.class)

    public void verifyCompleteShoppingFlow(SearchData data) {

        // HOME PAGE
        HomePage homePage = new HomePage();
        homePage.searchProduct(data.getSearchKeyword());

        // SEARCH RESULTS PAGE
        SearchResultsPage resultsPage = new SearchResultsPage();

        // Validate search results page
        Assert.assertTrue(resultsPage.isSearchResultPageDisplayed(data.getSearchKeyword()),
                "Search results page is not displayed"
        );

        // APPLY PRICE FILTER
        resultsPage.applyPriceFilter(data.getMinPrice(), data.getMaxPrice());
        Assert.assertTrue(resultsPage.isPriceFilterApplied(data.getMinPrice(), data.getMaxPrice()),
                "Price filter was not applied"
        );

        // SORT PRODUCTS
        resultsPage.sortByPriceLowToHigh();
        Assert.assertTrue(resultsPage.isSortingApplied(),
                "Sorting was not applied"
        );

        // OPEN FIRST PRODUCT
        resultsPage.openFirstProduct();

        // PRODUCT PAGE
        ProductPage productPage = new ProductPage();

        // VALIDATE PRODUCT TITLE
        Assert.assertTrue(productPage.isProductTitleDisplayed(),
                "Product title is not displayed"
        );

        Assert.assertFalse(productPage.getProductTitle().isEmpty(),
                "Product title is empty"
        );

        // VALIDATE PRODUCT PRICE
        Assert.assertTrue(productPage.isProductPriceDisplayed(),
                "Product price is not displayed"
        );

        Assert.assertFalse(productPage.getProductPrice().isEmpty(),
                "Product price is empty"
        );

        // VALIDATE THUMBNAILS
        Assert.assertTrue(productPage.areThumbnailImagesPresent(),
                "Thumbnail images are not displayed"
        );

        // VALIDATE IMAGE SWITCHING
        String beforeImage = productPage.getMainImageSource();
        productPage.clickThumbnailByIndex(1);
        String afterImage = productPage.getMainImageSource();

        Assert.assertNotEquals(beforeImage, afterImage,
                "Main product image did not change"
        );
    }
}