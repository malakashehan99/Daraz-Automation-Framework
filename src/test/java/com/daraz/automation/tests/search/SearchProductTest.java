package com.daraz.automation.tests.search;

import com.daraz.automation.models.SearchData;
import com.daraz.automation.pages.HomePage;
import com.daraz.automation.pages.SearchResultsPage;
import com.daraz.automation.tests.base.BaseTest;
import com.daraz.automation.tests.base.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {

    @Test(description = "TC-001", dataProvider = "commonDataProvider", dataProviderClass = TestDataProvider.class)

    public void verifyValidProductSearch(SearchData data) {

        // HOME PAGE
        HomePage homePage = new HomePage();
        homePage.searchProduct(data.getSearchKeyword());

        // SEARCH RESULTS PAGE
        SearchResultsPage searchResultsPage = new SearchResultsPage();

        // VALIDATION
        Assert.assertTrue(searchResultsPage.isSearchResultPageDisplayed(data.getSearchKeyword()),
                "Search results page is not displayed properly"
        );
    }
}