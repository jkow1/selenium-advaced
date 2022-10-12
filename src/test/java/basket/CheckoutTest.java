package basket;

import base.Pages;
import dataProvider.UserFactory;
import lombok.extern.slf4j.Slf4j;
import models.User;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CheckoutTest extends Pages {

    @Test
    public void checkoutOrderWithSuccessTest() {
        User user = new UserFactory().getAlreadyExistingUser();
        String orderReferenceNumber;
        String productName = "THE BEST IS YET POSTER";
        String productPrice = "$29.00";
        String productQuantity = "1";
        String productQuantityPrice = "$29.00";
        String shippingPrice = "$7.00";
        String paymentMethod = "Payments by check";
        String shippingMethod = "My carrier";
        String paymentAmount = "$36.00";
        String payableTo = "John Doe";
        String mailTo = "Washington";
        String orderStatus = "Awaiting check payment";

        logInUser(user);

        orderProduct(user);

        orderReferenceNumber = orderConfirmationPage.getOrderReferenceNumber();

        checkOrderConfirmationCorrectness(productName, productPrice, productQuantity, productQuantityPrice, shippingPrice, paymentMethod, shippingMethod, paymentAmount, payableTo, mailTo);

        checkOrderHistory(user, orderReferenceNumber, paymentAmount, orderStatus);


    }

    private void checkOrderHistory(User user, String orderReferenceNumber, String paymentAmount, String orderStatus) {
        topHeaderPage
                .clickOnUserAccountBtn();
        accountPage
                .goToOrderHistoryPage();
        orderHistoryPage
                .clickOnOrderDetail(orderReferenceNumber);

        String orderDeliveryAddress = "Jan Nowak\n" +
                "Adres 1\n" +
                "City1, Idaho 11111\n" +
                "United States";
        String orderInvoiceAddress = user.getFirstName() +
                " " + user.getLastName() +
                "\n" + user.getAddress() +
                "\n" + user.getCity() +
                ", " + user.getState() +
                " " + user.getPostalCode() +
                "\n" + user.getCountry();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String orderDate = simpleDateFormat.format(date);

        assertThat(orderDetailsPage.getOrderDate()).isEqualTo(orderDate);
        assertThat(orderDetailsPage.getOrderStatus()).isEqualTo(orderStatus);
        assertThat(orderDetailsPage.getOrderTotalPrice()).isEqualTo(paymentAmount);
        log.info("Asserting actual {} to expected {}", orderDetailsPage.getOrderDeliveryAddress(), orderDeliveryAddress);
        assertThat(orderDetailsPage.getOrderDeliveryAddress()).isEqualTo(orderDeliveryAddress);
        log.info("Asserting actual {} to expected {}", orderDetailsPage.getOrderInvoiceAddress(), orderInvoiceAddress);
        assertThat(orderDetailsPage.getOrderInvoiceAddress()).isEqualTo(orderInvoiceAddress);
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

    private void logInUser(User user) {
        topHeaderPage.clickOnOnSignInBtn();

        signInPage
                .waitToSignInFormBeVisible()
                .typeUserEmail(user.getEmail())
                .typeInUserPassword(user.getPassword())
                .clickOnSignInBtn();
    }

    private void checkOrderConfirmationCorrectness(String productName, String productPrice, String productQuantity, String productQuantityPrice, String shippingPrice, String paymentMethod, String shippingMethod, String paymentAmount, String payableTo, String mailTo) {
        assertThat(orderConfirmationPage.getOrderedProductName()).contains(productName);
        assertThat(orderConfirmationPage.getOrderedProductPrice()).contains(productPrice);
        assertThat(orderConfirmationPage.getOrderedProductQuantity()).contains(productQuantity);
        assertThat(orderConfirmationPage.getOrderedProductQuantityPrice()).contains(productQuantityPrice);
        assertThat(orderConfirmationPage.getOrderShippingPrice()).contains(shippingPrice);
        assertThat(orderConfirmationPage.getOrderPaymentMethod()).contains(paymentMethod);
        assertThat(orderConfirmationPage.getOrderShippingMethod()).contains(shippingMethod);
        assertThat(orderConfirmationPage.getOrderPaymentAmount()).contains(paymentAmount);
        assertThat(orderConfirmationPage.getOrderPaymentPayableTo()).contains(payableTo);
        assertThat(orderConfirmationPage.getOrderPaymentMailTo()).contains(mailTo);
        assertThat(orderConfirmationPage.getOrderPaymentReferenceNumber()).contains(orderConfirmationPage.getOrderReferenceNumber());
    }

}
