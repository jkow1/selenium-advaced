package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductsGridPage extends BasePage {

    public ProductsGridPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product")
    private List<WebElement> productsInGrid;

    @FindBy(css = ".total-products")
    private WebElement totalDisplayedProducts;

    @FindBy(css = "#js-active-search-filters")
    private WebElement activeFiltersBar;

    public ProductPage getRandomProduct() {
        return new ProductPage(driver, getRandomElementFromList(productsInGrid));
    }

    public ProductPage findProductWithName(String name) {
        waitToBeVisible(productsInGrid.stream().findFirst().get());
        return new ProductPage(driver, productsInGrid.stream()
                .filter(product -> new ProductPage(driver, product).getProductName().equals(name))
                .findFirst()
                .get());
    }

    public int getCountOfAllProducts() {
        int allProductsQuantity = productsInGrid.size();
        log.info("There are {} products in grid", allProductsQuantity);
        return allProductsQuantity;
    }

    public int getTotalDisplayedProducts() {
        return Integer.parseInt(getWebElementText(totalDisplayedProducts).replaceAll("\\D+", ""));
    }

    public List<ProductPage> getAllProducts() {
        List<ProductPage> list = new ArrayList<>();
        productsInGrid.forEach(product -> list.add(new ProductPage(driver, product)));
        return list;
    }

    public boolean isThereAnyActiveFilters() {
        return elementIsVisible(activeFiltersBar);
    }

}
