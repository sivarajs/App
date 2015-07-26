package i2par.placement.campus.lifecycle;

import i2par.campus.placement.interview.EmployerInterview;
import i2par.campus.placement.interview.StudentInterviewResult;
import i2par.placement.ApplicationEntityLifeCycle;
import i2par.placement.campus.InterviewRoundRunStatus;

import java.util.ArrayList;
import java.util.List;

import meru.persistence.EntityQuery;

public class InterviewRoundRunStatusLifeCycle extends ApplicationEntityLifeCycle<InterviewRoundRunStatus> {

    @Override
    public List<InterviewRoundRunStatus> preGet(EntityQuery<InterviewRoundRunStatus> runStatusQuerey) {

        Long empInterviewScheduleId = (Long) runStatusQuerey.getQueryParameterValue("employerInterviewId");

        EntityQuery<StudentInterviewResult> entityQuery = appEngine.createQuery(StudentInterviewResult.class);
        entityQuery.addQueryParameter("employerInterview.id",
                                      empInterviewScheduleId);

        List<StudentInterviewResult> stuInterviewResultList = appEngine.get(entityQuery);

        List<InterviewRoundRunStatus> runStatusList = new ArrayList<InterviewRoundRunStatus>(1);

        int studentsAppeared = 0;
        for (StudentInterviewResult result : stuInterviewResultList) {
            if (result.getSelectionState() != null && !"New".equals(result.getSelectionState()
                                                                          .getValue())) {
                studentsAppeared++;
            }
        }

        EmployerInterview empInterviewSchedule = appEngine.get(EmployerInterview.class,
                                                               empInterviewScheduleId);

        int totalStudents = stuInterviewResultList.size();

        String status = null;

        if (!"New".equals(empInterviewSchedule.getState()
                                              .getValue())) {
            status = "Published To Students";
        }
        else if (studentsAppeared == 0) {
            status = "Not Started";
        }
        else {
            status = "In Process";
        }

        InterviewRoundRunStatus runStatus = new InterviewRoundRunStatus(totalStudents,
                                                                        studentsAppeared,
                                                                        status);
        runStatusList.add(runStatus);

        return runStatusList;
    }

}
