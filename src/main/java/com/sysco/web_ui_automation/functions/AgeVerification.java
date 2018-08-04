package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.data.UserData;
import com.sysco.web_ui_automation.pages.AgeVerificationPage;
import com.sysco.web_ui_automation.pages.HomePage;

public class AgeVerification {

    private static AgeVerificationPage ageVerificationPage = new AgeVerificationPage();

    public static void verifyPageLoaded(){
        ageVerificationPage.waitUntillPageLoaded();
    }
    public static void enterBirthDate(UserData userData){

        ageVerificationPage.selectDay(userData.getBirthDay());
        ageVerificationPage.selectMonth(userData.getBirthMonth());
        ageVerificationPage.selectYear(userData.getBirthYear());
        ageVerificationPage.clickEnterBtn();
    }

    public static String getErrorMessage(){
        return ageVerificationPage.getErrorMessage();
    }

}
