package steps;

import models.entities.OrderData;
import org.openqa.selenium.WebDriver;
import pages.order.OrderDetailsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderDetailsSteps extends OrderDetailsPage {

    public OrderDetailsSteps(WebDriver driver) {
        super(driver);
    }

    public OrderDetailsSteps checkIfOrderDetailsAreCorrect(OrderData orderData) {
        assertThat(this.getOrderDate()).isEqualTo(orderData.getOrderDate());
        assertThat(this.getOrderStatus()).isEqualTo(orderData.getOrderStatus());
        assertThat(this.getOrderTotalPrice()).isEqualTo(orderData.getPaymentAmount());
        assertThat(this.getOrderDeliveryAddress()).isEqualTo(orderData.getOrderDeliveryAddress());
        assertThat(this.getOrderInvoiceAddress()).isEqualTo(orderData.getOrderInvoiceAddress());
        return this;
    }


}
