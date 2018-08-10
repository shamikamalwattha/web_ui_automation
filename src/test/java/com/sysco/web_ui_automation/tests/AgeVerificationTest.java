package com.sysco.web_ui_automation.tests;

import com.sysco.web_ui_automation.Types.UserTypes;
import com.sysco.web_ui_automation.data.UserData;
import com.sysco.web_ui_automation.functions.AgeVerification;
import com.sysco.web_ui_automation.functions.Home;
import com.sysco.web_ui_automation.pages.HomePage;
import com.sysco.web_ui_automation.utils.TestBase;
import com.syscolab.qe.core.reporting.SyscoLabListener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.sysco.web_ui_automation.data.VerficationMessages.AGE_LOCATION_NOT_PERMITTED_MESSAGE;

@Listeners(SyscoLabListener.class)
public class AgeVerificationTest extends TestBase {

    private SoftAssert softAssert;
    @BeforeClass
    public void initClass(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "Bundabergrum - Checkout");
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


}
