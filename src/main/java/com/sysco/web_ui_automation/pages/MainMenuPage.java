package com.sysco.web_ui_automation.pages;

import com.sysco.web_ui_automation.Types.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainMenuPage extends PageBase {

    private By lnkMyAccount = By.className("links-acc");

    public void navigateTo(Pages page){

        switch (page){

            case HOME:
                break;

            case MY_ACCOUNT:
                sleep(3000);
                syscoLabUI.click(lnkMyAccount);
                break;
        }
    }
}
