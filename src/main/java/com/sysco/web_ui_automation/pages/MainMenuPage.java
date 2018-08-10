package com.sysco.web_ui_automation.pages;

import com.sysco.web_ui_automation.Types.Pages;
import com.sysco.web_ui_automation.functions.ShoppingCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainMenuPage extends PageBase {

    private By lnkMyAccount = By.xpath("(//*[@class='links-acc'])[last()]");
    private By lnkMenuBar = By.className("nav-list");
    private By lnkProducts = By.className("link-product");
    private By divSubMenu = By.className("link-product-submenu");
    private By lnkShoppingCart = By.xpath("//div[@class='top-cart']");
    private By btnCheckout = By.xpath("//span[contains(text(),'Checkout')]");
    private By divMiniCartTopBar = By.id("topCartContent");
    private By divCartEmpty = By.className("cart-empty");
    private By btnRemove = By.className("btn-remove2");

    public void navigateTo(Pages page){

        switch (page){

            case HOME:
                break;

            case MY_ACCOUNT:
                sleep(3);
                syscoLabUI.click(lnkMyAccount);
                break;
            case SHOPPING_CART:
                syscoLabUI.click(lnkShoppingCart);
                syscoLabUI.waitTillElementLoaded(btnCheckout);
                syscoLabUI.click(btnCheckout);
                break;
            case SHOPPING_MINI_CART:
                syscoLabUI.click(lnkShoppingCart);
                syscoLabUI.waitTillElementLoaded(divMiniCartTopBar);
                break;

        }
    }

    public void moveToMenuItem(Pages page){
        switch (page){
            case PRODUCTS:
                syscoLabUI.mouseHover(lnkProducts);
                syscoLabUI.waitTillElementLoaded(divSubMenu);
                break;
        }

    }

    public void selectLinkInSubMenu(Pages page){

        switch (page){

            case VIEW_ALL_BOTTLES:
                WebElement element = webDriver.findElement(divSubMenu).findElement(By.linkText(Pages.VIEW_ALL_BOTTLES.getMenuItemName()));
                syscoLabUI.click(element);
        }
    }

    public boolean getCartIsEmpty(){
        return !findElements(divCartEmpty,5L).isEmpty();
    }

    public void removeAllItemsFromCart(){

        if(!getCartIsEmpty()){
            syscoLabUI.click(btnCheckout);
            ShoppingCart.waitUntillPageLoaded();

            syscoLabUI.click(btnRemove);
            syscoLabUI.waitTillElementDisappear(btnRemove);
            waitUntillPageLoaded();
            sleep(2);
        }
    }
}
