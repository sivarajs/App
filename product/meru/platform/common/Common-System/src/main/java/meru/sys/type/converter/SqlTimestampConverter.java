package meru.sys.type.converter;

import java.sql.Timestamp;

import meru.sys.SystemCalendar;

public class SqlTimestampConverter implements TypeConverter<Timestamp> {

    private SystemCalendar mSystemCalendar = SystemCalendar.getInstance();
    private static final String mDateFormat = "dd-MM-yyyy";

    public String serialize(Timestamp timestamp) {

        return mSystemCalendar.toDateString(timestamp.getTime(),
                                            mDateFormat);
    }

    public Timestamp deserialize(String calendarStr) {

        throw new UnsupportedOperationException();
    }

}