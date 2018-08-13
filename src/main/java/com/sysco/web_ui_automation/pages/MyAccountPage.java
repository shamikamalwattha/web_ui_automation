package com.sysco.web_ui_automation.pages;

import org.openqa.selenium.By;

public class MyAccountPage extends PageBase {

    private By btnLogin = By.id("send2");
    private By txtEmail = By.id("email");
    private By txtPassword = By.id("pass");
    private By divEmailError = By.xpath("//*[@id='email']/following-sibling::div[@class='validation-advice']");
    private By divPasswordError = By.xpath("//*[@id='pass']/following-sibling::div[@class='validation-advice']");


    public boolean isLoginButtonVisible(){
        return isDisplayed(btnLogin,15L);
    }

    public void enterEmailAddress(String emailAddress){
        syscoLabUI.sendKeys(txtEmail,emailAddress);
    }
    public void enterPassword(String password){
        syscoLabUI.sendKeys(txtPassword,password);
    }
    public void clickLoginButton(){
        waitUntilElementClickable(btnLogin);
        syscoLabUI.click(btnLogin);
    }
    public String getEmailErrorMessage(){
        return syscoLabUI.getText(divEmailError);
    }
    public String getPaswordErrorMessage(){
        return syscoLabUI.getText(divPasswordError);
    }

    public void waitUntillPageLoaded(){
        syscoLabUI.waitTillElementLoaded(btnLogin);
    }

    public void clearEmailAddress(){
        syscoLabUI.clear(txtEmail);
    }

    public void clearPassword(){
        syscoLabUI.clear(txtPassword);
    }

}
