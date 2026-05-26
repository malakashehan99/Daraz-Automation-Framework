package com.daraz.automation.tests.search;

import com.daraz.automation.models.SearchData;
import com.daraz.automation.pages.HomePage;
import com.daraz.automation.pages.SearchResultsPage;
import com.daraz.automation.tests.base.BaseTest;
import com.daraz.automation.tests.base.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidSearchTest extends BaseTest {

    @Test(description = "TC-002", dataProvider = "commonDataProvider", dataProviderClass = TestDataProvider.class)

    public void verifyInvalidSearchShowsNoResults(SearchData data) {

        // HOME PAGE
        HomePage homePage = new HomePage();
        homePage.searchProduct(data.getSearchKeyword());

        // SEARCH RESULTS PAGE
        SearchResultsPage resultsPage = new SearchResultsPage();

        // VALIDATION
        Assert.assertTrue(resultsPage.isNoResultsMessageDisplayed(),
                "No results message is not displayed"
        );
    }
}