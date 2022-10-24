package pages.product;

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
    private WebElement productsGrid;

    @FindBy(css = ".product")
    private List<WebElement> productsInGrid;

    @FindBy(css = ".total-product")
    private WebElement totalDisplayedProducts;

    @FindBy(css = "#js-active-search-filters")
    private WebElement activeFiltersBar;

    public ProductMiniaturePage getRandomProduct() {
        return new ProductMiniaturePage(driver, getRandomElementFromList(productsInGrid));
    }

    public ProductMiniaturePage findProductWithName(String name) {
        waitToBeVisible(productsInGrid.stream().findFirst().get());
        return new ProductMiniaturePage(driver, productsInGrid.stream()
                .filter(product -> new ProductMiniaturePage(driver, product).getProductName().equals(name))
                .findFirst()
                .get());
    }

    public int getCountOfAllProducts() {
        int allProductsQuantity = productsInGrid.size();
        log.info("There are {} product in grid", allProductsQuantity);
        return allProductsQuantity;
    }

    public int getTotalDisplayedProducts() {
        return Integer.parseInt(getWebElementText(totalDisplayedProducts).replaceAll("\\D+", ""));
    }

    public List<ProductMiniaturePage> getAllProducts() {
        List<ProductMiniaturePage> list = new ArrayList<>();
        productsInGrid.forEach(product -> list.add(new ProductMiniaturePage(driver, product)));
        return list;
    }

    public boolean isThereAnyActiveFilters() {
        return elementIsVisible(activeFiltersBar);
    }

    public ProductsGridPage waitToProductsBeVisible() {
        waitToBeVisible(productsGrid);
        return this;
    }

}
