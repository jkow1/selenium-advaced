package models;

import lombok.Data;

@Data
public class ProductData {

    private String productName;
    private double price;

    public ProductData(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

}
