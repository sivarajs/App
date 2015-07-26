package meru.sys;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SystemDate {

    protected Locale locale;
    protected TimeZone timeZone;

    private static SystemDate INSTANCE;

    public SystemDate(TimeZone timeZone,
                      Locale locale) {
        this.locale = locale;
        this.timeZone = timeZone;
        INSTANCE = this;
    }

    public static SystemDate getInstance() {
        return INSTANCE;
    }

    public Date currentDate() {
        Calendar calendar = Calendar.getInstance(timeZone,
                                                 locale);
        resetHours(calendar);
        return calendar.getTime();
    }
    
    private void resetHours(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY,
                     0);
        calendar.set(Calendar.MINUTE,
                     0);
        calendar.set(Calendar.SECOND,
                     0);
        calendar.set(Calendar.MILLISECOND,
                     0);
    }

/*    public Date toSystemDate(Date date) {

        Calendar calendar = Calendar.getInstance(timeZone,
                                                 locale);
        calendar.setTimeZone(timeZone);
        calendar.setTime(date);
        resetHours(calendar);
        return calendar.getTime();
    }

*/    public final String toString(Date date,
                                 String format) {
        
        DateFormat dateFormat = getDateFormat(format);
        return dateFormat.format(date);
    }

    private DateFormat getDateFormat(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format,
                                                           locale);
        return dateFormat;
    }

    public final Date parse(String dateStr,
                            String format) {

        DateFormat dateFormat = getDateFormat(format);
        if (dateStr != null) {
            try {
                Date date = dateFormat.parse(dateStr);
                return date;
            } catch (ParseException e) {
                throw new RuntimeException("Unable to parse date : " + dateStr,
                                           e);
            }
        }

        throw new RuntimeException("Unable to parse date : " + dateStr);
    }

}
