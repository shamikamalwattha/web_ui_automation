package com.sysco.web_ui_automation.pages;

import org.openqa.selenium.By;

public class ProductsPage extends PageBase {

    String productXpath =  "(//a[contains(text(),'product_name')])[last()]";
    By lbtItemCount = By.xpath("//div[@id='toolbar']//div[@class='plist-pager-total']");


    public void selectProductByName(String name){
        productXpath = productXpath.replaceAll("product_name", name);
        syscoLabUI.isDisplayed(By.xpath(productXpath));
        syscoLabUI.click(By.xpath(productXpath));
    }

    public boolean isItemsPerPageDisplayed(){
        return syscoLabUI.isDisplayed(lbtItemCount);
    }
}
