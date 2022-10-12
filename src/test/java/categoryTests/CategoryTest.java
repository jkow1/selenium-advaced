package categoryTests;

import base.Pages;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


@Slf4j
public class CategoryTest extends Pages {

    @Test
    public void categoryTestWithSuccess() {
        for (int i = 0; i < categoriesPage.getTopMenuCategories().size(); i++) {
            String expectedCategoryName = categoriesPage.clickOn(i);

            Assertions.assertThat(expectedCategoryName).isEqualTo(categoriesPage.getOpenedCategoryName());

            Assertions.assertThat(filtersSideMenuPage.sideMenuIsDisplayed()).isTrue();

            Assertions.assertThat(productsGridPage.getCountOfAllProducts()).isEqualTo(productsGridPage.getTotalDisplayedProducts());
        }
    }

    @Test
    public void subCategoryTestWithSuccess() {
        for (int i = 0; i < categoriesPage.getSizeOfTopMenuCategoryList(); i++) {
            categoriesPage.clickOn(i);

            for (int j = 0; j < categoriesPage.getCategoryAllSubcategories().size(); j++) {
                String expectedSubcategoryName = categoriesPage.clickOnSubcategory(j);
                Assertions.assertThat(expectedSubcategoryName).isEqualTo(categoriesPage.getOpenedCategoryName());

                Assertions.assertThat(filtersSideMenuPage.sideMenuIsDisplayed()).isTrue();

                Assertions.assertThat(productsGridPage.getCountOfAllProducts()).isEqualTo(productsGridPage.getTotalDisplayedProducts());

                categoriesPage.returnToPrevSite();
            }
        }
    }
}
