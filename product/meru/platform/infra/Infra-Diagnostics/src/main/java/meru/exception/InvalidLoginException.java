package meru.exception;

public class InvalidLoginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidLoginException(String message) {
        super(message);
    }
}
