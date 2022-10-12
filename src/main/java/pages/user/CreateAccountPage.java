package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

public class CreateAccountPage extends BasePage {

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".register-form")
    private WebElement userRegisterForm;

    @FindBy(css = "[name='firstname']")
    private WebElement userFirstName;

    @FindBy(css = "[name='lastname']")
    private WebElement userLastName;

    @FindBy(css = "[name='email']")
    private WebElement userEmail;

    @FindBy(css = "[name='password']")
    private WebElement userPassword;

    @FindBy(css = "[name='customer_privacy']")
    private WebElement customerPersonalDataPrivacyCheckBox;

    @FindBy(css = "[name='psgdpr']")
    private WebElement acceptTermsOfUseAndPrivacyPolicyCheckBox;

    @FindBy(css = "[data-link-action='save-customer']")
    private WebElement createAccountBtn;

    public CreateAccountPage typeFirstName(String firstName) {
        sendKeysWithClear(userFirstName, firstName);
        return this;
    }

    public CreateAccountPage typeLastName(String lastName) {
        sendKeysWithClear(userLastName, lastName);
        return this;
    }

    public CreateAccountPage typeEmail(String email) {
        sendKeysWithClear(userEmail, email);
        return this;
    }

    public CreateAccountPage typePassword(String password) {
        sendKeysWithClear(userPassword, password);
        return this;
    }

    public CreateAccountPage acceptPersonalDataPrivacy() {
        clickOnBtn(customerPersonalDataPrivacyCheckBox);
        return this;
    }

    public CreateAccountPage acceptTermsOfUseAndPrivacyPolicy() {
        clickOnBtn(acceptTermsOfUseAndPrivacyPolicyCheckBox);
        return this;
    }

    public CreateAccountPage waitToCreateAccountFormBeVisible() {
        waitToBeVisible(userRegisterForm);
        return this;
    }

    public CreateAccountPage getOnCreateAccountPage() {
        driver.get("http://146.59.32.4/index.php?controller=authentication&create_account=1");
        return this;
    }

    public CreateAccountPage clickOnSaveBtn() {
        clickOnBtn(createAccountBtn);
        return this;
    }
}
