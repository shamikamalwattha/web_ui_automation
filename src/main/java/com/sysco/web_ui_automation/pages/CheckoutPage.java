package com.sysco.web_ui_automation.pages;

import com.sysco.web_ui_automation.Types.PaymentMethods;
import org.openqa.selenium.By;

public class CheckoutPage extends PageBase {

    private By txtFirstName = By.id("billing:firstname");
    private By txtLastName = By.id("billing:lastname");
    private By btnContinue = By.id("delivery-address-button");
    private By btnContinueToPayment = By.id("shipping-method-button");
    private By btnPostCodeRemove = By.id("billing:postcodesuburbremove");
    private By divPostCodeAutoComplete =  By.id("Autocomplete_billing:postcodesuburb");
    private By txtPostCode = By.id("billing:postcodesuburb");
    private By txtAdress1 = By.id("billing:street1");
    private By txtContact = By.id("billing:telephone");
    private By checkAuthorize = By.id("ns-checkout-shipping-authorize_mm");
    private By rbCreditCard = By.xpath("(//div[@class='payment-method-wrapper'])[1]");
    private By rbPaypal = By.xpath("(//div[@class='payment-method-wrapper'])[2]");
    private By divPaymentMethods = By.id("-payment-methods");
    private By txtCreditCardNo = By.id("braintree_cc_number");
    private By ddCreditCardExpiryMonth = By.id("braintree_expiration");
    private By ddCreditCardExpiryYear = By.id("braintree_expiration_yr");
    private By txtCVV = By.id("braintree_cc_cid");
    private By btnPurchase = By.id("payment-method-button");

    private By divCreditCardNoError = By.xpath("//*[@id='braintree_cc_number']/following-sibling::div[@class='validation-advice']");
    private By divCVVError = By.xpath("//*[@id='braintree_cc_cid']/following-sibling::div[@class='validation-advice']");

    public String getFirstNameTxtValue(){
        return syscoLabUI.getValue(txtFirstName);
    }

    public String getLastNameTxtValue(){
        return syscoLabUI.getValue(txtLastName);
    }

    public void waitUntillPageLoaded(){
        syscoLabUI.waitTillElementLoaded(txtFirstName);
    }

    public void clearFirstName(){
        syscoLabUI.clear(txtFirstName);
    }

    public void clearLastName(){
        syscoLabUI.clear(txtLastName);
    }

    public void fillFirstName(String firstName){
        syscoLabUI.sendKeys(txtFirstName,firstName);
    }

    public void fillLastName(String lastName){
        syscoLabUI.sendKeys(txtLastName, lastName);
    }

    public void clickContinueButton(){
        syscoLabUI.click(btnContinue);
    }
    public void clickContinueToPaymentButton(){
        syscoLabUI.click(btnContinueToPayment);
    }

    public boolean isFirstNameTxtDisplayed(){
        return isDisplayed(txtFirstName,10L);
    }

    public void clickPostCodeRemoveButton(){
        syscoLabUI.click(btnPostCodeRemove);
    }

    public void selectPostCodeAutoCompleteSuggestion(int index){

        syscoLabUI.waitTillElementLoaded(divPostCodeAutoComplete);
        syscoLabUI.click(webDriver.findElement(divPostCodeAutoComplete).findElement(By.xpath(".//div['"+index+"']")));
        syscoLabUI.waitTillElementDisappear(divPostCodeAutoComplete);
    }

    public void typeInPostCodeTxt(String postCode){
        syscoLabUI.sendKeys(txtPostCode,postCode);
    }

    public void typeInAdress1(String adress){
        syscoLabUI.sendKeys(txtAdress1,adress);
    }

    public void typeInContactNumber(String contactNo){
        syscoLabUI.sendKeys(txtContact,contactNo);
    }

    public void selectAuthorizeCheckBox(boolean checkStatus){

        syscoLabUI.waitTillElementLoaded(btnContinueToPayment);
        boolean currentStatus=false;
        if(syscoLabUI.getValue(checkAuthorize).equals("1"))
            currentStatus = true;
         else
            checkStatus = false;

         if(currentStatus != checkStatus)
             syscoLabUI.click(checkAuthorize);
    }

    public void selectPaymentMethod(PaymentMethods paymentMethods){

        sleep(3);
        syscoLabUI.waitTillElementLoaded(rbCreditCard);
        if (paymentMethods.equals(PaymentMethods.CREDIT_CARD)){
            waitUntilElementClickable(rbCreditCard);
            syscoLabUI.click(rbCreditCard);
        } else {
            waitUntilElementClickable(rbCreditCard);
            syscoLabUI.click(rbPaypal);
        }
    }

    public void fillCreditCardNumber(String cardNo){
        syscoLabUI.sendKeys(txtCreditCardNo,cardNo);
    }

    public void fillCreditCardCVV(String cvv){
        syscoLabUI.sendKeys(txtCVV,cvv);
    }

    public String getCreditCardNumberError(){
        return syscoLabUI.getText(divCreditCardNoError);
    }

    public String getCreditCardCVVError(){
        return syscoLabUI.getText(divCVVError);
    }

    public void clickPurchaseOrderButton(){
        syscoLabUI.click(btnPurchase);
    }
}
