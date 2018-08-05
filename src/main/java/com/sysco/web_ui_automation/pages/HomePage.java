package com.sysco.web_ui_automation.pages;

import com.sysco.web_ui_automation.common.Constants;
import org.openqa.selenium.By;

public class HomePage extends PageBase {

    private By divBanner = By.className("home-hero-banner");

    public boolean isHomePageBannerVisible(){
        return isDisplayed(divBanner,10L);
    }
}
