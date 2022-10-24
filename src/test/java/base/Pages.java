package base;

import org.junit.jupiter.api.BeforeEach;
import pages.basket.CartPage;
import pages.checkout.CheckoutPage;
import pages.order.OrderConfirmationPage;
import pages.menu.CategoriesPage;
import pages.menu.FiltersSideMenuPage;
import pages.menu.TopHeaderPage;
import pages.order.OrderDetailsPage;
import pages.product.ProductCartModalPage;
import pages.product.ProductContainerPage;
import pages.product.ProductMiniaturePage;
import pages.product.ProductsGridPage;
import pages.search.SearchPage;
import pages.user.AccountPage;
import pages.user.CreateAccountPage;
import pages.order.OrderHistoryPage;
import pages.user.SignInPage;

public class Pages extends TestBase {

    public CategoriesPage categoriesPage;
    public FiltersSideMenuPage filtersSideMenuPage;
    public ProductMiniaturePage productMiniaturePage;
    public ProductsGridPage productsGridPage;
    public SearchPage searchPage;
    public ProductContainerPage productContainerPage;
    public ProductCartModalPage productCartModalPage;
    public TopHeaderPage topHeaderPage;
    public CartPage cartPage;
    public CreateAccountPage createAccountPage;
    public SignInPage signInPage;
    public CheckoutPage checkoutPage;
    public OrderConfirmationPage orderConfirmationPage;
    public AccountPage accountPage;
    public OrderHistoryPage orderHistoryPage;
    public OrderDetailsPage orderDetailsPage;



    @BeforeEach
    public void pagesSetUp() {
        categoriesPage = new CategoriesPage(driver);
        filtersSideMenuPage = new FiltersSideMenuPage(driver);
        productMiniaturePage = new ProductMiniaturePage(driver);
        productsGridPage = new ProductsGridPage(driver);
        searchPage = new SearchPage(driver);
        productContainerPage = new ProductContainerPage(driver);
        productCartModalPage = new ProductCartModalPage(driver);
        topHeaderPage = new TopHeaderPage(driver);
        cartPage = new CartPage(driver);
        createAccountPage = new CreateAccountPage(driver);
        signInPage = new SignInPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        accountPage = new AccountPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
    }
}
