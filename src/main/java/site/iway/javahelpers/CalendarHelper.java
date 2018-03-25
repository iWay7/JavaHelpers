package site.iway.javahelpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarHelper {

    public static final long SECOND_TO_MILLIS = 1 * 1000;
    public static final long MINUTE_TO_MILLIS = 60 * SECOND_TO_MILLIS;
    public static final long HOUR_TO_MILLIS = 60 * MINUTE_TO_MILLIS;
    public static final long DAY_TO_MILLIS = 24 * HOUR_TO_MILLIS;

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_WINDOWS = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final TimeZone TIME_ZONE = TimeZone.getDefault();
    public static final Locale LOCALE = Locale.getDefault();

    public static Calendar now() {
        return Calendar.getInstance(TIME_ZONE, LOCALE);
    }

    public static Calendar nowDate() {
        Calendar calendar = now();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar fromMillis(long millis) {
        Calendar calendar = now();
        calendar.setTimeInMillis(millis);
        return calendar;
    }

    public static long toMillis(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    public static Calendar fromSeconds(long seconds) {
        return fromMillis(seconds * SECOND_TO_MILLIS);
    }

    public static long toSeconds(Calendar calendar) {
        return toMillis(calendar) / SECOND_TO_MILLIS;
    }

    public static Calendar fromMinutes(long minutes) {
        return fromMillis(minutes * MINUTE_TO_MILLIS);
    }

    public static long toMinutes(Calendar calendar) {
        return toMillis(calendar) / MINUTE_TO_MILLIS;
    }

    public static Calendar fromHours(long hours) {
        return fromMillis(hours * HOUR_TO_MILLIS);
    }

    public static long toHours(Calendar calendar) {
        return toMillis(calendar) / HOUR_TO_MILLIS;
    }

    public static Calendar fromDays(long days) {
        return fromMillis(days * DAY_TO_MILLIS);
    }

    public static long toDays(Calendar calendar) {
        return toMillis(calendar) / DAY_TO_MILLIS;
    }

    public static Calendar fromString(String s, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format, LOCALE);
            formatter.setTimeZone(TIME_ZONE);
            return fromMillis(formatter.parse(s).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static Calendar fromString(String s) {
        Calendar calendar = fromString(s, DATE_TIME_FORMAT);
        if (calendar == null) {
            calendar = fromString(s, DATE_TIME_FORMAT_WINDOWS);
        }
        return calendar;
    }

    public static String format(Calendar calendar, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, LOCALE);
        formatter.setTimeZone(TIME_ZONE);
        return formatter.format(calendar.getTime());
    }

    public static String formatDateTime(Calendar calendar) {
        return format(calendar, DATE_TIME_FORMAT);
    }

    public static String formatDate(Calendar calendar) {
        return format(calendar, DATE_FORMAT);
    }

    public static String formatTime(Calendar calendar) {
        return format(calendar, TIME_FORMAT);
    }

    public static String format(long timeInMillis, String format) {
        Calendar calendar = fromMillis(timeInMillis);
        return format(calendar, format);
    }

    public static String formatDateTime(long timeInMillis) {
        return format(timeInMillis, DATE_TIME_FORMAT);
    }

    public static String formatDate(long timeInMillis) {
        return format(timeInMillis, DATE_FORMAT);
    }

    public static String formatTime(long timeInMillis) {
        return format(timeInMillis, TIME_FORMAT);
    }

    public static int compare(Calendar lhs, Calendar rhs) {
        return lhs.compareTo(rhs);
    }

    public static int compareDate(Calendar lhs, Calendar rhs) {
        int lhsYear = lhs.get(Calendar.YEAR);
        int lhsMonth = lhs.get(Calendar.MONTH);
        int lhsDate = lhs.get(Calendar.DATE);
        int rhsYear = rhs.get(Calendar.YEAR);
        int rhsMonth = rhs.get(Calendar.MONTH);
        int rhsDate = rhs.get(Calendar.DATE);
        if (lhsYear != rhsYear)
            return lhsYear - rhsYear;
        if (lhsMonth != rhsMonth)
            return lhsMonth - rhsMonth;
        return lhsDate - rhsDate;
    }

    public static int compareTime(Calendar lhs, Calendar rhs) {
        int lhsHour = lhs.get(Calendar.HOUR_OF_DAY);
        int lhsMinute = lhs.get(Calendar.MINUTE);
        int lhsSecond = lhs.get(Calendar.SECOND);
        int rhsHour = rhs.get(Calendar.HOUR_OF_DAY);
        int rhsMinute = rhs.get(Calendar.MINUTE);
        int rhsSecond = rhs.get(Calendar.SECOND);
        if (lhsHour != rhsHour)
            return lhsHour - rhsHour;
        if (lhsMinute != rhsMinute)
            return lhsMinute - rhsMinute;
        return lhsSecond - rhsSecond;
    }

    public static int compareWithMillis(Calendar lhs, Calendar rhs) {
        int i = compareTime(lhs, rhs);
        if (i != 0) {
            int lhsMillis = lhs.get(Calendar.MILLISECOND);
            int rhsMillis = rhs.get(Calendar.MILLISECOND);
            return lhsMillis - rhsMillis;
        }
        return 0;
    }

}
