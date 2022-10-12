package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

public class OrderDetailsPage extends BasePage {

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-history td")
    private WebElement orderDate;

    @FindBy(css = "#order-history td span")
    private WebElement orderStatus;

    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address address")
    private WebElement invoiceAddress;

    @FindBy(css = "#order-products .line-total td")
    private List<WebElement> totalPrice;

    public String getOrderDate() {
        return getWebElementText(orderDate);
    }

    public String getOrderStatus() {
        return getWebElementText(orderStatus);
    }

    public String getOrderDeliveryAddress() {
        return getWebElementText(deliveryAddress);
    }

    public String getOrderInvoiceAddress() {
        return getWebElementText(invoiceAddress);
    }

    public String getOrderTotalPrice() {
        return getWebElementText(totalPrice.get(1));
    }

}
