package steps;

import dataProvider.UrlProvider;
import lombok.extern.slf4j.Slf4j;
import models.entities.Basket;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.basket.CartPage;

@Slf4j
public class BasketSteps extends CartPage {

    public BasketSteps(WebDriver driver, Basket expectedBasket) {
        super(driver);
        this.expectedBasket = expectedBasket;
    }

    private Basket expectedBasket;

    public BasketSteps goToBasket() {
        driver.get(UrlProvider.basketUrl);
        waitToCartBeVisible();
        return this;
    }

    public BasketSteps checkIfBasketContainsCorrectProducts() {
        Assertions.assertThat(this.toBasket()).usingRecursiveComparison().isEqualTo(expectedBasket);
        return this;
    }

    public BasketSteps checkIfCartTotalPriceIsCorrect() {
        Assertions.assertThat(getTotalPriceWithoutShipment()).isEqualTo(expectedBasket.getBasketTotalPrice());
        return this;
    }

    public BasketSteps checkIfCartIsCorrectAfterEachProductRemove() {
        do {
            this.removeFirstProduct()
                    .waitForRemoveProduct();
            expectedBasket.removeFirstElement();
            checkIfBasketContainsCorrectProducts();
            checkIfCartTotalPriceIsCorrect();
        } while (expectedBasket.getSizeOfListOfProducts() > 0);
        return this;
    }

    public BasketSteps checkIfBasketIsEmpty() {
        Assertions.assertThat(this.emptyCartTextIsVisible()).isEqualTo("There are no more items in your cart");
        return this;
    }

}
