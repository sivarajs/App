package meru.app.binding.http.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.BindingError;
import meru.exception.AppException;

public class AppExceptionHandler extends ExceptionHandler {

    @Override
    public void handleException(Exception e,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        e.printStackTrace();
        AppException appEx = (AppException) e;
        BindingError bindingError = new BindingError(appEx.getCode(),
                                                     appEx.getMessage());
        sendBindingError(bindingError,
                         request,
                         response);
    }

}
