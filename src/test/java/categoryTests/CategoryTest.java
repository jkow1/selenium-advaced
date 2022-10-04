package categoryTests;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.menu.CategoriesPage;
import pages.menu.FiltersSideMenuPage;
import pages.products.ProductsGridPage;

@Slf4j
public class CategoryTest extends TestBase {

    @Test
    public void categoryTestWithSuccess() {
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        FiltersSideMenuPage filtersSideMenuPage = new FiltersSideMenuPage(driver);
        ProductsGridPage productsGridPage = new ProductsGridPage(driver);


        for (int i = 0; i < categoriesPage.getTopMenuCategories().size(); i++) {
            String expectedCategoryName = categoriesPage.clickOn(i);

            Assertions.assertThat(expectedCategoryName).isEqualTo(categoriesPage.getOpenedCategoryName());

            Assertions.assertThat(filtersSideMenuPage.sideMenuIsDisplayed()).isNotEqualTo(false);

            Assertions.assertThat(productsGridPage.getCountOfAllProducts()).isEqualTo(productsGridPage.getTotalDisplayedProducts());
        }
    }

    @Test
    public void subCategoryTestWithSuccess() {
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        FiltersSideMenuPage filtersSideMenuPage = new FiltersSideMenuPage(driver);
        ProductsGridPage productsGridPage = new ProductsGridPage(driver);


        for (int i = 0; i < categoriesPage.getTopMenuCategories().size(); i++) {
            categoriesPage.clickOn(i);

            for (int j = 0; j < categoriesPage.getCategoryAllSubcategories().size(); j++) {
                String expectedSubcategoryName = categoriesPage.clickOnSubcategory(j);
                Assertions.assertThat(expectedSubcategoryName).isEqualTo(categoriesPage.getOpenedCategoryName());

                Assertions.assertThat(filtersSideMenuPage.sideMenuIsDisplayed()).isNotEqualTo(false);

                Assertions.assertThat(productsGridPage.getCountOfAllProducts()).isEqualTo(productsGridPage.getTotalDisplayedProducts());

                categoriesPage.returnToPrevSite();
            }
        }
    }
}
