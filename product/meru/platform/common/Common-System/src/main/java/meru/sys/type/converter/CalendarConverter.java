package meru.sys.type.converter;

import java.util.Calendar;

import meru.sys.SystemCalendar;

public class CalendarConverter implements TypeConverter<Calendar> {

    private SystemCalendar mSystemCalendar = SystemCalendar.getInstance();
    private static final String mDateTimeFormat = "dd-MM-yyyy hh:mm:ss a";

    public String serialize(Calendar calendar) {

        return mSystemCalendar.toCalendarString(calendar,
                                        mDateTimeFormat);
    }

    public Calendar deserialize(String calendarStr) {

        return mSystemCalendar.parseCalendar(calendarStr,
                                             mDateTimeFormat);
    }

}