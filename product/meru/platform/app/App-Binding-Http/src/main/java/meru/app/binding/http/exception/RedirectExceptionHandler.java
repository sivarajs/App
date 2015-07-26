package meru.app.binding.http.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.exception.RedirectException;

public class RedirectExceptionHandler extends ExceptionHandler {

    @Override
    public void handleException(Exception e,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.sendRedirect(((RedirectException) e).getAddress());
    }

    
    
}
