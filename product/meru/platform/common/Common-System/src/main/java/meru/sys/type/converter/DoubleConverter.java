package meru.sys.type.converter;


public class DoubleConverter implements TypeConverter<Double> {


  public String serialize(Double value) {

    return value.toString();
  }


  public Double deserialize(String value) {
    return Double.valueOf(value);
  }

}