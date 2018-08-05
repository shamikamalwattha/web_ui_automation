package com.sysco.web_ui_automation.pages;

import org.openqa.selenium.By;

public class MyAccountPage extends PageBase {

    private By btnLogin = By.id("send2");
    private By txtEmail = By.id("email");
    private By txtPassword = By.id("pass");
    private By divEmailError = By.xpath("//*[@id='email']/following-sibling::div[@class='validation-advice']");
    private By divPasswordError = By.xpath("//*[@id='pass']/following-sibling::div[@class='validation-advice']");


    public boolean isLoginButtonVisible(){
        return isDisplayed(btnLogin,10L);
    }

    public void enterEmailAddress(String emailAddress){
        syscoLabUI.sendKeys(txtEmail,emailAddress);
    }
    public void enterPassword(String password){
        syscoLabUI.sendKeys(txtPassword,password);
    }
    public void clickLoginButton(){
        syscoLabUI.click(btnLogin);
    }
    public String getEmailErrorMessage(){
        return syscoLabUI.getText(divEmailError);
    }
    public String getPaswordErrorMessage(){
        return syscoLabUI.getText(divPasswordError);
    }
}
