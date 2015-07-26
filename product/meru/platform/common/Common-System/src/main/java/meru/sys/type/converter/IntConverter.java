package meru.sys.type.converter;


public class IntConverter implements TypeConverter<Integer> {


  public String serialize(Integer value) {

    return value.toString();
  }


  public Integer deserialize(String intStr) {
    return Integer.valueOf(intStr);
  }

}