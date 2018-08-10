package com.sysco.web_ui_automation.pages;

import org.openqa.selenium.By;

public class ProductDetailsPage extends PageBase {

    private By btnAddToCart = By.xpath("//div[@class='add-to-box']//button[@title='Add to Cart']");

    public void waitUntillPageLoaded(){
        syscoLabUI.waitTillElementLoaded(btnAddToCart);
    }

    public void addItemToCart(){
        syscoLabUI.click(btnAddToCart);
    }
}
