package com.sysco.web_ui_automation.pages;

import org.openqa.selenium.By;

import static com.sysco.web_ui_automation.common.Constants.DEFAULT_TIMEOUT;

public class AccountManagementPage extends PageBase{

    private By txtWelcomeMessage = By.xpath("//div[@class='welcome-msg']/h2");


    public String getLoggedInUserName(){

        return webDriver.findElement(txtWelcomeMessage).getText().split(",")[1].trim().replace("!","");
    }

    public boolean isUserWelcomeMsgDisplayed(){
        return isDisplayed(txtWelcomeMessage,DEFAULT_TIMEOUT);
    }

    public void waitUntilPageLoaded(){
        syscoLabUI.waitTillElementLoaded(txtWelcomeMessage,DEFAULT_TIMEOUT);
    }

}