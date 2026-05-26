package com.daraz.automation.tests.filter;

import com.daraz.automation.models.SearchData;
import com.daraz.automation.pages.HomePage;
import com.daraz.automation.pages.SearchResultsPage;
import com.daraz.automation.tests.base.BaseTest;
import com.daraz.automation.tests.base.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortProductsTest extends BaseTest {

    @Test(description = "TC-004", dataProvider = "commonDataProvider", dataProviderClass = TestDataProvider.class)

    public void verifyUserCanSortProductsByPriceLowToHigh(SearchData data) {

        // HOME PAGE
        HomePage homePage = new HomePage();
        homePage.searchProduct(data.getSearchKeyword());

        // SEARCH RESULTS PAG
        SearchResultsPage resultsPage = new SearchResultsPage();

        // APPLY SORTING
        resultsPage.sortByPriceLowToHigh();

        // VALIDATION
        Assert.assertTrue(resultsPage.isSortingApplied(), "Sorting by price low to high was not applied"
        );
    }
}