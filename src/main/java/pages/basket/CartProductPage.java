package pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

public class CartProductPage extends BasePage {

    public CartProductPage(WebDriver driver) {
        super(driver);
    }

    public CartProductPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = "li .label")
    private WebElement productName;

    @FindBy(css = ".current-price .price")
    private WebElement currentProductPrice;

    @FindBy(css = ".product-line-grid-right strong")
    private WebElement totalProductPrice;

    @FindBy(css = "[name='product-quantity-spin']")
    private WebElement productQuantity;

    public String getProductName() {
        return getWebElementText(productName);
    }

    public double getSingleProductPrice() {
        return getPriceFromWebElementText(currentProductPrice);
    }

    public double getProductTotalPrice() {
        return getPriceFromWebElementText(totalProductPrice);
    }

    public int getProductQuantity() {
        return Integer.parseInt(getWebElementValue(productQuantity));
    }
}
