package meru.app;

public class BindingWarning extends BindingMessage {

  public BindingWarning(String message) {
    super(message);
  }

  public BindingWarning(String code, String message) {
    super(code,
          message);
  }

  @Override
  public String getType() {
    return "warning";
  }
}
