package com.sysco.web_ui_automation.Types;

public enum Pages {

    HOME,
    MY_ACCOUNT,
    PRODUCTS,
    SHOPPING_CART,
    SHOPPING_MINI_CART,
    EXCLUSIVE_RANGE("Exclusive Range"),
    ROYAL_AND_FLAVOURED("Royal Liqueur & Flavour Infused"),
    MASTERS_COLLECTION("Masters Distillers' Collection"),
    OLD_AGED_RANGE("Old Aged Range"),
    CLASSIC_RANGE("Classic Range"),
    BUNDLE_OFFERS("Bundle Offers"),
    VIEW_ALL_BOTTLES("View All Bottles");

    private String menuItemName;

    Pages(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    Pages() {

    }

    public String getMenuItemName() {
        return menuItemName;
    }
}

