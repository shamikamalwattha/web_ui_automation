package com.sysco.web_ui_automation.data;

public class AgeVerificationData {

    public static final String LATEST_YEAR = "2003";
    public static final String FURTHEST_YEAR = "1913";
    public enum MONTHS{
        JANUARY("January"),
        FEBRUARY("February"),
        MARCH("March"),
        APRIL("April"),
        MAY("May"),
        JUNE("June"),
        JULY("July"),
        AUGUST("August"),
        SEPTEMBER("September"),
        OCTOBER("October"),
        NOVEMBER("November"),
        DECEMBER("December");

        private String monthName;
        MONTHS(String monthName){
            this.monthName = monthName;
        }

        public String getMonthName(){
            return monthName;
        }
    }

    public enum DAYS{

        DAY_1("1"),DAY_2("2"),DAY_3("3"),DAY_4("4"),DAY_5("5"),DAY_6("6"),DAY_7("7"), DAY_8("8"), DAY_9("9"), DAY_10("10"),
        DAY_11("11"),DAY_12("12"),DAY_13("13"),DAY_14("14"),DAY_15("15"),DAY_16("16"),DAY_17("17"), DAY_18("18"), DAY_19("19"),
        DAY_20("20"),DAY_21("21"),DAY_22("22"),DAY_23("23"),DAY_24("24"),DAY_25("25"),DAY_26("26"),DAY_27("27"), DAY_28("28"), DAY_29("29"),
        DAY_30("30"), DAY_31("31");

        private String dayNumber;
        DAYS(String dayNumber){
            this.dayNumber = dayNumber;
        }

        public String getDayNumber(){
            return dayNumber;
        }
    }
}
