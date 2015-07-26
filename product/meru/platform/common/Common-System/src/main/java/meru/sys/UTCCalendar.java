package meru.sys;

import java.util.Locale;
import java.util.TimeZone;

public class UTCCalendar extends SystemCalendar {

    private UTCCalendar(Locale locale) {

        super(TimeZone.getTimeZone("UTC"),
              locale);
    }

    public static UTCCalendar getUTCCalendar(Locale locale) {
        return new UTCCalendar(locale);
    }
}
