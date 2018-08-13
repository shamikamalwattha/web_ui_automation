package com.sysco.web_ui_automation.pages;

import com.syscolab.qe.core.ui.SyscoLabUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.sysco.web_ui_automation.common.Constants.DEFAULT_TIMEOUT;

public abstract class PageBase {

    protected static SyscoLabUI syscoLabUI;
    protected static RemoteWebDriver webDriver;

    public PageBase(){

    }

    protected boolean isDisplayed(By by, Long timeOut){
        webDriver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        try{
            WebElement element = webDriver.findElement(by);
            webDriver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            return syscoLabUI.isDisplayed(element);
        } catch (Exception e){
            //LoggerUtil.logERROR("Element located by: " + by +  " not displayed", e);
            webDriver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            return false;
        }

    }

    protected List<WebElement> findElements(By by, Long timeOut){
        webDriver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        List<WebElement> elements = webDriver.findElements(by);
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return elements;
    }

    protected void waitUntillPageLoaded(){

        int count=0;
        while (true){
            boolean isComplete = (Boolean) new WebDriverWait(webDriver, DEFAULT_TIMEOUT).until(new Function<WebDriver, Object>() {
                public Object apply(WebDriver webDriver) {
                    return  ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
                }
            });
            sleep(1);
            count++;
            if(isComplete || count > DEFAULT_TIMEOUT)
                break;
        }

    }

    public static void setSyscoLabUI(SyscoLabUI syscoLabUI) {

        PageBase.syscoLabUI = syscoLabUI;
        PageBase.webDriver = PageBase.syscoLabUI.driver;
    }

    public void sleep(long seconds){

        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement waitUntilElementClickable(By by){

        WebDriverWait wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void refreshPage(){
        webDriver.navigate().refresh();
    }
}
