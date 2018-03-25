package site.iway.javahelpers;

import java.text.DecimalFormat;
import java.util.Calendar;

public class StringConverter {

    public static String dayToEnglish(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
        }
        return null;
    }

    public static String dayToChineseZhou(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
            case Calendar.SUNDAY:
                return "周日";
        }
        return null;
    }

    public static String dayToChineseXingQi(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return "星期一";
            case Calendar.TUESDAY:
                return "星期二";
            case Calendar.WEDNESDAY:
                return "星期三";
            case Calendar.THURSDAY:
                return "星期四";
            case Calendar.FRIDAY:
                return "星期五";
            case Calendar.SATURDAY:
                return "星期六";
            case Calendar.SUNDAY:
                return "星期天";
        }
        return null;
    }

    public static String monthToEnglish(int month) {
        switch (month) {
            case Calendar.JANUARY:
                return "January";
            case Calendar.FEBRUARY:
                return "February";
            case Calendar.MARCH:
                return "March";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "June";
            case Calendar.JULY:
                return "July";
            case Calendar.AUGUST:
                return "August";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "October";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "December";
        }
        return null;
    }

    public static String monthToChinese(int month) {
        switch (month) {
            case Calendar.JANUARY:
                return "一月";
            case Calendar.FEBRUARY:
                return "二月";
            case Calendar.MARCH:
                return "三月";
            case Calendar.APRIL:
                return "四月";
            case Calendar.MAY:
                return "五月";
            case Calendar.JUNE:
                return "六月";
            case Calendar.JULY:
                return "七月";
            case Calendar.AUGUST:
                return "八月";
            case Calendar.SEPTEMBER:
                return "九月";
            case Calendar.OCTOBER:
                return "十月";
            case Calendar.NOVEMBER:
                return "十一月";
            case Calendar.DECEMBER:
                return "十二月";
        }
        return null;
    }

    private static DecimalFormat mMoneyFormatter = new DecimalFormat("0.00");
    private static DecimalFormat mMoneyFormatterNoDecimal = new DecimalFormat("0");

    public static String money(double value) {
        return mMoneyFormatter.format(value);
    }

    public static String money(String value) {
        return mMoneyFormatter.format(value);
    }

    public static String moneyNoDecimal(double value) {
        return mMoneyFormatterNoDecimal.format(value);
    }

    public static String moneyNoDecimal(String value) {
        return mMoneyFormatterNoDecimal.format(value);
    }

    private static DecimalFormat mDistanceFormatterKM = new DecimalFormat("0.0");
    private static DecimalFormat mDistanceFormatterM = new DecimalFormat("0");

    public static String distanceToEnglish(double km) {
        if (km >= 1) {
            return mDistanceFormatterKM.format(km) + "km";
        } else {
            return mDistanceFormatterM.format(km * 1000) + "m";
        }
    }

    public static String distanceToChineseQianMi(double km) {
        if (km >= 1) {
            return mDistanceFormatterKM.format(km) + "千米";
        } else {
            return mDistanceFormatterM.format(km * 1000) + "米";
        }
    }

    public static String distanceToChineseGongLi(double km) {
        if (km >= 1) {
            return mDistanceFormatterKM.format(km) + "公里";
        } else {
            return mDistanceFormatterM.format(km * 1000) + "米";
        }
    }

    public static String numberToChineseNumber(int n) {
        switch (n) {
            case 0:
                return "零";
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
        }
        return null;
    }

}
