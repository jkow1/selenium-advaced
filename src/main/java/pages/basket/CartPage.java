package pages.basket;

import lombok.extern.slf4j.Slf4j;
import models.Basket;
import models.ProductData;
import models.ProductLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

@Slf4j
public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-grid")
    private WebElement cartGrid;

    @FindBy(css = ".cart-items li")
    private List<WebElement> productsInCart;

    @FindBy(css = ".cart-total .value")
    private WebElement totalPriceOfCart;

    @FindBy(css = "#cart-subtotal-shipping span.value")
    private WebElement shippingCartPrice;

    @FindBy(css = ".remove-from-cart i")
    private WebElement removeProductBtn;

    @FindBy(css = ".no-items")
    private WebElement emptyCartInfoText;

    @FindBy(css = ".btn-primary")
    private WebElement proceedToCheckoutBtn;

    public CartPage waitToCartBeVisible() {
        waitToBeVisible(cartGrid);
        return this;
    }

    public double getTotalCartPrice() {
        return getPriceFromWebElementText(totalPriceOfCart);
    }

    public double getShippingPrice() {
        return getPriceFromWebElementText(shippingCartPrice);
    }

    public double getTotalPriceOfProducts() {
        return getFormattedDouble(getTotalCartPrice() - getShippingPrice());
    }

    public CartPage removeProduct() {
        log.info("Removing first element from cart");
        clickOnBtn(removeProductBtn);
        return this;
    }

    public Basket getProductsInCart() {
        Basket listOfProducts = new Basket();
        waitToCartBeVisible();
        for (WebElement element : productsInCart) {
            CartProductPage product = new CartProductPage(driver, element);
            String productName = product.getProductName();
            double singlePrice = product.getSingleProductPrice();
            int quantity = product.getProductQuantity();
            double quantityPrice = product.getProductTotalPrice();
            listOfProducts.addNewProduct(new ProductLine(new ProductData(productName, singlePrice), quantity, quantityPrice));
            log.info("Adding {} items of {} with  single price {} and quantity price {} to list ", quantity, productName, singlePrice, quantityPrice);
        }
        log.info("Cart contains {} of products of total value {}", listOfProducts.getSizeOfListOfProducts(), listOfProducts.getBasketValue());
        return listOfProducts;
    }

    public void waitForRemoveProduct() {
        waitToBeInvisible(productsInCart.get(0));
    }

    public String emptyCartTextIsVisible() {
        waitToBeVisible(emptyCartInfoText);
        return getWebElementText(emptyCartInfoText);
    }

    public void clickOnProceedToCheckoutBtn() {
        clickOnBtn(proceedToCheckoutBtn);
    }

}
