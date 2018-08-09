package com.sysco.web_ui_automation.data;

import com.sysco.web_ui_automation.Types.CreditCardTypes;

public class CreditCardData {

    private String creditCardNo;
    private String cvv;

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public CreditCardData(CreditCardTypes creditCardTypes){
        switch (creditCardTypes){

            case EMPTY_CC_NO:
                creditCardNo = "";
                cvv = "123";
                break;

            case INVALID_CC_NO:
                creditCardNo = "411111111111111";
                cvv = "123";
                break;

            case EMPTY_CVV_NO:
                creditCardNo = "4111111111111111";
                cvv="";
                break;

        }
    }
}
