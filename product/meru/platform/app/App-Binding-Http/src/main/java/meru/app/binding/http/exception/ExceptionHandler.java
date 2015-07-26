package meru.app.binding.http.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.BindingMessage;

public abstract class ExceptionHandler {

  public final void handle(Exception e,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

    handleException(e,
                    request,
                    response);
  }

  public abstract void handleException(Exception e,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException;

  protected void sendBindingError(BindingMessage bindingMessage,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {

    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//    String accept = request.getHeader("accept");
    String message = bindingMessage.getMessage();;
/*    if (accept != null && accept.equals("application/json")) {

      message = MediaTypeFactory.getMediaType("application/json")
                                .encode(bindingMessage);

      response.setContentType("application/json");

    }
    else {
      message = bindingMessage.getMessage();
    }
*/
    response.getWriter()
            .print(message);

  }
}
