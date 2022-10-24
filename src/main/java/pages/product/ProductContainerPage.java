package pages.product;

import lombok.extern.slf4j.Slf4j;
import models.entities.BasketLine;
import models.entities.ProductData;
import org.decimal4j.util.DoubleRounder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.Random;

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

    @FindBy(css = ".current-price [itemprop='price']")
    private WebElement productPrice;

    @FindBy(css = "#quantity_wanted")
    private WebElement productQuantity;


    public ProductContainerPage increaseQuantity(int x) {
        waitToBeVisible(productQuantity);
        sendKeysWithClear(productQuantity, String.valueOf(x));
        return new ProductContainerPage(driver);
    }

    public int getRandomQuantity(int minRange, int maxRange){
        return new Random(System.currentTimeMillis()).nextInt(minRange,maxRange);
    }


    public void addToCart() {
        clickOnBtn(addToCartBtn);
    }

    public String getProductName() {
        return getWebElementText(productName);
    }

    public double getProductPrice() {
        return getPrice(productPrice);
    }

    public int getProductQuantity() {
        return Integer.parseInt(getValue(productQuantity));
    }

    public BasketLine toBasketLine() {
        return new BasketLine(new ProductData(getProductName(),getProductPrice()),getProductQuantity(), DoubleRounder.round(getProductPrice()*getProductQuantity(),2));
    }
}
