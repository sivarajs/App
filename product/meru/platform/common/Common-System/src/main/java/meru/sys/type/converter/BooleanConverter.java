package meru.sys.type.converter;


public class BooleanConverter implements TypeConverter<Boolean> {


  public String serialize(Boolean bool) {

    return bool.toString();
  }


  public Boolean deserialize(String boolStr) {
    return Boolean.valueOf(boolStr);
  }

}