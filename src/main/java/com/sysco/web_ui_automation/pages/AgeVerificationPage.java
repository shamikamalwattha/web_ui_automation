package com.sysco.web_ui_automation.pages;

import org.openqa.selenium.By;

public class AgeVerificationPage extends PageBase {

    private By ddDay = By.id("age_select_day");
    private By ddMonth = By.id("age_select_month");
    private By ddYear = By.id("age_select_year");
    private By ddCountry = By.id("age_select_country");
    private By btnEnter = By.id("age_confirm_btn");
    private By divErrorMessage = By.id("age_missing_message");

    public void waitUntillPageLoaded(){
        syscoLabUI.waitTillElementLoaded(btnEnter);
    }

    public void selectDay(String day){
        syscoLabUI.selectFromDropDown(ddDay,day);
    }
    public void selectMonth(String month){
        syscoLabUI.selectFromDropDown(ddMonth,month);
    }
    public void selectYear(String year){
        syscoLabUI.selectFromDropDown(ddYear,year);
    }
    public void selectCountry(String country){
        syscoLabUI.selectFromDropDown(ddCountry,country);
    }
    public void clickEnterBtn(){
        syscoLabUI.click(btnEnter);
    }
    public String getErrorMessage(){
       return syscoLabUI.findElement(divErrorMessage).getText();
    }
}