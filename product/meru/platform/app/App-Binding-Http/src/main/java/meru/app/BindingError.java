package meru.app;

public class BindingError extends BindingMessage {

  public BindingError(String message) {
    super(message);
  }

  public BindingError(String code, String message) {
    super(code,
          message);
  }

  @Override
  public String getType() {
    return "error";
  }

}
