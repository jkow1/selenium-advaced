package pages.product;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

@Slf4j
public class ProductMiniaturePage extends BasePage {
    public ProductMiniaturePage(WebDriver driver) {
        super(driver);
    }

    public ProductMiniaturePage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = ".product-title")
    private WebElement productName;

    @FindBy(css = ".price")
    private WebElement productPrice;

    public String getProductName() {
        return getWebElementText(productName);
    }

    public double getProductPrice() {
        return getPrice(productPrice);
    }

    public void clickOnProduct() {
        clickOnBtn(productName);
    }
}
