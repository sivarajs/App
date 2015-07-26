package meru.app.engine;

import meru.app.AppContext;
import meru.app.config.AppConfig;
import meru.app.service.ServiceManager;
import meru.persistence.EntityQuery;

public abstract class AppEngine implements EntityManagable {

    protected AppConfig mAppConfig;
    protected AppContext mAppContext;

    protected ServiceManager mServiceManager;

    public AppEngine(AppConfig appConfig,
                     AppContext appContext) throws Exception {

        mAppConfig = appConfig;
        mAppContext = appContext;
    }

    public abstract long nextSequenceNumber(String sequenceName);

    public abstract <T> EntityQuery<T> createQuery(Class<T> entityClass);

    public void closeSession() {

    }
}
