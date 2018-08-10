package com.sysco.web_ui_automation.pages;

import com.syscolab.qe.core.ui.web.SyscoLabWUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends PageBase {

    private By btnCheckout = By.xpath("//div[@class='payment-types bottom']//button[@title='Proceed to Checkout']");
    String productNameXpath = "//*[@id='shopping-cart-table']/div[2]/div[ITEM_INDEX]/div/div[2]/h2";
    String productPriceXpath = "//*[@id='shopping-cart-table']/div[2]/div[ITEM_INDEX]/div/div[3]/div[5]/span/span";

    public String getItemName(int itemIndex){

        productNameXpath = productNameXpath.replace("ITEM_INDEX",String.valueOf(itemIndex));
        return syscoLabUI.getText(By.xpath(productNameXpath));
    }

    public String getItemPrice(int itemIndex){
        productPriceXpath = productPriceXpath.replace("ITEM_INDEX", String.valueOf(itemIndex));
        return syscoLabUI.getText(By.xpath(productPriceXpath));
    }

    public boolean isCheckoutButtonDisplayed(){
        return isDisplayed(btnCheckout,10L);
    }

    public void waitUntillPageLoaded(){
        syscoLabUI.waitTillElementLoaded(btnCheckout);
    }

    public void clickProceedToCheckoutBtn(){
        syscoLabUI.click(btnCheckout);
    }

}
