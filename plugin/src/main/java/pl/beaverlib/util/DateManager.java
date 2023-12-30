package pl.beaverlib.util;

import pl.beaverlib.BeaverLib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public abstract class DateManager {

    private final static BeaverLib plugin = BeaverLib.getInstance();

    public static String getDate(String separator) {
        String month = getMonth() + "";
        if(getMonth() < 10) {
            month = "0" + month;
        }
        return getDay() + separator + month + separator + getYear();
    }

    public static int getDay() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
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
