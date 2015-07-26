package meru.app;

public abstract class BindingMessage {

  public String code;
  public String message;

  public BindingMessage(String message) {
    this.message = message;
  }

  public BindingMessage(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
  
  public abstract String getType();
}
