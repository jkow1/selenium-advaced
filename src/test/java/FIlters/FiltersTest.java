package FIlters;

import base.TestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.menu.CategoriesPage;
import pages.menu.FiltersSideMenuPage;
import pages.products.ProductPage;
import pages.products.ProductsGridPage;

public class FiltersTest extends TestBase {

    @Test
    public void filtersTestWithSuccess() {
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        FiltersSideMenuPage filtersSideMenuPage = new FiltersSideMenuPage(driver);
        ProductsGridPage productsGridPage = new ProductsGridPage(driver);
        ProductPage productPage = new ProductPage(driver);

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
