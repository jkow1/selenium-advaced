package filters;

import base.Pages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.product.ProductMiniaturePage;

public class FiltersTest extends Pages {

    @Test
    public void filtersTestWithSuccess() {
        categoriesPage.clickOnCategory("ART");

        filtersSideMenuPage.moveLeftSlider(8);
        filtersSideMenuPage.moveRightSlider(10);

        for (ProductMiniaturePage product : productsGridPage.getAllProducts()) {
            Assertions.assertThat(product.getProductPrice() >= 8.0 && product.getProductPrice() <= 10.0);
        }

        filtersSideMenuPage.clearAllFilters();

        Assertions.assertThat(filtersSideMenuPage.clearFiltersBtnIsPresent()).isEqualTo(false);
    }
}
