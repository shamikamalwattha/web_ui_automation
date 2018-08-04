package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.pages.HomePage;

public class Home {

    private static HomePage homePage = new HomePage();

    public static boolean verifyPageLoaded(){
        return homePage.isHomePageLoaded();
    }
}
