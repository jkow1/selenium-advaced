package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody tr th")
    private List<WebElement> listOfReferenceNumbers;

    @FindBy(css = "tbody td [data-link-action='view-order-details']")
    private List<WebElement> listOfDetailsBtn;

    public void clickOnOrderDetail(String referenceNumber) {
        for (int i = 0; i < listOfReferenceNumbers.size(); i++) {
            if (getWebElementText(listOfReferenceNumbers.get(i)).equals(referenceNumber)) {
                clickOnBtn(listOfDetailsBtn.get(i));
                break;
            }
        }
    }

}
