package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.data.UserData;
import com.sysco.web_ui_automation.pages.MyAccountPage;

public class MyAccount {

    private static MyAccountPage myAccountPage = new MyAccountPage();

    public static void verifyPageLoaded(){
        myAccountPage.waitUntillPageLoaded();
    }

    public static boolean isLoginButtonDisplayed(){
        return myAccountPage.isLoginButtonVisible();
    }

    public static String loginWithPasswordError(UserData userData){
        myAccountPage.clearEmailAddress();
        myAccountPage.clearPassword();
        myAccountPage.enterEmailAddress(userData.getEmail());
        if(!userData.getPassword().isEmpty())
            myAccountPage.enterPassword(userData.getPassword());
        myAccountPage.clickLoginButton();
        return myAccountPage.getPaswordErrorMessage();
    }

    public static String loginWithEmailError(UserData userData){

        myAccountPage.clearEmailAddress();
        myAccountPage.clearPassword();
        if(!userData.getEmail().isEmpty())
            myAccountPage.enterEmailAddress(userData.getEmail());
        myAccountPage.enterPassword(userData.getPassword());
        myAccountPage.clickLoginButton();
        return myAccountPage.getEmailErrorMessage();
    }

    public static void Login(UserData userData){
        myAccountPage.enterEmailAddress(userData.getEmail());
        myAccountPage.enterPassword(userData.getPassword());
        myAccountPage.clickLoginButton();
    }

}
