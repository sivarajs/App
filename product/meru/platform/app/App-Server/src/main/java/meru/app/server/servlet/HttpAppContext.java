package meru.app.server.servlet;

import java.io.File;
import java.io.InputStream;

import javax.servlet.ServletContext;

import meru.app.AppContext;
import meru.app.AppPort;

public class HttpAppContext extends AppContext {

  private ServletContext mServletContext;
  private AppPort mHttpPort;
  private AppPort mHttpsPort;

  public HttpAppContext(ServletContext servletContext) {
    super(new File(servletContext.getRealPath("/")),
          servletContext.getContextPath());
    mServletContext = servletContext;
  }

  @Override
  public final InputStream getInputStream(String relativePath) {

    return this.mServletContext.getResourceAsStream(relativePath);
  }

  @Override
  public AppPort getHttpPort() {
    return mHttpPort;
  }

  @Override
  public AppPort getHttpsPort() {
    return mHttpsPort;
  }

  public void setAppPorts(AppPort httpPort,
                          AppPort httpsPort) {
    mHttpPort = httpPort;
    mHttpsPort = httpsPort;
  }
}
