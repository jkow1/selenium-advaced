package steps;

import models.entities.OrderData;
import org.openqa.selenium.WebDriver;
import pages.order.OrderConfirmationPage;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderConfirmationSteps extends OrderConfirmationPage {

    public OrderConfirmationSteps(WebDriver driver) {
        super(driver);
    }

    public OrderConfirmationPage checkIfOrderConfirmationIsCorrect(String productName, String productPrice, String productQuantity, OrderData orderData) {
        assertThat(this.getOrderedProductName()).contains(productName);
        assertThat(this.getOrderedProductPrice()).contains(productPrice);
        assertThat(this.getOrderedProductQuantity()).contains(productQuantity);
        assertThat(this.getOrderedProductQuantityPrice()).contains(productPrice);
        assertThat(this.getOrderShippingPrice()).contains(orderData.getSippingPrice());
        assertThat(this.getOrderPaymentMethod()).contains(orderData.getPaymentMethod());
        assertThat(this.getOrderShippingMethod()).contains(orderData.getShippingMethod());
        assertThat(this.getOrderPaymentAmount()).contains(orderData.getPaymentAmount());
        assertThat(this.getOrderPaymentPayableTo()).contains(orderData.getPayableTo());
        assertThat(this.getOrderPaymentMailTo()).contains(orderData.getMailTo());
        assertThat(this.getOrderPaymentReferenceNumber()).contains(this.getOrderReferenceNumber());
        return this;
    }
}
