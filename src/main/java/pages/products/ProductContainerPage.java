package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

@Slf4j
public class ProductContainerPage extends BasePage {

    public ProductContainerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".qty button.bootstrap-touchspin-up")
    private WebElement increaseQuantityBtn;

    @FindBy(css = ".add button.add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(css = ".h1")
    private WebElement productName;

    @FindBy(css = ".current-price")
    private WebElement productPrice;

    @FindBy(css = "#quantity_wanted")
    private WebElement productQuantity;


    public ProductContainerPage increaseQuantity(int x) {
        for (int i = 1; i < x; i++) {
            clickOnBtn(increaseQuantityBtn);
        }
        return new ProductContainerPage(driver);
    }

    public void addToCart() {
        clickOnBtn(addToCartBtn);
    }

    public String getProductName() {
        return getWebElementText(productName);
    }

    public double getProductPrice() {
        return getPriceFromWebElementText(productPrice);
    }

    public int getProductQuantity() {
        return Integer.parseInt(getWebElementValue(productQuantity));
    }

}
