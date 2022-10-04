package searchTests;

import base.TestBase;
import logback.BasicLogger;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.products.ProductPage;
import pages.products.ProductsGridPage;
import pages.search.SearchPage;

@Slf4j
public class SearchTest extends TestBase {

    @Test
    public void randomSearchTestWithSuccess() {
        SearchPage searchPage = new SearchPage(driver);
        ProductsGridPage productsGridPage = new ProductsGridPage(driver);
        ProductPage productPage = productsGridPage.getRandomProduct();

        String expectedProductName = productPage.getProductName();

        searchPage.searchThis(expectedProductName)
                .clickOnSearchBtn();

        String actualProductTitle = productsGridPage.findProductWithName(expectedProductName).getProductName();

        BasicLogger.logAssertedValues(log, expectedProductName, actualProductTitle);
        Assertions.assertThat(actualProductTitle).isEqualTo(expectedProductName);
    }

    @Test
    public void searchDropdownTestWithSuccess() {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.searchThis("HUMMINGBIRD");

        Assertions.assertThat(searchPage.checkIfSearchingTextIsInDropdown()).isEqualTo(true);

    }
}
