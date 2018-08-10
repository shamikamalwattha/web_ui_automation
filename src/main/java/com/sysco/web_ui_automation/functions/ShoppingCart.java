package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.pages.ShoppingCartPage;

public class ShoppingCart {

    private static ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    public static String getShoppingCartItemName(int itemIndex){
        return shoppingCartPage.getItemName(itemIndex);
    }

    public static String getShoppingCartItemPrice(int itemIndex){
        return shoppingCartPage.getItemPrice(itemIndex);
    }

    public static void waitUntillPageLoaded(){
        shoppingCartPage.waitUntillPageLoaded();
    }

    public static void proceedToCheckout(){
        shoppingCartPage.clickProceedToCheckoutBtn();
    }
}
