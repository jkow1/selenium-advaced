package pages.order;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

@Slf4j
public class OrderConfirmationPage extends BasePage {

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-confirmation")
    private WebElement orderConfirmationPage;

    @FindBy(css = ".details span")
    private WebElement productName;

    @FindBy(css = ".col-sm-6 .col-xs-4")
    private List<WebElement> productDetails;

    @FindBy(css = "tr td")
    private List<WebElement> shippingAndTotalPrices;

    @FindBy(css = "#order-details li")
    private List<WebElement> orderDetails;

    @FindBy(css = "#content-hook_payment_return li")
    private List<WebElement> paymentsDetails;

    public String getOrderPaymentAmount() {
        return getWebElementText(paymentsDetails.get(0));
    }

    public String getOrderPaymentPayableTo() {
        return getWebElementText(paymentsDetails.get(1));
    }

    public String getOrderPaymentMailTo() {
        return getWebElementText(paymentsDetails.get(2));
    }

    public String getOrderPaymentReferenceNumber() {
        return getWebElementText(paymentsDetails.get(3));
    }

    public String getOrderedProductName() {
        return getWebElementText(productName);
    }

    public String getOrderedProductPrice() {
        return getWebElementText(productDetails.get(0));
    }

    public String getOrderedProductQuantity() {
        return getWebElementText(productDetails.get(1));
    }

    public String getOrderedProductQuantityPrice() {
        return getWebElementText(productDetails.get(2));
    }

    public String getOrderShippingPrice() {
        return getWebElementText(shippingAndTotalPrices.get(3));
    }

    public String getOrderPaymentMethod() {
        return getWebElementText(orderDetails.get(1));
    }

    public String getOrderShippingMethod() {
        return getWebElementText(orderDetails.get(2));
    }

    public String getOrderReferenceNumber() {
        String referenceNumber = getWebElementText(orderDetails.get(0)).replaceAll("Order reference: ", "");
        log.info("Reference number is {}", referenceNumber);
        return referenceNumber;
    }

    public OrderConfirmationPage waitToOrderConfirmationSiteBeVisible() {
        waitToBeVisible(orderConfirmationPage);
        return this;
    }

}
