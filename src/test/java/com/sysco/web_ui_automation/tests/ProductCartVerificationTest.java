package com.sysco.web_ui_automation.tests;

import com.sysco.web_ui_automation.Types.CreditCardTypes;
import com.sysco.web_ui_automation.Types.Pages;
import com.sysco.web_ui_automation.Types.PaymentMethods;
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

import static com.sysco.web_ui_automation.data.VerficationMessages.*;


@Listeners(SyscoLabListener.class)
public class ProductCartVerificationTest extends TestBase {

    private SoftAssert softAssert;
    UserData registeredUser;

    @BeforeClass
    public void initClass(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "Bundabergrum - Checkout");
        registeredUser = new UserData(UserTypes.REGISTERED_USER);
    }

    @Test(description = "Verify age less than 18 message")
    public void testAgeLessThan18(){

        softAssert = new SoftAssert();
        AgeVerification.verifyPageLoaded();
        UserData lessThanLimitUser = new UserData(UserTypes.LESS_THAN_LEGAL_AGE);
        AgeVerification.enterBirthDate(lessThanLimitUser);
        String errorMessage = AgeVerification.getErrorMessage();
        softAssert.assertEquals(errorMessage,AGE_LOCATION_NOT_PERMITTED_MESSAGE,
                "Verify age less than legal age message.");
        softAssert.assertAll();
    }

    @Test(description = "Verify legal age users are navigated to home page", dependsOnMethods = "testAgeLessThan18")
    public void testAgeMoreThan18(){

        softAssert = new SoftAssert();
        AgeVerification.verifyPageLoaded();
        UserData legalAgeUser = new UserData(UserTypes.LEGAL_AGE);
        AgeVerification.enterBirthDate(legalAgeUser);
        softAssert.assertTrue(Home.verifyPageLoaded(), "Verify legal age user is navigated to home page.");
        softAssert.assertAll();
    }

    @Test(description = "Verify My Account Page Loaded", dependsOnMethods = "testAgeMoreThan18")
    public void testMyAccountPageLoaded(){

        softAssert = new SoftAssert();
        MainMenu.navigateTo(Pages.MY_ACCOUNT);
        MyAccount.verifyPageLoaded();
        softAssert.assertTrue(MyAccount.isLoginButtonDisplayed(), "Verify My Account Page is loaded");
        softAssert.assertAll();
    }

    @Test(description = "Verify Email is mandatory", dependsOnMethods = "testMyAccountPageLoaded")
    public void testEmailIsMandatory(){

        softAssert = new SoftAssert();
        UserData emptyEmailUser = new UserData(UserTypes.EMPTY_EMAIL);
        String errorMessage = MyAccount.loginWithEmailError(emptyEmailUser);
        softAssert.assertEquals(errorMessage,MANDATORY_FIELD_MESSAGE, "Verify Email is Mandatory");
        softAssert.assertAll();
    }

    @Test(description = "Verify Password is mandatory", dependsOnMethods = "testEmailIsMandatory")
    public void testPwdIsMandatory(){

        softAssert = new SoftAssert();
        UserData emptyPwdUser = new UserData(UserTypes.EMPTY_PASSWORD);
        String errorMessage = MyAccount.loginWithPasswordError(emptyPwdUser);
        softAssert.assertEquals(errorMessage,MANDATORY_FIELD_MESSAGE, "Verify Password is Mandatory");
        softAssert.assertAll();
    }

    @Test(description = "Verify Invalid emails are not allowed", dependsOnMethods = "testPwdIsMandatory")
    public void testInvalidEmailRejected(){

        softAssert = new SoftAssert();
        UserData invalidEmailUser = new UserData(UserTypes.INVALID_EMAIL);
        String errorMessage = MyAccount.loginWithEmailError(invalidEmailUser);
        softAssert.assertEquals(errorMessage,INVALID_EMAIL_MESSAGE, "Verify Email is Invalid");
        softAssert.assertAll();
    }

    @Test(description = "Verify Invalid passwords are not allowed", dependsOnMethods = "testInvalidEmailRejected")
    public void testInvalidPasswordRejected(){

        softAssert = new SoftAssert();
        UserData invalidPasswordUser = new UserData(UserTypes.INVALID_PASSWORD);
        String errorMessage = MyAccount.loginWithPasswordError(invalidPasswordUser);
        softAssert.assertEquals(errorMessage,INVALID_PASSWORD_MESSAGE, "Verify Password is Invalid");
        softAssert.assertAll();
    }

    @Test(description = "Verify Successful login with a registered user" ,dependsOnMethods = "testInvalidPasswordRejected")
    public void testSuccessfulLogin(){

        softAssert = new SoftAssert();
        MyAccount.Login(registeredUser);
        AccountManagement.waitUntilPageLoaded();
        String loggedUserName = AccountManagement.getLoggedInUserName();
        softAssert.assertEquals(loggedUserName,registeredUser.getFirstName().toUpperCase()
                + " " + registeredUser.getLastName().toUpperCase());
        softAssert.assertAll();
    }


    @Test(description = "Verify cart displays added items correctly", dependsOnMethods = "testSuccessfulLogin")
    public void testProductsInCart(){

        softAssert = new SoftAssert();
        MainMenu.navigateTo(Pages.SHOPPING_MINI_CART);
        MainMenu.removeAllItemsFromCart();

        MainMenu.expandMenuItem(Pages.PRODUCTS);
        MainMenu.selectSubMenu(Pages.VIEW_ALL_BOTTLES);
        Products.verifyProductPageLoaded();
        Products.addExclusiveProductByProductName(ProductData.BUNDABERG_BLACK_XPATH_NAME);
        Products.addItemToCart();

        MainMenu.navigateTo(Pages.SHOPPING_CART);
        ShoppingCart.waitUntillPageLoaded();
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
        Checkout.selectPaymentMethod(PaymentMethods.CREDIT_CARD);
        String messageCCNO1 = Checkout.fillCreditCardNoWithInvalidData(new CreditCardData(CreditCardTypes.EMPTY_CC_NO));
        softAssert.assertEquals(messageCCNO1, VerficationMessages.MANDATORY_FIELD_MESSAGE, "Credit card no mandatory" );
        String messageCCNO2 = Checkout.fillCreditCardNoWithInvalidData(new CreditCardData(CreditCardTypes.INVALID_CC_NO));
        softAssert.assertEquals(messageCCNO2, VerficationMessages.INVALID_CC_NO_MESSAGE, "Credit card no invalid" );
        String messageCVV1 = Checkout.fillCreditCardCVVWithInvalidData(new CreditCardData(CreditCardTypes.EMPTY_CVV_NO));
        softAssert.assertEquals(messageCVV1, VerficationMessages.MANDATORY_FIELD_MESSAGE, "Credit card CVV mandatory" );
        softAssert.assertAll();

    }


}
