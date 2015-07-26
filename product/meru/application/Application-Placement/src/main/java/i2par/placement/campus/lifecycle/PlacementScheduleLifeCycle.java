package i2par.placement.campus.lifecycle;

import i2par.campus.Campus;
import i2par.campus.placement.PlacementRegistration;
import i2par.campus.placement.Rfp;
import i2par.campus.placement.RfpResponse;
import i2par.campus.placement.interview.EmployerInterview;
import i2par.campus.placement.interview.InterviewRound;
import i2par.campus.placement.interview.StudentInterview;
import i2par.campus.placement.interview.StudentInterviewResult;
import i2par.campus.placement.schedule.EmployerSchedule;
import i2par.campus.placement.schedule.PlacementSchedule;
import i2par.campus.student.Student;
import i2par.employer.Employer;
import i2par.employer.EmployerContact;
import i2par.placement.ApplicationEntityLifeCycle;

import java.util.List;

import meru.app.AppRequest;
import meru.exception.AppException;
import meru.persistence.EntityQuery;
import app.domain.PropertyGroup;

public class PlacementScheduleLifeCycle extends ApplicationEntityLifeCycle<PlacementSchedule> {

    private static final String DOCUMENT_ID = "placement-schedule-id";

    private static final String DOCUMENT_ID_FORMAT = "${Campus.documentPrefix}SH${Number}";

    @Override
    public PlacementSchedule postGet(PlacementSchedule placementSchedule) {

        AppRequest appRequest = AppRequest.currentRequest();
        PropertyGroup state = null;
        if (appRequest.existsParameter("dispatch")) {

            state = getPropertyGroup("placement-schedule-state",
                                     "Dispatched");

            PlacementRegistration placementRegistration = placementSchedule.getPlacementRegistration();

            EntityQuery<Student> studentQuery = appEngine.createQuery(Student.class);
            studentQuery.addQueryParameter("studentRegistration.placementRegistrationId",
                                           placementRegistration.getId());
            studentQuery.addQueryParameter("studentRegistration.state.value",
                                           "Registered");
            List<Student> studentList = appEngine.get(studentQuery);

            EntityQuery<EmployerSchedule> entityQuery = appEngine.createQuery(EmployerSchedule.class);
            entityQuery.addQueryParameter("placementSchedule.id",
                                          placementSchedule.getId());

            List<EmployerSchedule> employerScheduleList = appEngine.get(entityQuery);
            for (EmployerSchedule employerSchedule : employerScheduleList) {

                Employer employer = employerSchedule.getEmployer();

                EntityQuery<EmployerContact> empContactQuery = appEngine.createQuery(EmployerContact.class);
                empContactQuery.addQueryParameter("employerId",
                                                  employer.getId());
                empContactQuery.addQueryParameter("type.value",
                                                  "HR");

                EmployerContact employerContact = appEngine.getFirst(empContactQuery);

                if (employerContact == null) {
                    continue;
                }

               /* String email = employerContact.getEmail();

                String domain = email.substring(email.indexOf("@") + 1,
                                                email.indexOf("."));

                String userName = domain.toUpperCase() + "_PA";

                EntityQuery<User> userQuery = appEngine.createQuery(User.class);
                userQuery.addQueryParameter("name",
                                            userName);

                User user = appEngine.getFirst(userQuery);
                if (user == null) {
                    user = createUser(userName,
                                      "HR PA",
                                      employer.getId());
                }
*/
                // createMail(employerSchedule,
                // employer,
                // user,
                // email);

                createStudentInterview(employerSchedule,
                                       studentList);

            }

        }
        else if (appRequest.existsParameter("abort")) {
            state = getPropertyGroup("placement-schedule-state",
                                     "Dispatched");
        }

        if (state != null) {
            placementSchedule.setState(state);
            appEngine.save(placementSchedule);
        }

        return placementSchedule;
    }

    @Override
    public boolean preCreate(PlacementSchedule placementSchedule) {
        long campusId = placementSchedule.getCampus()
                                         .getId();
        Campus campus = appEngine.get(Campus.class,
                                      campusId);

        long sequenceId = getSequenceId(campusId,
                                        DOCUMENT_ID);

        String documentId = getDocumentId(DOCUMENT_ID_FORMAT,
                                          campus,
                                          sequenceId);

        placementSchedule.setDocumentId(documentId);

        if (placementSchedule.getRfp() != null) {
            PlacementRegistration placementRegistration = placementSchedule.getRfp()
                                                                           .getPlacementRegistration();

            if (placementRegistration == null) {
                throw new AppException("Can not schedule without Registration");
            }

            placementSchedule.setPlacementRegistration(placementRegistration);

        }

        return true;
    }

