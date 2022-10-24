package models.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class Basket {
    private List<BasketLine> products;
    private double basketTotalPrice;

    public Basket() {
        products = new ArrayList<>();
    }

    public void addProduct(BasketLine basketLine) {
        if (checkIfListContainsProduct(basketLine.getProductData().getName())) {
            getProductFromList(basketLine.getProductData().getName()).addToQuantity(basketLine.getQuantity());
        } else {
            products.add(basketLine);
        }
        basketTotalPrice = DoubleRounder.round((basketLine.getQuantity() * basketLine.getProductData().getPrice()) + basketTotalPrice, 2);
    }

    public boolean checkIfListContainsProduct(String productName) {
        return products.stream()
                .anyMatch(basketLine -> basketLine.getProductData().getName().equals(productName));
    }

    public BasketLine getProductFromList(String productName) {
        return products.stream()
                .filter(basketLine -> basketLine.getProductData().getName().equals(productName))
                .findFirst()
                .get();
    }

    public int getSizeOfListOfProducts() {
        return products.size();
    }

    public String getNameOfProductFromBasket(int i) {
        return products.get(i).getProductData().getName();
    }

    public int getQuantityOfProductFromBasket(int i) {
        return products.get(i).getQuantity();
    }

    public void removeFirstElement() {
        if (getSizeOfListOfProducts() > 0) {
            log.info("Removing first element from shopping list of quantity value {}", products.get(0).getTotalPrice());
            basketTotalPrice = DoubleRounder.round(basketTotalPrice - products.get(0).getTotalPrice(), 2);
            products.remove(0);
            log.info("There is {} product left of total value {}", getSizeOfListOfProducts(), basketTotalPrice);
        }
    }

    @Override
    public String toString() {
        return "Basket{" +
                "products=" + products.toString() +
                ", basketTotalPrice=" + basketTotalPrice +
                '}';
    }
}
