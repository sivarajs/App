package meru.application.lifecycle;

import app.domain.AppEntityState;
import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.exception.AppException;
import meru.persistence.EntityQuery;

public abstract class BusinessAppEntityLifeCycle<T> extends AbstractEntityLifeCycle<T> {

    protected AppEntityState getAppEntityState(String entityName, int code) {
        EntityQuery<AppEntityState> entityQuery = appEngine.createQuery(AppEntityState.class);
        entityQuery.addQueryParameter("entity",
                                      entityName);

        entityQuery.addQueryParameter("code",
                                      code);

        AppEntityState appState = appEngine.getFirst(entityQuery);
        if (appState == null) {
            throw new AppException("###",
                                   "Unknown AppEntityState [" + entityName
                                           + ", " + code + "]");
        }

        return appState;

    }

}
