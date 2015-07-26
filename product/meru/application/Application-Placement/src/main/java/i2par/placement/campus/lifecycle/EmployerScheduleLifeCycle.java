package i2par.placement.campus.lifecycle;

import i2par.campus.placement.schedule.EmployerSchedule;
import i2par.placement.ApplicationEntityLifeCycle;
import meru.app.AppRequest;
import app.domain.PropertyGroup;

public class EmployerScheduleLifeCycle extends
        ApplicationEntityLifeCycle<EmployerSchedule> {

    @Override
    public EmployerSchedule postGet(EmployerSchedule employerSchedule) {

        AppRequest appRequest = AppRequest.currentRequest();
        String stateValue = null;
        if (appRequest.existsParameter("publishCampus")) {
            stateValue = "Published To Campus";
        }
        else if (appRequest.existsParameter("publishStudents")) {
            stateValue = "Published To Students";
        }

        if (stateValue != null) {
            PropertyGroup state = getPropertyGroup("employer-schedule-state",
                                                   stateValue);
            employerSchedule.setDispatchState(state);
            appEngine.save(employerSchedule);
        }

        return employerSchedule;
    }
}
