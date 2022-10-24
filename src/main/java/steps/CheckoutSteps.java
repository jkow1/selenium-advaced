package steps;

import lombok.Getter;
import lombok.Setter;
import models.entities.OrderData;
import org.openqa.selenium.WebDriver;
import pages.checkout.CheckoutPage;
import pages.menu.TopHeaderPage;

@Getter
@Setter
public class CheckoutSteps extends CheckoutPage {

    public CheckoutSteps(WebDriver driver, OrderData orderData) {
        super(driver);
        this.orderData = orderData;
    }

    private String orderNumber;
    private OrderData orderData;

    public CheckoutSteps goToOrderDetails() {
        new TopHeaderPage(driver).clickOnUserAccountBtn()
                .goToOrderHistoryPage()
                .clickOnOrderDetail(orderNumber);
        return this;
    }

    public CheckoutSteps checkOrderDetailsInOrderHistory() {
        new OrderDetailsSteps(driver).checkIfOrderDetailsAreCorrect(orderData);
        return this;
    }

    public CheckoutSteps checkIfOrderConfirmationIsCorrect(String productName, String productPrice, String productQuantity) {
        new OrderConfirmationSteps(driver).checkIfOrderConfirmationIsCorrect(productName, productPrice, productQuantity, orderData);
        return this;
    }
}
