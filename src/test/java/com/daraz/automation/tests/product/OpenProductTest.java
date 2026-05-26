package com.daraz.automation.tests.product;

import com.daraz.automation.models.SearchData;
import com.daraz.automation.pages.HomePage;
import com.daraz.automation.pages.ProductPage;
import com.daraz.automation.pages.SearchResultsPage;
import com.daraz.automation.tests.base.BaseTest;
import com.daraz.automation.tests.base.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenProductTest extends BaseTest {

    @Test(description = "TC-005", dataProvider = "commonDataProvider", dataProviderClass = TestDataProvider.class)

    public void verifyUserCanOpenFirstProduct(SearchData data) {

        // HOME PAGE
        HomePage homePage = new HomePage();
        homePage.searchProduct(data.getSearchKeyword());

        // SEARCH RESULTS PAGE
        SearchResultsPage resultsPage = new SearchResultsPage();

        // Open first product
        resultsPage.openFirstProduct();

        // PRODUCT PAGE
        ProductPage productPage = new ProductPage();

        // VALIDATION
        Assert.assertTrue(productPage.isProductTitleDisplayed(), "Product title is not displayed"
        );
    }
}