    @Override
    public PlacementSchedule postCreate(PlacementSchedule placementSchedule) {

        Rfp rfp = placementSchedule.getRfp();

        if (rfp != null) {

            long rfpId = rfp.getId();
            EntityQuery<RfpResponse> entityQuery = createEntityQuery(RfpResponse.class,
                                                                     "rfp.id",
                                                                     rfpId);
            List<RfpResponse> rfpResponses = appEngine.get(entityQuery);

            for (RfpResponse rfpResponse : rfpResponses) {

                if (rfpResponse.getState()
                               .getValue()
                               .equals("Accepted")) {

                    EmployerSchedule empSchedule = new EmployerSchedule();
                    empSchedule.setCampusId(placementSchedule.getCampus()
                                                             .getId());
                    empSchedule.setEmployer(rfpResponse.getEmployer());
                    empSchedule.setPlacementSchedule(placementSchedule);
                    empSchedule.setRfpResponse(rfpResponse);
                    empSchedule.setState(getPropertyGroup("employer-schedule-state", "New"));
                    appEngine.save(empSchedule);

                    createEmployerInterviewSchedule(empSchedule,
                                                    rfpResponse);
                }
            }

        }

        return placementSchedule;
    }

    private void createEmployerInterviewSchedule(EmployerSchedule empSchedule,
                                                 RfpResponse rfpResponse) {

        EntityQuery<InterviewRound> entityQuery = createEntityQuery(InterviewRound.class,
                                                                    "rfpResponseId",
                                                                    rfpResponse.getId());

        List<InterviewRound> interviewRounds = appEngine.get(entityQuery);

        for (InterviewRound interviewRound : interviewRounds) {

            EmployerInterview interviewSchedule = new EmployerInterview();
            interviewSchedule.setInterviewType(interviewRound.getInterviewType());
            interviewSchedule.setDescription(interviewRound.getDescription());
            interviewSchedule.setDate(interviewRound.getExpectedDate());
            interviewSchedule.setTime("10 AM");
            interviewSchedule.setDuration(interviewRound.getDuration());
            interviewSchedule.setEmployerScheduleId(empSchedule.getId());
            interviewSchedule.setState(getPropertyGroup("employer-interview-state", "New"));
            appEngine.save(interviewSchedule);

        }

    }

   /* private void createMail(EmployerSchedule employerSchedule,
                            Employer employer,
                            User user,
                            String email) {
        TemplateMultiData templateData = new TemplateMultiData();
        templateData.addObject("emloyerSchedule",
                               employerSchedule);
        templateData.addObject("employer",
                               employer);
        templateData.addObject("user",
                               user);

        String template = IOSystem.read(appContext.getInputStream(PlacementConfig.MAIL_TEMPLATE_DIR + "PlacementScheduleMailTemplate.html"));

        String message = TemplateEngine.getText(template,
                                                templateData);
        appEngine.save(createSendEmail(email,
                                       "Placement Schedule",
                                       message));
    }*/

    private void createStudentInterview(EmployerSchedule employerSchedule,
                                        List<Student> studentList) {

        List<EmployerInterview> interviewSchedules = employerSchedule.getEmployerInterview();

        for (Student student : studentList) {

            StudentInterview stuInterview = new StudentInterview();
            stuInterview.setCampusId(employerSchedule.getCampusId());
            stuInterview.setEmployerId(employerSchedule.getEmployer()
                                                       .getId());
            stuInterview.setStudent(student);
            stuInterview.setEmployerScheduleId(employerSchedule.getId());
            stuInterview.setCourseId(student.getCourse1()
                                            .getId());

            appEngine.save(stuInterview);

            for (EmployerInterview interviewSchedule : interviewSchedules) {
                StudentInterviewResult interviewResult = new StudentInterviewResult();
                interviewResult.setEmployerInterview(interviewSchedule);
                interviewResult.setStudentInterview(stuInterview);
                appEngine.save(interviewResult);
            }

        }
    }
}
