package meru.app.binding.http;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import meru.app.AppContext;
import meru.app.binding.BindingComponent;
import meru.app.config.AppConfig;
import meru.app.engine.AppEngine;
import meru.app.registry.EntityClassRegistry;
import meru.app.security.SecurityGuardChain;
import meru.app.session.SessionManager;
import meru.persistence.EntityQuery;
//import kaga.app.session.SessionManager;

public class HttpBindingComponent extends BindingComponent<HttpEntityRequest> {

  public static final String TYPE = "http";

  private HttpAppSecurity mAppSecurity;

  @Override
  public void setContext(AppConfig appConfig,
                         AppContext appContext,
                         AppEngine appEngine,
                         SessionManager sessionManager,
                         SecurityGuardChain securityGuardChain,
                         EntityClassRegistry entityClassRegistry) {
    super.setContext(appConfig,
                     appContext,
                     appEngine,
                     sessionManager,
                     securityGuardChain,
                     entityClassRegistry);

    mAppSecurity = new HttpAppSecurity(securityGuardChain);
  }

  public HttpAppSecurity getHttpAppSecurity() {
    return mAppSecurity;
  }

  @Override
  protected void getBindingSpecific(HttpEntityRequest entityRequest) throws IOException {

    Object id = entityRequest.getEntityId();
    String encodedStr = null;
    if (id == null) {
      EntityQuery<?> entityQuery = mAppEngine.createQuery(getEntityClass(entityRequest.getEntityName()));

      entityQuery.setQueryParameters(entityRequest.mRequest.getParameterMap());

      List<?> entities = mAppEngine.get(entityQuery);
      encodedStr = encodeEntities("application/json",
                                  entities);
    }
    else {
      Object entity = mAppEngine.get(getEntityClass(entityRequest.getEntityName()),
                                     id);
      encodedStr = encodeEntity("application/json",
                                entity);
    }

    HttpServletResponse response = entityRequest.getResponse();
    response.setContentType("application/json");
    response.getWriter()
            .print(encodedStr);
  }

  @Override
  protected void postBindingSpecific(HttpEntityRequest entityRequest) throws IOException {

    Object entity = mAppEngine.save(entityRequest.getEntity(getEntityClass(entityRequest.getEntityName())));

    String data = encodeEntity("application/json",
                               entity);

    HttpServletResponse response = entityRequest.getResponse();

    response.setContentType("application/json");

    response.getWriter()
            .print(data);

  }

  @Override
  protected void deleteBindingSpecific(HttpEntityRequest entityRequest) {
    Object id = entityRequest.getEntityId();
    if (id != null) {

      mAppEngine.remove(getEntityClass(entityRequest.getEntityName()),
                        id);
    }

  }

  public String getType() {
    return TYPE;
  }
}
