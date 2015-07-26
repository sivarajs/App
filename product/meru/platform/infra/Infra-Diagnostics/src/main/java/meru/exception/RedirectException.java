package meru.exception;

public class RedirectException extends AppException {

    private static final long serialVersionUID = 1L;

    private String address;

    public RedirectException(String address) {
        super(address);
        this.address = address;
    }

    public RedirectException(String address,
                             String message) {
        super(message);
        this.address = address;
    }

    public RedirectException(String address,
                             String message,
                             Throwable exception) {
        super(message,
              exception);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    
    @Override
    public boolean isRollbackOnly() {
      return false;
    }
}
