package pl.beaverlib.util;

import pl.beaverlib.BeaverLib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.*;

public abstract class DateManager {

    private final static BeaverLib plugin = BeaverLib.getInstance();
    private final String[] dayNames = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private final String[] monthNames = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public int getSecond() {
        return getCalendar().get(SECOND);
    }

    public int getMinute() {
        return getCalendar().get(MINUTE);
    }

    public int getHour() {
        return getCalendar().get(HOUR_OF_DAY);
    }

    public static int getDay() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfWeek() {
        return getCalendar().get(DAY_OF_WEEK);
    }

    public String getDayName() {
        return dayNames[getDayOfWeek() - 1];
    }

    public int getDaysOfMonth() {
        return getCalendar().getActualMaximum(DAY_OF_MONTH);
    }

    public static int getMonth() {
        return getCalendar().get(Calendar.MONTH)+1;
    }

    public static int getYear() {
        return getCalendar().get(Calendar.YEAR);
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public String getFormattedDate(String format) {
        format = format.replace("%Y", String.valueOf(getYear()));
        format = format.replace("%M", String.valueOf(getMonth()));
        format = format.replace("%D", String.valueOf(getDay()));
        format = format.replace("%h", String.valueOf(getHour()));
        format = format.replace("%m", String.valueOf(getMinute()));
        format = format.replace("%s", String.valueOf(getSecond()));
        return format;
    }

    public static String getDate(String separator) {
        String month = getMonth() + "";
        if(getMonth() < 10) {
            month = "0" + month;
        }
        return getDay() + separator + month + separator + getYear();
    }

    public static long calculateDays(String startDate, String endDate, String separator) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd" + separator + "MM" + separator + "yyyy", Locale.ENGLISH);
            Date dStart = sdf.parse(startDate);
            Date dEnd = sdf.parse(endDate);
            long diffInMillis = Math.abs(dEnd.getTime() - dStart.getTime());
            return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        } catch(ParseException e) {
            plugin.getLogger().severe("Cannot calculate days with dates: " + startDate + " " + endDate + "!");
            return 0;
        }
    }

}
