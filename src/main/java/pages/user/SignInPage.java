package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".login-form")
    private WebElement signInForm;

    @FindBy(css = ".login-form [name='email']")
    private WebElement signInEmailInput;

    @FindBy(css = ".login-form [name='password']")
    private WebElement signInPasswordInput;

    @FindBy(css = "#submit-login")
    private WebElement submitSignInBtn;

    @FindBy(css = "[data-action='show-password']")
    private WebElement showPasswordBtn;

    public SignInPage typeUserEmail(String email) {
        signInEmailInput.sendKeys(email);
        return this;
    }

    public SignInPage typeInUserPassword(String password) {
        signInPasswordInput.sendKeys(password);
        return this;
    }

    public void clickOnSignInBtn() {
        clickOnBtn(submitSignInBtn);
    }

    public SignInPage waitToSignInFormBeVisible() {
        waitToBeVisible(signInForm);
        return this;
    }

}
