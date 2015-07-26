package meru.app.binding.http.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.BindingWarning;
import meru.exception.AppEntityWarning;

public class AppEntityWarningHandler extends ExceptionHandler {

  @Override
  public void handleException(Exception e,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
    //e.printStackTrace();
    AppEntityWarning appWarn = (AppEntityWarning) e;
    BindingWarning bindingWarning = new BindingWarning(appWarn.getCode(),
                                                 appWarn.getMessage());
    sendBindingError(bindingWarning,
                     request,
                     response);
  }

}
