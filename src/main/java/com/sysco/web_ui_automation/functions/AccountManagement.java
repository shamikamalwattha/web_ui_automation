package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.pages.AccountManagementPage;

public class AccountManagement {

    private static AccountManagementPage accountManagementPage = new AccountManagementPage();
    public static boolean verifyPageLoaded(){
        return accountManagementPage.isUserWelcomeMsgDisplayed();
    }

    public static String getLoggedInUserName(){
        return accountManagementPage.getLoggedInUserName();
    }
}
