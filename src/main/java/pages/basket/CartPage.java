package pages.basket;

import lombok.extern.slf4j.Slf4j;
import models.entities.Basket;
import models.entities.BasketLine;
import models.entities.ProductData;
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

    @FindBy(css = "#cart-subtotal-products .value")
    private WebElement totalPriceWithoutShipment;

    public CartPage waitToCartBeVisible() {
        waitToBeVisible(cartGrid);
        return this;
    }

    public double getTotalCartPrice() {
        return getPrice(totalPriceWithoutShipment);
    }

    public double getTotalPriceWithoutShipment() {
        return getFormattedDouble(getTotalCartPrice());
    }

    public CartPage removeFirstProduct() {
        log.info("Removing first element from cart");
        clickOnBtn(removeProductBtn);
        return this;
    }

    public Basket toBasket() {
        Basket listOfProducts = new Basket();
        waitToCartBeVisible();
        productsInCart.stream().map(element -> new CartProductPage(driver, element))
                .map(product -> new BasketLine(new ProductData(product.getProductName(), product.getSingleProductPrice()), product.getProductQuantity(), product.getProductTotalPrice()))
                .forEach(listOfProducts::addProduct);
        log.info("Cart contains {} product/s of total value {}", listOfProducts.getProducts().toString(), listOfProducts.getBasketTotalPrice());
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
