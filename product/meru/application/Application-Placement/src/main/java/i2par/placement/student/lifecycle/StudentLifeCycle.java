package i2par.placement.student.lifecycle;

import i2par.campus.student.Student;
import i2par.placement.ApplicationEntityLifeCycle;
import meru.app.AppRequest;
import app.domain.PropertyGroup;

public class StudentLifeCycle extends ApplicationEntityLifeCycle<Student> {

    @Override
    public Student postGet(Student student) {

        AppRequest appRequest = AppRequest.currentRequest();
        PropertyGroup state = null;

        if (appRequest.existsParameter("register")) {
            state = getPropertyGroup("student-registration-state",
                                     "Registered");
        }
        else if (appRequest.existsParameter("reject")) {
            String reason = appRequest.getSingleParameter("reason");
            if (reason != null) {
                student.getStudentRegistration()
                       .setStateInfo(reason);
            }
            state = getPropertyGroup("student-registration-state",
                                     "Rejected");
        }

        if (state != null) {
            student.getStudentRegistration()
                   .setState(state);
            appEngine.save(student.getStudentRegistration());
            
        }

        
        return null;
    }

}
