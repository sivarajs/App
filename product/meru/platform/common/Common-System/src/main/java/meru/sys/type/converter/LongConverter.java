package meru.sys.type.converter;


public class LongConverter implements TypeConverter<Long> {


  public String serialize(Long value) {

    return value.toString();
  }


  public Long deserialize(String value) {
    return Long.valueOf(value);
  }

}