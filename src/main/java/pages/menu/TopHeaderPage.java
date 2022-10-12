package pages.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

public class TopHeaderPage extends BasePage {
    public TopHeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".header .cart-products-count")
    private WebElement cartHeaderProductCount;

    @FindBy(css = "#_desktop_logo img")
    private WebElement mainPageBtn;

    @FindBy(css = "#_desktop_user_info .user-info")
    private WebElement signInBtn;

    @FindBy(css = "#_desktop_user_info .logout")
    private WebElement signOutBtn;

    @FindBy(css = ".user-info .account")
    private WebElement userAccountBtn;

    public int getProductCount(){
        return Integer.parseInt(getWebElementText(cartHeaderProductCount).replaceAll("\\D+",""));
    }

    public void goToMainPage(){
        waitToBeVisible(mainPageBtn);
        clickOnBtn(mainPageBtn);
    }

    public void clickOnUserAccountBtn(){
        clickOnBtn(userAccountBtn);
    }

    public TopHeaderPage waitToUserAccountBtnBeVisible(){
        waitToBeVisible(userAccountBtn);
        return this;
    }

    public TopHeaderPage clickOnOnSignInBtn(){
        clickOnBtn(signInBtn);
        return this;
    }

}
