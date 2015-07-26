package meru.application.module.core.lifecycle;

import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.sys.SystemCalendar;
import app.domain.AuditableEntity;

public class AuditableEntityLifeCycle extends
        AbstractEntityLifeCycle<AuditableEntity> {

    private SystemCalendar mSystemCalendar;

    @Override
    public void init() {
        mSystemCalendar = SystemCalendar.getInstance();
    }

    @Override
    public boolean preCreate(AuditableEntity auditableEntity) {
        auditableEntity.setCreatedBy("engine");
        auditableEntity.setCreatedTime(mSystemCalendar.getUTCCalendar());
        return true;
    }

    @Override
    public boolean preModify(AuditableEntity auditableEntity) {
        auditableEntity.setModifiedBy("engine");
        auditableEntity.setModifiedTime(mSystemCalendar.getUTCCalendar());
        return true;

    }

}
