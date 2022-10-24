package basket;

import base.Pages;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class ShoppingCartTest extends Pages {

    @Test
    public void addToCartTestWithSuccess() {
        String expectedProductName = "THE BEST IS YET POSTER";
        String expectedProductsCountInformation = "There are 3 items in your cart.";
        int expectedProductQuantity = 3;
        String expectedTotalValue = "$94.00";

        categoriesPage.clickOnCategory("ART");

        productsGridPage.findProductWithName(expectedProductName)
                .clickOnProduct();

        productContainerPage.increaseQuantity(expectedProductQuantity)
                .addToCart();

        productCartModalPage.waitToModalBeVisible();

        log.info("Asserting product name");
        Assertions.assertThat(productCartModalPage.getProductName()).isEqualTo(expectedProductName);
        log.info("Asserting product quantity");
        Assertions.assertThat(Integer.parseInt(productCartModalPage.geProductQuantity())).isEqualTo(expectedProductQuantity);
        log.info("Asserting added product quantity");
        Assertions.assertThat(productCartModalPage.geProductsCount()).isEqualTo(expectedProductsCountInformation);
        log.info("Asserting product total value");
        Assertions.assertThat(productCartModalPage.geProductTotalValue()).isEqualTo(expectedTotalValue);

        productCartModalPage.clickOnContinueShoppingBtn()
                .waitToModalBeNotVisible();

        log.info("Asserting all product quantity in cart");
        Assertions.assertThat(topHeaderPage.getProductCount()).isEqualTo(expectedProductQuantity);
    }
}
