package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.data.UserData;
import com.sysco.web_ui_automation.pages.MyAccountPage;

public class MyAccount {

    private static MyAccountPage myAccountPage = new MyAccountPage();

    public static boolean verifyPageLoaded(){
        return myAccountPage.isLoginButtonVisible();
    }

    public static String loginWithPasswordError(UserData userData){
        myAccountPage.enterEmailAddress(userData.getEmail());
        myAccountPage.enterPassword(userData.getPassword());
        myAccountPage.clickLoginButton();
        return myAccountPage.getPaswordErrorMessage();
    }

    public static String loginWithEmailError(UserData userData){
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
