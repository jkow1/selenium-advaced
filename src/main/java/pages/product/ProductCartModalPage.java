package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

public class ProductCartModalPage extends BasePage {

    public ProductCartModalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#blockcart-modal")
    private WebElement cartModal;

    @FindBy(css = "#blockcart-modal .product-name")
    private WebElement cartModalProductName;

    @FindBy(css = "#blockcart-modal .product-quantity strong")
    private WebElement cartModalProductQuantity;

    @FindBy(css = "#blockcart-modal .cart-product-count")
    private WebElement cartModalProductCount;

    @FindBy(css = "#blockcart-modal .product-total .value")
    private WebElement cartModalTotalProductValue;

    @FindBy(css = "#blockcart-modal .btn-secondary")
    private WebElement cartModalContinueBtn;

    @FindBy(css = "#blockcart-modal a.btn-primary i")
    private WebElement cartModalProceedToCheckoutBtn;

    public ProductCartModalPage waitToModalBeVisible(){
            waitToBeVisible(cartModal);
            return this;
    }

    public ProductCartModalPage waitToModalBeNotVisible(){
        waitToBeInvisible(cartModal);
        return this;
    }

    public String getProductName(){
        return getWebElementText(cartModalProductName);
    }

    public String geProductQuantity(){
        return getWebElementText(cartModalProductQuantity);
    }

    public String geProductsCount(){
        return getWebElementText(cartModalProductCount);
    }
    public String geProductTotalValue(){
        return getWebElementText(cartModalTotalProductValue);
    }

    public ProductCartModalPage clickOnContinueShoppingBtn(){
        waitToModalBeVisible();
        clickOnBtn(cartModalContinueBtn);
        waitToModalBeNotVisible();
        return this;
    }

    public ProductCartModalPage clickOnProceedToCheckoutBtn(){
        waitToModalBeVisible();
        clickOnBtn(cartModalProceedToCheckoutBtn);
        waitToModalBeNotVisible();
        return this;
    }

}
