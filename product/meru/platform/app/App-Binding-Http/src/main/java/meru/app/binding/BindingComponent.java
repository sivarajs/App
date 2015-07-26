package meru.app.binding;

import java.io.IOException;
import java.util.List;

import meru.app.AppContext;
import meru.app.AppRequest;
import meru.app.config.AppConfig;
import meru.app.engine.AppEngine;
import meru.app.mediatype.MediaTypeFactory;
import meru.app.registry.EntityClassRegistry;
import meru.app.security.SecurityGuardChain;
import meru.app.session.SessionManager;
import meru.ui.faces.EntityDataProvider;

//import kaga.app.session.SessionManager;

public abstract class BindingComponent<T extends AppRequest> {

    protected AppConfig mAppConfig;
    protected AppContext mAppContext;
    protected AppEngine mAppEngine;
    private EntityClassRegistry mEntityClassRegistry;

    private EntityDataProvider mEntityDataProvider;
    protected SessionManager mSessionManager;

    public void setContext(AppConfig appConfig,
                           AppContext appContext,
                           AppEngine appEngine,
                           SessionManager sessionManager,
                           SecurityGuardChain securityGuardChain,
                           EntityClassRegistry entityClassRegistry) {
        mAppConfig = appConfig;
        mAppContext = appContext;
        mAppEngine = appEngine;
        mSessionManager = sessionManager;
        mEntityClassRegistry = entityClassRegistry;
        mEntityDataProvider = new EntityDataProviderImpl(appEngine,
                                                         entityClassRegistry);
    }

    public AppConfig getAppConfig() {
        return mAppConfig;
    }

    public AppContext getAppContext() {
        return mAppContext;
    }

    public SessionManager getSessionManager() {
        return mSessionManager;
    }

    public EntityDataProvider getEntityDataProvider() {
        return mEntityDataProvider;
    }

    protected Class<?> getEntityClass(String entityName) {
        return mEntityClassRegistry.getEntityClass(entityName);
    }

    protected static final String encodeEntity(String mediaType, Object entity) {
        return MediaTypeFactory.getMediaType(mediaType)
                               .encode(entity);

    }

    protected static final String encodeEntities(String mediaType,
                                                 List<?> entities) {
        return MediaTypeFactory.getMediaType(mediaType)
                               .encode(entities);

    }

    public final void get(T appRequest) throws IOException {
        getBindingSpecific(appRequest);
    }

    public final void post(T appRequest) throws IOException {
        postBindingSpecific(appRequest);
    }

    public final void delete(T appRequest) throws IOException {
        deleteBindingSpecific(appRequest);

    }

    protected void getBindingSpecific(T appRequest) throws IOException {

    }

    protected void postBindingSpecific(T appRequest) throws IOException {

    }

    protected void deleteBindingSpecific(T appRequest) throws IOException {

    }

    public abstract String getType();
}
