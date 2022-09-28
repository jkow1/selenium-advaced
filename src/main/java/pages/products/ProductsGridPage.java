package pages.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeProductsPage {

    public HomeProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "#homefeatured .product-container")
    private List<WebElement> homePageProducts;

    public List<ProductPage> getAllProducts() {
        List<ProductPage> allProducts = new ArrayList<>();
        homePageProducts.forEach(product -> allProducts.add(new ProductPage(product)));
        return homePageProducts.stream()
                .map(ProductPage::new)
                .collect(Collectors.toList());
    }


}
