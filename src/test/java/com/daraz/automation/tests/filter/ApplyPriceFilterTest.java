package com.daraz.automation.tests.filter;

import com.daraz.automation.models.SearchData;
import com.daraz.automation.pages.HomePage;
import com.daraz.automation.pages.SearchResultsPage;
import com.daraz.automation.tests.base.BaseTest;
import com.daraz.automation.tests.base.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplyPriceFilterTest extends BaseTest {

    @Test(description = "TC-003", dataProvider = "commonDataProvider", dataProviderClass = TestDataProvider.class)

    public void verifyUserCanApplyPriceFilter(SearchData data) {

        // HOME PAGE
        HomePage homePage = new HomePage();
        homePage.searchProduct(data.getSearchKeyword());

        // SEARCH RESULTS PAGE
        SearchResultsPage resultsPage = new SearchResultsPage();

        // Apply price filter
        resultsPage.applyPriceFilter(data.getMinPrice(), data.getMaxPrice());

        // VALIDATION
        Assert.assertTrue(resultsPage.isPriceFilterApplied(data.getMinPrice(), data.getMaxPrice()),
                "Price filter was not applied successfully"
        );
    }
}