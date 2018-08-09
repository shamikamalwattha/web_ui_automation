package com.sysco.web_ui_automation.tests;

import com.sysco.web_ui_automation.Types.CreditCardTypes;
import com.sysco.web_ui_automation.Types.Pages;
import com.sysco.web_ui_automation.Types.UserTypes;
import com.sysco.web_ui_automation.data.*;
import com.sysco.web_ui_automation.functions.*;
import com.sysco.web_ui_automation.utils.TestBase;
import com.syscolab.qe.core.reporting.SyscoLabListener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


@Listeners(SyscoLabListener.class)
public class ProductCartVerificationTest extends TestBase {

    private SoftAssert softAssert;
    UserData registeredUser;
    @BeforeClass
    public void initClass(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "Account Verification - Account Verification");

        AgeVerification.verifyPageLoaded();
        UserData legalAgeUser = new UserData(UserTypes.LEGAL_AGE);
        AgeVerification.enterBirthDate(legalAgeUser);
        Home.verifyPageLoaded();
        MainMenu.navigateTo(Pages.MY_ACCOUNT);
        registeredUser = new UserData(UserTypes.REGISTERED_USER);
        MyAccount.Login(registeredUser);
        AccountManagement.verifyPageLoaded();

        MainMenu.navigateTo(Pages.SHOPPING_MINI_CART);
        MainMenu.removeAllItemsFromCart();

    }

    @Test(description = "Verify cart displays added items correctly")
    public void testProductsInCart(){

        softAssert = new SoftAssert();
        MainMenu.expandMenuItem(Pages.PRODUCTS);
        MainMenu.selectSubMenu(Pages.VIEW_ALL_BOTTLES);
        Products.verifyProductPageLoaded();
        Products.addExclusiveProductByProductName(ProductData.BUNDABERG_BLACK_XPATH_NAME);
        Products.addItemToCart();

        MainMenu.navigateTo(Pages.SHOPPING_CART);
        ShoppingCart.verifyShoppingCartLoaded();
        softAssert.assertEquals(ShoppingCart.getShoppingCartItemName(1).toUpperCase(),(ProductData.BUNDABERG_BLACK_CART_NAME));
        softAssert.assertEquals(ShoppingCart.getShoppingCartItemPrice(1).toUpperCase(),ProductData.BUNDABERG_BLACK_PRICE);
        softAssert.assertAll();

    }

    @Test(description = "Verify checkout page details", dependsOnMethods = "testProductsInCart",alwaysRun = true)
    public void testCheckoutPageData(){

        softAssert = new SoftAssert();
        ShoppingCart.proceedToCheckout();
        Checkout.waitUntillPageLoaded();
        softAssert.assertEquals(Checkout.getFirstName().toLowerCase(), registeredUser.getFirstName().toLowerCase(), "Verify First Name" );
        softAssert.assertEquals(Checkout.getLastName().toLowerCase(), registeredUser.getLastName().toLowerCase(), "Verify Last Name" );
        softAssert.assertAll();
    }

    @Test(description = "Verify continue button behaviour without mandatory data", dependsOnMethods = "testCheckoutPageData",alwaysRun = true)
    public void testContinueWithoutMandatoryInfo(){

        softAssert = new SoftAssert();
        Checkout.clearFirstName();
        Checkout.continueCheckout();
        softAssert.assertTrue(Checkout.isStillInCheckoutPage(), "Verify checkout not continued");
        Checkout.fillFirstName(registeredUser.getFirstName());
        Checkout.fillAddress(registeredUser.getAddress1());
        Checkout.fillContactNo(registeredUser.getContactNo());
        Checkout.removePostCode();
        Checkout.searchPostCode(registeredUser.getPostCode());
        Checkout.selectPostCodeFromSuggestions(1);
        Checkout.continueCheckout();
        Checkout.selectAuthorizeCheckBox(true);
        Checkout.continueToPayment();
        softAssert.assertAll();
    }

    @Test(description = "Verify Credit Card Validation", dependsOnMethods = "testContinueWithoutMandatoryInfo")
    public void testCreditCardValidation(){

        softAssert = new SoftAssert();
        Checkout.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        String messageCCNO1 = Checkout.fillCreditCardCVVWithInvalidData(new CreditCardData(CreditCardTypes.EMPTY_CC_NO));
        softAssert.assertEquals(messageCCNO1, VerficationMessages.MANDATORY_FIELD_MESSAGE, "Credit card no mandatory" );

        String messageCVV1 = Checkout.fillCreditCardCVVWithInvalidData(new CreditCardData(CreditCardTypes.EMPTY_CVV_NO));
        softAssert.assertEquals(messageCVV1, VerficationMessages.MANDATORY_FIELD_MESSAGE, "Credit card CVV mandatory" );

        String messageCCNO2 = Checkout.fillCreditCardCVVWithInvalidData(new CreditCardData(CreditCardTypes.INVALID_CC_NO));
        softAssert.assertEquals(messageCCNO2, VerficationMessages.INVALID_CC_NO_MESSAGE, "Credit card no invalid" );
        softAssert.assertAll();

    }


}
