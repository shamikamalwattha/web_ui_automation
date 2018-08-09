package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.pages.ProductsPage;
import com.sysco.web_ui_automation.pages.ProductDetailsPage;

public class Products {

    private static ProductsPage productsPage = new ProductsPage();
    private static ProductDetailsPage productDetailsPage = new ProductDetailsPage();

    public static void addExclusiveProductByProductName(String productName){
        productsPage.selectProductByName(productName);
        productDetailsPage.waitUntillPageLoaded();
    }

    public static boolean verifyProductPageLoaded(){
        return productsPage.isItemsPerPageDisplayed();
    }

    public static void addItemToCart(){
        productDetailsPage.addItemToCart();
    }
}
