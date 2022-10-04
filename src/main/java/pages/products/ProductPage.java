package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

@Slf4j
public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = ".product-title")
    private WebElement productName;

    @FindBy(css = ".price")
    private WebElement productPrice;

    public String getProductName() {
        return getWebElementText(productName);
    }

    public String getProductPrice() {
        return getWebElementText(productPrice);
    }

    public int getProductIntPrice() {
        return Integer.parseInt(getProductPrice().replaceAll("[\\D+00]", ""));
    }
}
