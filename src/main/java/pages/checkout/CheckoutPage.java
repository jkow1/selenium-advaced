package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#checkout-addresses-step")
    private WebElement checkoutAddressStepSection;

    @FindBy(css = "[name='address1']")
    private WebElement addressInput;

    @FindBy(css = "[name='city']")
    private WebElement cityInput;

    @FindBy(css = "[name='id_state']")
    private WebElement stateSelect;

    @FindBy(css = "[name='postcode']")
    private WebElement postCodeInput;

    @FindBy(css = "[name='id_country']")
    private WebElement countrySelect;

    @FindBy(css = "#checkout-addresses-step .continue")
    private WebElement continueBtn;

    @FindBy(css = "[data-link-action='different-invoice-address']")
    private WebElement addBillingAddressBtn;

    @FindBy(css = "#invoice-address")
    private WebElement invoiceAddressSection;

    @FindBy(css = "#checkout-delivery-step .continue")
    private WebElement shippingMethodContinueBtn;

    @FindBy(css = "#checkout-payment-step")
    private WebElement paymentSection;

    @FindBy(css = "#payment-option-1")
    private WebElement payByCheckCheckBox;

    @FindBy(css = ".custom-checkbox input")
    private WebElement agreeToTermsOfServicesCheckBox;

    @FindBy(css = "#payment-confirmation button")
    private WebElement placeOrderBtn;

    @FindBy(css = "#delivery-addresses article")
    private List<WebElement> listOfAddresses;

    @FindBy(css = "#delivery-addresses article .delete-address")
    private List<WebElement> deleteAddressBtns;


    public CheckoutPage checkIfThereIsAnyDeliveryAddress() {
        if (listOfAddresses.size() > 1) {
            clickOnBtn(deleteAddressBtns.get(1));
        }
        return this;
    }

    public CheckoutPage clickOnPlaceOrderBtn() {
        clickOnBtn(placeOrderBtn);
        return this;
    }

    public CheckoutPage agreeToTermsOfServices() {
        clickOnBtn(agreeToTermsOfServicesCheckBox);
        return this;
    }

    public CheckoutPage choosePayByCheck() {
        clickOnBtn(payByCheckCheckBox);
        return this;
    }

    public CheckoutPage waitToAddressSectionBeVisible() {
        waitToBeVisible(checkoutAddressStepSection);
        return this;
    }

    public CheckoutPage typeAddress(String address) {
        sendKeysWithClear(addressInput, address);
        return this;
    }

    public CheckoutPage typeCity(String City) {
        sendKeysWithClear(cityInput, City);
        return this;
    }

    public CheckoutPage typePostalCode(String postalCode) {
        sendKeysWithClear(postCodeInput, postalCode);
        return this;
    }

    public CheckoutPage chooseState(String state) {
        sendKeys(stateSelect, state);
        return this;
    }

    public CheckoutPage chooseCountry(String country) {
        sendKeys(countrySelect, country);
        return this;
    }

    public CheckoutPage clickOnContinueBtn() {
        clickOnBtn(continueBtn);
        return this;
    }

    public CheckoutPage clickOnChangeBillingAddressBtn() {
        clickOnBtn(addBillingAddressBtn);
        return this;
    }

    public CheckoutPage waitToInvoiceAddressSectionBeVisible() {
        waitToBeVisible(invoiceAddressSection);
        return this;
    }

    public CheckoutPage waitToShippingMethodContinueBtnIsClickable() {
        waitToBtnIsClickable(shippingMethodContinueBtn);
        return this;
    }

    public CheckoutPage waitToAddBillingAddressBtnBeVisible() {
        waitToBeVisible(addBillingAddressBtn);
        return this;
    }

    public CheckoutPage clickOnShippingMethodContinueBtn() {
        clickOnBtn(shippingMethodContinueBtn);
        return this;
    }


}
