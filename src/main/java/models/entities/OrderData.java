package models.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderData {
    private String orderDate;
    private String orderStatus;
    private String paymentAmount;
    private String orderDeliveryAddress;
    private String orderInvoiceAddress;
    private String sippingPrice;
    private String payableTo;
    private String mailTo;
    private String paymentMethod;
    private String shippingMethod;


    public OrderData(String orderDate, String orderStatus, String paymentAmount, String orderDeliveryAddress, String orderInvoiceAddress, String sippingPrice, String payableTo, String mailTo, String paymentMethod, String shippingMethod) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.paymentAmount = paymentAmount;
        this.orderDeliveryAddress = orderDeliveryAddress;
        this.orderInvoiceAddress = orderInvoiceAddress;
        this.sippingPrice = sippingPrice;
        this.payableTo = payableTo;
        this.mailTo = mailTo;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
    }
}
