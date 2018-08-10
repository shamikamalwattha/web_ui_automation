package com.sysco.web_ui_automation.tests;

import com.sysco.web_ui_automation.Types.Pages;
import com.sysco.web_ui_automation.Types.UserTypes;
import com.sysco.web_ui_automation.data.UserData;
import com.sysco.web_ui_automation.functions.*;
import com.sysco.web_ui_automation.utils.TestBase;
import com.syscolab.qe.core.reporting.SyscoLabListener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.sysco.web_ui_automation.data.VerficationMessages.INVALID_EMAIL_MESSAGE;
import static com.sysco.web_ui_automation.data.VerficationMessages.INVALID_PASSWORD_MESSAGE;
import static com.sysco.web_ui_automation.data.VerficationMessages.MANDATORY_FIELD_MESSAGE;

@Listeners(SyscoLabListener.class)
public class AccountVerificationTest extends TestBase {

    private SoftAssert softAssert;
    @BeforeClass
    public void initClass(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "Bundabergrum - Checkout");
    }

    @Test(description = "Verify My Account Page Loaded")
    public void testMyAccountPageLoaded(){

        AgeVerification.verifyPageLoaded();
        UserData legalAgeUser = new UserData(UserTypes.LEGAL_AGE);
        AgeVerification.enterBirthDate(legalAgeUser);
        Home.verifyPageLoaded();

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
        UserData registeredUser = new UserData(UserTypes.REGISTERED_USER);
        MyAccount.Login(registeredUser);
        AccountManagement.verifyPageLoaded();
        String loggedUserName = AccountManagement.getLoggedInUserName();
        softAssert.assertEquals(loggedUserName,registeredUser.getFirstName().toUpperCase()
                + " " + registeredUser.getLastName().toUpperCase());
        softAssert.assertAll();
    }


}
