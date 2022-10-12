package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductLine {
    private ProductData productData;
    private int quantity;

    private double quantityPrice;

    public ProductLine(ProductData productData, int quantity, double quantityPrice) {
        this.productData = productData;
        this.quantity = quantity;
        this.quantityPrice = quantityPrice;
    }

    public ProductLine addToQuantity(int x) {
        quantity = quantity + x;
        return this;
    }

    public void setProductTotalPrice() {
        quantityPrice = quantity * productData.getPrice();
    }

    @Override
    public String toString() {
        return "ProductLine{" +
                "productName=" + productData.getProductName() +
                ", productPrice=" + productData.getPrice() +
                ", quantity=" + quantity +
                ", quantityPrice=" + quantityPrice +
                '}';
    }
}
