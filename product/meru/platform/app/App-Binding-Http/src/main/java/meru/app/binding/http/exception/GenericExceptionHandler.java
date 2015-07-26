package meru.app.binding.http.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.BindingError;

public class GenericExceptionHandler extends ExceptionHandler {

    protected static final String DEFAULT_ERROR_MESSAGE = "Unable to fullfil the request";

    @Override
    public void handleException(Exception e,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        e.printStackTrace();
        BindingError bindingError = new BindingError(DEFAULT_ERROR_MESSAGE
                + ": " + e.getMessage());
        sendBindingError(bindingError,
                         request,
                         response);
    }

}
