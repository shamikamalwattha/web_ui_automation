package com.sysco.web_ui_automation.pages;

import com.sysco.web_ui_automation.common.Constants;
import org.openqa.selenium.By;

public class HomePage extends PageBase {

    private By divBanner = By.className("home-hero-banner");

    public void waitUntillPageLoaded(){
        syscoLabUI.waitTillElementLoaded(divBanner);
    }

    public boolean isHomePageLoaded(){
        return isDisplayed(divBanner,15L);
    }


}
