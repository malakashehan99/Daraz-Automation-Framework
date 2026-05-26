package com.daraz.automation.tests.paginations;

import com.daraz.automation.models.SearchData;
import com.daraz.automation.pages.HomePage;
import com.daraz.automation.pages.SearchResultsPage;
import com.daraz.automation.tests.base.BaseTest;
import com.daraz.automation.tests.base.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PaginationTest extends BaseTest {

    @Test(description = "TC-008", dataProvider = "commonDataProvider", dataProviderClass = TestDataProvider.class)

    public void verifyUserCanNavigateToNextPage(SearchData data) {

        // HOME PAGE
        HomePage homePage = new HomePage();
        homePage.searchProduct(data.getSearchKeyword());

        // SEARCH RESULTS PAGE
        SearchResultsPage resultsPage = new SearchResultsPage();

        // GET CURRENT PAGE
        int beforePage = resultsPage.getCurrentPageNumber();

        // GO TO NEXT PAGE
        resultsPage.goToNextPage();

        // GET NEW PAGE
        int afterPage = resultsPage.getCurrentPageNumber();

        // VALIDATION
        Assert.assertEquals(afterPage, beforePage + 1, "Pagination did not navigate to next page"
        );
    }
}