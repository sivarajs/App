package meru.sys.type.converter;

import java.text.DecimalFormat;


public class FloatConverter implements TypeConverter<Float> {

  public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

  public String serialize(Float value) {
    return DECIMAL_FORMAT.format(value);
  }


  public Float deserialize(String value) {
    return Float.valueOf(value);
  }

}