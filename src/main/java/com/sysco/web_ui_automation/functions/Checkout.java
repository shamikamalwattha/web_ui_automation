package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.data.CreditCardData;
import com.sysco.web_ui_automation.Types.PaymentMethods;
import com.sysco.web_ui_automation.pages.CheckoutPage;

public class Checkout {

    private static CheckoutPage checkoutPage = new CheckoutPage();

    public static String getFirstName(){
        return checkoutPage.getFirstNameTxtValue().trim();

    }
    public static String getLastName(){
        return checkoutPage.getLastNameTxtValue().trim();

    }

    public static void waitUntillPageLoaded(){
        checkoutPage.waitUntillPageLoaded();
    }

    public static void clearFirstName(){
        checkoutPage.clearFirstName();
    }

    public static void clearLastName(){
        checkoutPage.clearFirstName();
    }

    public static void fillFirstName(String firstName){
        checkoutPage.fillFirstName(firstName);
    }

    public static void fillLastName(String lastName){
        checkoutPage.fillLastName(lastName);
    }

    public static void continueCheckout(){
        checkoutPage.clickContinueButton();
    }

    public static boolean isStillInCheckoutPage(){
        return checkoutPage.isFirstNameTxtDisplayed();
    }

    public static void removePostCode(){
        checkoutPage.clickPostCodeRemoveButton();
    }

    public static void selectPostCodeFromSuggestions(int index){
        checkoutPage.selectPostCodeAutoCompleteSuggestion(index);
    }

    public static void searchPostCode(String postCode){
        checkoutPage.typeInPostCodeTxt(postCode);
    }

    public static void fillAddress(String adress){
        checkoutPage.typeInAdress1(adress);
    }

    public static void fillContactNo(String contactNo){
        checkoutPage.typeInContactNumber(contactNo);
    }

    public static void selectAuthorizeCheckBox(boolean status){
        checkoutPage.selectAuthorizeCheckBox(status);
    }

    public static String fillCreditCardNoWithInvalidData(CreditCardData creditCardData){
        checkoutPage.fillCreditCardNumber(creditCardData.getCreditCardNo());
        checkoutPage.fillCreditCardCVV(creditCardData.getCvv());
        checkoutPage.clickPurchaseOrderButton();
        return checkoutPage.getCreditCardNumberError();
    }

    public static String fillCreditCardCVVWithInvalidData(CreditCardData creditCardData){
        checkoutPage.fillCreditCardNumber(creditCardData.getCreditCardNo());
        checkoutPage.fillCreditCardCVV(creditCardData.getCvv());
        checkoutPage.clickPurchaseOrderButton();
        return checkoutPage.getCreditCardCVVError();
    }

    public static void selectPaymentMethod(PaymentMethods paymentMethods){
        checkoutPage.selectPaymentMethod(paymentMethods);
    }

    public static void continueToPayment(){
        checkoutPage.clickContinueToPaymentButton();
    }
}
