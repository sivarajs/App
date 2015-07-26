package meru.sys;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import meru.sys.config.Configurable;
import meru.sys.config.SystemConfig;

public class SystemCalendar implements Configurable {

    protected Locale mLocale;
    protected TimeZone mTimeZone;

    protected String mDefaultDateFormat = "dd-MM-yyyy";
    protected String mDefaultDateTimeFormat = "dd-MM-yyyy hh:mm:ss a";

    private static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");

    private static SystemCalendar INSTANCE = new SystemCalendar();

    protected SystemCalendar() {
        this(null,
             null);
    }

    public static final SystemCalendar getInstance() {
        return INSTANCE;
    }

    protected SystemCalendar(TimeZone timeZone,
                             Locale locale) {

        mLocale = (locale == null) ? Locale.getDefault() : locale;
        mTimeZone = (timeZone == null) ? TimeZone.getDefault() : timeZone;
    }

    public static SystemCalendar getSystemCalendar(TimeZone timeZone,
                                                   Locale locale) {
        return new SystemCalendar(timeZone,
                                  locale);
    }

    public final Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance(mTimeZone,
                                                 mLocale);
        return calendar;
    }

    public Calendar getUTCCalendar() {
        Calendar calendar = Calendar.getInstance(UTC_TIMEZONE,
                                                 mLocale);
        return calendar;
    }
    
    public final String toCalendarString(Calendar calendar,
                                         String format) {
        DateFormat dateFormat = getDateFormat(format);
        return dateFormat.format(calendar.getTime());
    }

    public final String toDateTimeString(long milliSecs) {
        return toDateTimeString(milliSecs,
                                mDefaultDateTimeFormat);
    }

    public final String toDateTimeString(long milliSecs,
                                         String format) {
        DateFormat dateFormat = getDateFormat(format);
        return dateFormat.format(new Date(milliSecs));
    }

    public final String toDateString(long milliSecs,
                                     String dateFormat) {
        return toDateTimeString(milliSecs,
                                dateFormat);
    }

    public final String toDateString(Date date,
                                     String format) {
        DateFormat dateFormat = getDateFormat(format);
        return dateFormat.format(date);
    }

    public final Calendar parseCalendar(String calendarStr,
                                        String format) {
        Calendar calendar = getCalendar();
        try {
            calendar.setTime(parseDate(calendarStr,
                                       format));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public final Date parseDate(String dateStr,
                                String format) {

        DateFormat dateFormat = getDateFormat(format);
        if (dateStr != null) {
            try {
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                throw new RuntimeException("Unable to parse date : " + dateStr,
                                           e);
            }
        }

        throw new RuntimeException("Unable to parse date : " + dateStr);
    }

    private final DateFormat getDateFormat(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format,
                                                           mLocale);
        dateFormat.setTimeZone(mTimeZone);

        return dateFormat;
    }

    public void configure(SystemConfig systemConfig) {

    }

}
