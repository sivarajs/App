package meru.comm.mail;

public class DeliveryFailedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DeliveryFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public DeliveryFailedException(String message) {
    super(message);
  }
}