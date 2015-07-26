package app;
public class ApplicationModule_CoreEntityClassRegistry extends meru.app.registry.EntityClassRegistry {
    protected void populateClassMap() {
        super.populateClassMap();
        addResourceClass("RegisteredNewUser", app.domain.account.RegisteredNewUser.class);
        addResourceClass("AppEntity", app.domain.AppEntity.class);
        addResourceClass("AppEntitySequence", app.domain.AppEntitySequence.class);
        addResourceClass("AppEntityState", app.domain.AppEntityState.class);
        addResourceClass("AppHierarchicalBaseEntity", app.domain.AppHierarchicalBaseEntity.class);
        addResourceClass("AppHierarchicalEntity", app.domain.AppHierarchicalEntity.class);
        addResourceClass("AuditableEntity", app.domain.AuditableEntity.class);
        addResourceClass("BusinessApplication", app.domain.BusinessApplication.class);
        addResourceClass("Alert", app.domain.comm.Alert.class);
        addResourceClass("Email", app.domain.comm.Email.class);
        addResourceClass("SendEmail", app.domain.comm.SendEmail.class);
        addResourceClass("SendSMS", app.domain.comm.SendSMS.class);
        addResourceClass("EntityFeature", app.domain.EntityFeature.class);
        addResourceClass("EntityFeatureValue", app.domain.EntityFeatureValue.class);
        addResourceClass("Property", app.domain.Property.class);
        addResourceClass("PropertyGroup", app.domain.PropertyGroup.class);
        addResourceClass("Schedule", app.domain.schedule.Schedule.class);
        addResourceClass("ScheduleTrigger", app.domain.schedule.ScheduleTrigger.class);
        addResourceClass("SequenceId", app.domain.SequenceId.class);
    }
}
