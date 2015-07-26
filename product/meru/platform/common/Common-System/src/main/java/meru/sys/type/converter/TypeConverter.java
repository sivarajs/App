package meru.sys.type.converter;

public interface TypeConverter<T> {

  public String serialize(T object);
  public T deserialize(String objectStr);
}
