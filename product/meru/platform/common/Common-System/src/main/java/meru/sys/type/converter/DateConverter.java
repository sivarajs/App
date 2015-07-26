package meru.sys.type.converter;

import java.util.Date;

import meru.sys.SystemDate;

public class DateConverter implements TypeConverter<Date> {

    private SystemDate mSystemDate = SystemDate.getInstance();
    private static final String mDateFormat = "dd-MM-yyyy";

    public String serialize(Date date) {

        return mSystemDate.toString(date,
                                    mDateFormat);
    }

    public Date deserialize(String dateStr) {

        return mSystemDate.parse(dateStr,
                                 mDateFormat);
    }

}