package meru.exception;

import meru.exception.bundle.EMResourceBundle;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final EMResourceBundle resourceBundle = new EMResourceBundle();

    private String code;

    public AppException(String code) {
        super(resourceBundle.getMessage(code));
        this.code = code;
    }

    public AppException(String code, Object... args) {
        super(resourceBundle.getMessage(code,
                                        args));
        this.code = code;
    }

    public AppException(Throwable cause, String code, Object... args) {
        super(resourceBundle.getMessage(code,
                                        args),
              cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    public boolean isRollbackOnly() {
      return true;
    }

}
