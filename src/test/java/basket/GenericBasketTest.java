package basket;

import base.Pages;
import lombok.extern.slf4j.Slf4j;
import models.Basket;
import models.ProductData;
import models.ProductLine;
import org.decimal4j.util.DoubleRounder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GenericBasketTest extends Pages {

    @Test
    //@RepeatedTest(10)
    @DisplayName("Generic test of basket - successful adding and removing products")
    public void checkingBasketManagementWithSuccess() {

        Basket expectedOrder = createListOfRandomProducts(5, 5);
        Basket actualOrder = cartPage.getProductsInCart();

        assertThat(actualOrder.getListOfProductsInBasket()).usingRecursiveComparison().isEqualTo(expectedOrder.getListOfProductsInBasket());
        assertThat(cartPage.getTotalPriceOfProducts()).isEqualTo(expectedOrder.getBasketValue());

        removeProduct(expectedOrder);

        while (expectedOrder.getSizeOfListOfProducts() > 0) {
            actualOrder = cartPage.getProductsInCart();

            assertThat(actualOrder.getListOfProductsInBasket()).usingRecursiveComparison().isEqualTo(expectedOrder.getListOfProductsInBasket());
            assertThat(cartPage.getTotalPriceOfProducts()).isEqualTo(expectedOrder.getBasketValue());

            removeProduct(expectedOrder);
        }

        assertThat(cartPage.emptyCartTextIsVisible()).isEqualTo("There are no more items in your cart");
    }

    private void addProductsFromListToCart(Basket basket) {
        for (int i = 0; i < basket.getSizeOfListOfProducts(); i++) {
            productsGridPage.waitToProductsBeVisible()
                    .findProductWithName(basket.getNameOfProductFromBasket(i))
                    .clickOnProduct();
            productContainerPage.increaseQuantity(basket.getQuantityOfProductFromBasket(i))
                    .addToCart();
            productCartModalPage.waitToModalBeVisible();
            productCartModalPage.clickOnContinueShoppingBtn()
                    .waitToModalBeNotVisible();
            topHeaderPage.goToMainPage();
        }
        driver.get("http://146.59.32.4/index.php?controller=cart&action=show");
    }

    public int getRandomQuantity(int maxRange) {
        return new Random(System.currentTimeMillis()).nextInt(1, maxRange);
    }


    public Basket createListOfRandomProducts(int repetitionOfAddProduct, int maxRangeOfProductQuantity) {
        Basket listOfProducts = new Basket();
        for (int i = 0; i < repetitionOfAddProduct; i++) {
            productPage = productsGridPage.waitToProductsBeVisible()
                    .getRandomProduct();
            String productName = productPage.getProductName();
            int productQuantity = getRandomQuantity(maxRangeOfProductQuantity);
            if (listOfProducts.checkIfListContainsProduct(productName)) {
                listOfProducts.getProductFromList(productName).addToQuantity(productQuantity).setProductTotalPrice();
                log.info("Adding {} more of {} to list", productQuantity, productName);
            } else {
                double productPrice = productPage.getProductDoublePrice();
                double quantityPrice = DoubleRounder.round(productQuantity * productPrice, 2);
                listOfProducts.addNewProduct(new ProductLine(new ProductData(productName, productPrice), productQuantity, quantityPrice));
                log.info("Adding {} items of {} with  single price {} and quantity price {} to list ", productQuantity, productName, productPrice, quantityPrice);
            }
        }
        log.info("Generated shopping list of {} product of total value {}", listOfProducts.getSizeOfListOfProducts(), listOfProducts.getBasketValue());
        addProductsFromListToCart(listOfProducts);
        return listOfProducts;
    }

    public void removeProduct(Basket expectedOrder) {
        cartPage.removeProduct()
                .waitForRemoveProduct();
        expectedOrder.removeFirstElement();
    }
}
