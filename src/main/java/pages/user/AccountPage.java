package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;
import pages.order.OrderHistoryPage;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#history-link span")
    private WebElement orderHistoryBtn;

    public OrderHistoryPage goToOrderHistoryPage() {
        waitToBeVisible(orderHistoryBtn);
        clickOnBtn(orderHistoryBtn);
        return new OrderHistoryPage(driver);
    }


}
