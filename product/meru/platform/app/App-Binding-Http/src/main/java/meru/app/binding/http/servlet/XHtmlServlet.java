package meru.app.binding.http.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import meru.app.binding.http.HttpAppRequest;
import meru.ui.faces.renderer.XHtmlRenderer;
import meru.ui.faces.renderer.html.HtmlFileView;
import meru.ui.faces.var.ApplicationVariable;
import meru.ui.faces.var.QueryVariable;
import meru.ui.faces.var.SessionVariable;

public class XHtmlServlet extends AppServlet {
  private static final long serialVersionUID = 1L;

  private ServletContext mServletContext;
  private ApplicationVariable mApplicationVariable;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    mServletContext = config.getServletContext();

    Map<String, Object> varMap = new HashMap<String, Object>(1);
    varMap.put("root",
               mAppContext.getApplicationRoot());
    mApplicationVariable = new ApplicationVariable(varMap);

  }

  @Override
  protected void post(HttpAppRequest appRequest) throws ServletException,
                                                IOException {
    get(appRequest);
  }

  @Override
  protected void get(HttpAppRequest appRequest) throws ServletException,
                                               IOException {

    HtmlFileView htmlView = new HtmlFileView(appRequest.getRequest()
                                                       .getServletPath());

    get(appRequest,
        htmlView);
  }

  protected final void get(HttpAppRequest appRequest,
                           HtmlFileView htmlView) throws ServletException,
                                                 IOException {

    System.out.println("##### Received URL ==> " + mApplicationVariable.getValue("root")+"   "+htmlView.getURL());
    appRequest.getResponse()
              .setContentType("text/html");

    StringWriter strWriter = new StringWriter();

   // strWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");

    QueryVariable queryVariable = new QueryVariable(appRequest.getRequest()
                                                              .getParameterMap());

    htmlView.setVariable("query",
                         queryVariable);
    htmlView.setVariable("session",
                         new SessionVariable(appRequest.getRequest()));
    htmlView.setVariable("application",
                         mApplicationVariable);
    render(appRequest,
           strWriter,
           htmlView);
    appRequest.getResponse()
              .getWriter()
              .write(strWriter.toString());

  }

  protected final void render(HttpAppRequest appRequest,
                              Writer writer,
                              HtmlFileView htmlView) throws IOException {
    XHtmlRenderer htmlRenderer = new XHtmlRenderer(mServletContext,
                                                   mBindingComponent.getEntityDataProvider());
    htmlRenderer.writeHtml(writer,
                           htmlView);
  }
}
