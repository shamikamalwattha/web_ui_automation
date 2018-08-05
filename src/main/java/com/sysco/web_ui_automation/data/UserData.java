package com.sysco.web_ui_automation.data;

import com.sysco.web_ui_automation.Types.UserTypes;
import com.sysco.web_ui_automation.functions.AgeVerification;

public class UserData {

    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String country;
    private String email;
    private String password;

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserData(UserTypes userType){

        switch (userType){
            case LESS_THAN_LEGAL_AGE:
                birthDay = AgeVerificationData.DAYS.DAY_1.getDayNumber();
                birthMonth = AgeVerificationData.MONTHS.DECEMBER.getMonthName();
                birthYear = AgeVerificationData.LATEST_YEAR;
                break;
            case LEGAL_AGE:
                birthDay = AgeVerificationData.DAYS.DAY_1.getDayNumber();
                birthMonth = AgeVerificationData.MONTHS.DECEMBER.getMonthName();
                birthYear = AgeVerificationData.FURTHEST_YEAR;
                break;
            case EMPTY_EMAIL:
                email = "";
                password = "12345678";
                break;
            case EMPTY_PASSWORD:
                email = "williamjacob802@gmail.com";
                password = "";
                break;
            case INVALID_EMAIL:
                email = "williamjacob802";
                password = "12345678";
                break;
            case INVALID_PASSWORD:
                email = "williamjacob802@gmail.com";
                password = "12345";
                break;
            case REGISTERED_USER:
                email = "williamjacob802@gmail.com";
                password = "12345678";

        }
    }
}
