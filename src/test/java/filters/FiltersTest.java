package filters;

import base.Pages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.products.ProductPage;

public class FiltersTest extends Pages {

    @Test
    public void filtersTestWithSuccess() {
        categoriesPage.clickOnCategory("ART");

        filtersSideMenuPage.moveLeftSlider(8);
        filtersSideMenuPage.moveRightSlider(10);

        for (ProductPage product : productsGridPage.getAllProducts()) {
            Assertions.assertThat(product.getProductIntPrice() >= 8 && product.getProductIntPrice() <= 10);
        }

        filtersSideMenuPage.clearAllFilters();

        Assertions.assertThat(filtersSideMenuPage.clearFiltersBtnIsPresent()).isEqualTo(false);
    }
}
