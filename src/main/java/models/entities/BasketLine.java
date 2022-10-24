package models.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketLine {
    private ProductData productData;
    private int quantity;
    private double totalPrice;

    public BasketLine(ProductData productData, int quantity, double totalPrice) {
        this.productData = productData;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public BasketLine addToQuantity(int x) {
        quantity = quantity + x;
        totalPrice = quantity * productData.getPrice();
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "productName=" + productData.getName() +
                ", productPrice=" + productData.getPrice() +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
