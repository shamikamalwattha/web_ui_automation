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
    private String firstName;
    private String lastName;
    private String address1;
    private String contactNo;
    private String postCode;


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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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
                email = "williamjacob803@gmail.com";
                password = "12345678";
                firstName = "William";
                lastName = "Jacob";
                country = "Australia";
                contactNo = "5555555555";
                address1 = "ABC";
                postCode = "2000";

        }
    }
}
