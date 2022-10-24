package basket;

import base.Pages;
import dataProvider.UserFactory;
import lombok.extern.slf4j.Slf4j;
import models.entities.OrderData;
import models.entities.User;
import org.junit.jupiter.api.Test;
import steps.CheckoutSteps;
import steps.UserSteps;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CheckoutTest extends Pages {

    @Test
    public void checkoutOrderWithSuccessTest() {
        User user = new UserFactory().getAlreadyExistingUser();
        String productName = "THE BEST IS YET POSTER";
        String productPrice = "$29.00";
        String productQuantity = "1";

        CheckoutSteps checkoutSteps = new CheckoutSteps(driver,
                setOrderData(user)
        );

        new UserSteps(driver).logInUser(user);

        orderProduct(user);

        checkoutSteps.setOrderNumber(orderConfirmationPage.getOrderReferenceNumber());

        checkoutSteps.checkIfOrderConfirmationIsCorrect(productName, productPrice, productQuantity)
                .goToOrderDetails()
                .checkOrderDetailsInOrderHistory();
    }

    private OrderData setOrderData(User user) {
        return new OrderData(new SimpleDateFormat("MM/dd/yyyy").format(new Date()),
                "Awaiting check payment",
                "$36.00",
                setDeliveryAddress(),
                setInvoiceAddress(user),
                "$7.00",
                "John Doe",
                "Washington",
                "Payments by check",
                "My carrier"
        );
    }

    private String setDeliveryAddress() {
        return "Jan Nowak\n" +
                "Adres 1\n" +
                "City1, Idaho 11111\n" +
                "United States";
    }

    private String setInvoiceAddress(User user) {
        return user.getFirstName() +
                " " + user.getLastName() +
                "\n" + user.getAddress() +
                "\n" + user.getCity() +
                ", " + user.getState() +
                " " + user.getPostalCode() +
                "\n" + user.getCountry();
    }

    private void orderProduct(User user) {
        topHeaderPage
                .goToMainPage();

        productsGridPage
                .waitToProductsBeVisible()
                .findProductWithName("THE BEST IS YET POSTER")
                .clickOnProduct();

        productContainerPage
                .addToCart();

        productCartModalPage
                .waitToModalBeVisible()
                .clickOnProceedToCheckoutBtn();

        cartPage
                .waitToCartBeVisible()
                .clickOnProceedToCheckoutBtn();

        checkoutPage
                .waitToAddressSectionBeVisible()
                .checkIfThereIsAnyDeliveryAddress()
                .waitToAddBillingAddressBtnBeVisible()
                .clickOnChangeBillingAddressBtn()
                .waitToInvoiceAddressSectionBeVisible()
                .chooseCountry(user.getCountry())
                .typeAddress(user.getAddress())
                .typeCity(user.getCity())
                .typePostalCode(user.getPostalCode())
                .chooseState(user.getState())
                .clickOnContinueBtn()
                .waitToShippingMethodContinueBtnIsClickable()
                .clickOnShippingMethodContinueBtn()
                .choosePayByCheck()
                .agreeToTermsOfServices()
                .clickOnPlaceOrderBtn();

        orderConfirmationPage
                .waitToOrderConfirmationSiteBeVisible();
    }
}
