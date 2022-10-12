package searchTests;

import base.Pages;
import base.logback.BasicLogger;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class SearchTest extends Pages {

    @Test
    public void randomSearchTestWithSuccess() {
        String expectedProductName = productsGridPage.getRandomProduct().getProductName();

        searchPage.searchThis(expectedProductName)
                .clickOnSearchBtn();

        String actualProductTitle = productsGridPage.findProductWithName(expectedProductName).getProductName();

        BasicLogger.logAssertedValues(log, expectedProductName, actualProductTitle);
        Assertions.assertThat(actualProductTitle).isEqualTo(expectedProductName);
    }

    @Test
    public void searchDropdownTestWithSuccess() {
        searchPage.searchThis("HUMMINGBIRD");

        Assertions.assertThat(searchPage.getAllSearchResults()).contains("HUMMINGBIRD");

    }
}
