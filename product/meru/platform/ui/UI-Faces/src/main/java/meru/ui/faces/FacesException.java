package meru.ui.faces;

public class FacesException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected FacesException() {
        
    }
    
    public FacesException(String message) {
        super(message);
    }

    public FacesException(String message,
                          Throwable exception) {
        super(message,
              exception);
    }
}
