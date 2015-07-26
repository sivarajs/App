package meru.application.module.core.lifecycle;

import meru.app.engine.entity.AbstractEntityLifeCycle;
import app.domain.AppEntity;


public class AppEntityLifeCycle extends AbstractEntityLifeCycle<AppEntity> {

    private Long mAppId;

    @Override
    public void init() {
        String appId = appConfig.getProperty("app.id");
        if (appId != null) {
            mAppId = Long.parseLong(appId);

        }
    }

    private void setAppId(Object resource) {
        if (mAppId != null && resource instanceof AppEntity) {
            ((AppEntity) resource).setAppId(mAppId);
        }
        ((AppEntity) resource).setClientId(-1L);
    }

    @Override
    public boolean preCreate(AppEntity appEntity) {
        setAppId(appEntity);
        return true;
    }

    @Override
    public boolean preModify(AppEntity appEntity) {
        setAppId(appEntity);
        return true;

    }

}
