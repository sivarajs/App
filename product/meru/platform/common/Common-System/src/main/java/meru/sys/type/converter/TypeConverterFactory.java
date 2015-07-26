package meru.sys.type.converter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class TypeConverterFactory {

  private static final Map<Class<?>, TypeConverter<?>> mTypeConverters = new HashMap<Class<?>, TypeConverter<?>>();


  static {
    mTypeConverters.put(Boolean.class, new BooleanConverter());
    mTypeConverters.put(boolean.class, new BooleanConverter());
    mTypeConverters.put(int.class, new IntConverter());
    mTypeConverters.put(Integer.class, new IntConverter());
    mTypeConverters.put(long.class, new LongConverter());
    mTypeConverters.put(Long.class, new LongConverter());
    mTypeConverters.put(float.class, new FloatConverter());
    mTypeConverters.put(Float.class, new FloatConverter());
    mTypeConverters.put(double.class, new DoubleConverter());
    mTypeConverters.put(Double.class, new DoubleConverter());
    mTypeConverters.put(String.class, new StringConverter());
    
    mTypeConverters.put(Date.class, new DateConverter());
    mTypeConverters.put(Calendar.class, new CalendarConverter());
    mTypeConverters.put(GregorianCalendar.class, new CalendarConverter());
    mTypeConverters.put(Timestamp.class, new SqlTimestampConverter());
  }
  
  @SuppressWarnings("unchecked")
  public static <T> TypeConverter<T> getTypeConverter(Class<T> claz) {
    
    return (TypeConverter<T>) mTypeConverters.get(claz);
    
  }
}
