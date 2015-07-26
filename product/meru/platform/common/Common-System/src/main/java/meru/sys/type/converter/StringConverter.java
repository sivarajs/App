package meru.sys.type.converter;


public class StringConverter implements TypeConverter<String> {


  public String serialize(String value) {

    return value;
  }


  public String deserialize(String value) {
    return value;
  }

}