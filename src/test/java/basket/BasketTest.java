package basket;

import base.Pages;
import lombok.extern.slf4j.Slf4j;
import models.entities.Basket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.BasketSteps;

@Slf4j
public class BasketTest extends Pages {

    @Test
    @DisplayName("Generic test of basket - successful adding and removing product")
    public void shouldSuccessfulModifyBasket() {
        Basket expectedBasket = addProductsToBasket(5, 5);
        BasketSteps basketSteps = new BasketSteps(driver, expectedBasket);

        basketSteps.goToBasket()
                .checkIfBasketContainsCorrectProducts()
                .checkIfCartTotalPriceIsCorrect()
                .checkIfCartIsCorrectAfterEachProductRemove()
                .checkIfBasketIsEmpty();
    }

    public Basket addProductsToBasket(int quantityOfProducts, int maxQuantityPerProduct) {
        Basket expectedBasket = new Basket();
        for(int i=0;i<quantityOfProducts;i++) {
            productsGridPage.getRandomProduct().clickOnProduct();
            productContainerPage.increaseQuantity(productContainerPage.getRandomQuantity(1, maxQuantityPerProduct));
            expectedBasket.addProduct(productContainerPage.toBasketLine());
            productContainerPage.addToCart();
            productCartModalPage.clickOnContinueShoppingBtn();
            topHeaderPage.goToMainPage();
        }
        log.info("Expected basket contains: {}",expectedBasket.toString());
        return expectedBasket;
    }
}
