package i2par.placement.campus.lifecycle;

import i2par.campus.placement.interview.EmployerInterview;
import i2par.placement.ApplicationEntityLifeCycle;
import meru.app.AppRequest;
import app.domain.PropertyGroup;

public class EmployerInterviewLifeCycle extends ApplicationEntityLifeCycle<EmployerInterview> {

    @Override
    public EmployerInterview postGet(EmployerInterview employerInterview) {

        AppRequest appRequest = AppRequest.currentRequest();
        if (appRequest.existsParameter("publish")) {

            PropertyGroup state = getPropertyGroup("employer-interview-schedule-state",
                                     "Published To Students");
            
            employerInterview.setState(state);
            appEngine.save(employerInterview);
        }
     
        return employerInterview;
    }  
}
