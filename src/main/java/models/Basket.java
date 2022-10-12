package models;

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
    private List<ProductLine> listOfProductsInBasket;

    public Basket() {
        listOfProductsInBasket = new ArrayList<>();
    }

    public Basket(List<ProductLine> listOfProductsInBasket) {
        this.listOfProductsInBasket = listOfProductsInBasket;
    }

    public void addNewProduct(ProductLine productLine) {
        listOfProductsInBasket.add(productLine);
    }

    public boolean checkIfListContainsProduct(String productName) {
        return listOfProductsInBasket.stream()
                .anyMatch(productLine -> productLine.getProductData().getProductName().equals(productName));
    }

    public ProductLine getProductFromList(String productName) {
        return listOfProductsInBasket.stream()
                .filter(productLine -> productLine.getProductData().getProductName().equals(productName))
                .findFirst()
                .get();
    }

    public int getSizeOfListOfProducts() {
        return listOfProductsInBasket.size();
    }

    public String getNameOfProductFromBasket(int i) {
        return listOfProductsInBasket.get(i).getProductData().getProductName();
    }

    public int getQuantityOfProductFromBasket(int i) {
        return listOfProductsInBasket.get(i).getQuantity();
    }

    public void removeFirstElement() {
        if (getSizeOfListOfProducts() > 0) {
            log.info("Removing first element from shopping list of quantity value {}", listOfProductsInBasket.get(0).getQuantityPrice());
            listOfProductsInBasket.remove(0);
            log.info("There is {} products left of total value {}", getSizeOfListOfProducts(), getBasketValue());
        }
    }

    public double getBasketValue() {
        double basketValue = 0.0;
        for (ProductLine product : listOfProductsInBasket) {
            basketValue += product.getQuantity() * product.getProductData().getPrice();
        }
        return DoubleRounder.round(basketValue, 2);
    }
}
