package com.sysco.web_ui_automation.data;

import com.sysco.web_ui_automation.Types.UserTypes;
import com.sysco.web_ui_automation.functions.AgeVerification;

public class UserData {

    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String country;

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

    public UserData(UserTypes userType){

        if (userType.equals(UserTypes.LESS_THAN_LEGAL_AGE)){
            birthDay = AgeVerificationData.DAYS.DAY_1.getDayNumber();
            birthMonth = AgeVerificationData.MONTHS.DECEMBER.getMonthName();
            birthYear = AgeVerificationData.LATEST_YEAR;

        } else if(userType.equals(UserTypes.LEGAL_AGE)){

            birthDay = AgeVerificationData.DAYS.DAY_1.getDayNumber();
            birthMonth = AgeVerificationData.MONTHS.DECEMBER.getMonthName();
            birthYear = AgeVerificationData.FURTHEST_YEAR;

        }
    }
}
