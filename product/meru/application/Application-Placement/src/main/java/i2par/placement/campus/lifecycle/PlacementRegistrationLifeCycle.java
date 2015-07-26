package i2par.placement.campus.lifecycle;

import i2par.campus.Campus;
import i2par.campus.placement.PlacementRegistration;
import i2par.campus.student.Student;
import i2par.campus.student.StudentRegistration;
import i2par.placement.ApplicationEntityLifeCycle;
import i2par.placement.PlacementConfig;

import java.util.List;

import meru.app.AppRequest;
import meru.exception.AppException;
import meru.persistence.AttributeOperator;
import meru.persistence.EntityQuery;
import meru.sys.IOSystem;
import meru.sys.lang.StringHelper;
import meru.template.TemplateEngine;
import meru.template.TemplateMultiData;
import app.domain.PropertyGroup;
import app.domain.security.Role;
import app.domain.security.User;

public class PlacementRegistrationLifeCycle extends
        ApplicationEntityLifeCycle<PlacementRegistration> {

    private static final String DOCUMENT_ID = "placemnt-registraion-id";

    private static final String DOCUMENT_ID_FORMAT = "${Campus.documentPrefix}RG${Number}";

    @Override
    public boolean preCreate(PlacementRegistration placementRegistration) {
        long campusId = placementRegistration.getCampusId();
        Campus campus = appEngine.get(Campus.class,
                                      campusId);

        long sequenceId = getSequenceId(campusId,
                                        DOCUMENT_ID);

        String documentId = getDocumentId(DOCUMENT_ID_FORMAT,
                                          campus,
                                          sequenceId);

        placementRegistration.setDocumentId(documentId);
        return true;
    }

    @Override
    public PlacementRegistration postGet(PlacementRegistration placementRegistration) {

        AppRequest appRequest = AppRequest.currentRequest();
        PropertyGroup state = null;
        if (appRequest.existsParameter("rollout")) {

            if (placementRegistration.getState()
                                     .getValue()
                                     .equals("Rolled Out")) {
                //throw new AppException("The registration has already been rolled out");
            }

            state = getPropertyGroup("registration-state",
                                     "Rolled Out");

            sendStudentRegistrationEmail(placementRegistration);

        } else if (appRequest.existsParameter("suspend")) {

            state = getPropertyGroup("registration-state",
                                     "Suspended");
        } else if (appRequest.existsParameter("resume")) {

            state = getPropertyGroup("registration-state",
                                     "Rolled Out");
        } else if (appRequest.existsParameter("extend")) {

            state = performStudentAction(placementRegistration,
                                         "Extended");
        } else if (appRequest.existsParameter("block")) {

            performStudentAction(placementRegistration,
                                 "Blocked");
        } else if (appRequest.existsParameter("unblock")) {

            performStudentAction(placementRegistration,
                                 "Open");
        } else if (appRequest.existsParameter("generateI2ParIds")) {

            if (canI2ParIdsGenerated(placementRegistration)) {

                EntityQuery<Student> entityQuery = appEngine.createQuery(Student.class);
                entityQuery.addQueryParameter("studentRegistration.placementRegistrationId",
                                              placementRegistration.getId());

                List<Student> studentList = appEngine.get(entityQuery);

                if (studentList == null || studentList.isEmpty()) {
                    throw new RuntimeException("No students found to generate IDs");
                }

                for (Student student : studentList) {
                    if (!StringHelper.isNullOrEmpty(student.getIparId()))
                        continue;

                    String i2parId = generateStudentCode(student,
                                                         placementRegistration);
                    student.setIparId(i2parId);
                    appEngine.save(student);

                }

            } else {
                String stateStr = (placementRegistration.getState() == null) ? "NO STATE"
                        : placementRegistration.getState()
                                               .getValue();

                throw new AppException("IDs can not be generated as the registration is in '"
                        + stateStr + "' state");

            }
        }

        if (state != null) {
            placementRegistration.setState(state);

            appEngine.save(placementRegistration);
        }

        return placementRegistration;
    }

    private boolean canI2ParIdsGenerated(PlacementRegistration placementRegistration) {

        String state = placementRegistration.getState()
                                            .getValue();

        return state == null || state.equals("New");

    }

    public String generateStudentCode(Student student,
                                      PlacementRegistration registration) {

        Campus campus = appEngine.get(Campus.class,
                                      student.getCampusId());

        String campusCode = campus.getCode();

        int year = registration.getPlacement()
                               .getYear()
                               .intValue();
        String yearLast2Digit = String.valueOf(year)
                                      .substring(2);

        long seq = getSequenceId(registration.getCampusId()
                                             .longValue(),
                                 "student-" + year);

        String seqStr = StringHelper.pad(String.valueOf(seq),
                                         '0',
                                         5);

        String studentId = campusCode + yearLast2Digit + seqStr;
        student.setIparId(studentId);

        return studentId;
    }

    private void sendStudentRegistrationEmail(PlacementRegistration placementRegistration) {

        EntityQuery<Student> entityQuery = appEngine.createQuery(Student.class);
        entityQuery.addQueryParameter("studentRegistration.placementRegistrationId",
                                      placementRegistration.getId());

        List<Student> studentList = appEngine.get(entityQuery);

        EntityQuery<Role> roleQuery = appEngine.createQuery(Role.class);
        roleQuery.addQueryParameter("name",
                                    "Student");

        Campus campus = appEngine.get(Campus.class,
                                      placementRegistration.getCampusId());

        String password = "welcome";
        for (Student student : studentList) {

            User user = new User();
            user.setName(student.getIparId());
            user.setPassword(password);

            TemplateMultiData templateData = new TemplateMultiData();
            templateData.addObject("student",
                                   student);
            templateData.addObject("user",
                                   user);
            templateData.addObject("placementRegistration",
                                   placementRegistration);
            templateData.addObject("campus",
                                   campus);

            templateData.addObject("appPort",
                                   appContext.getHttpsPort());

            String template = IOSystem.read(appContext.getInputStream(PlacementConfig.MAIL_TEMPLATE_DIR
                    + "RegistrationInvitationTemplate.html"));

            String message = TemplateEngine.getText(template,
                                                    templateData);
//            appEngine.save(createSendEmail(student.getEmail(),
//                                           "Placement Registration",
//                                           message));

            createUser(student.getIparId(),
                       113L,
                       String.valueOf(student.getId()));
        }
    }

    private PropertyGroup performStudentAction(PlacementRegistration placementRegistration,
                                               String registrationState) {

        String studentIds = AppRequest.currentRequest()
                                      .getSingleParameter("studentIds");

        if (studentIds == null) {
            return getPropertyGroup("registration-state",
                                    "Extended");
        } else {
            PropertyGroup regState = getPropertyGroup("student-registration-state",
                                                      registrationState);

            EntityQuery<Student> entityQuery = appEngine.createQuery(Student.class);
            entityQuery.addQueryParameter("id",
                                          AttributeOperator.IN,
                                          studentIds);

            List<Student> students = appEngine.get(entityQuery);

            for (Student student : students) {
                StudentRegistration stuRegistration = student.getStudentRegistration();
                stuRegistration.setState(regState);
                appEngine.save(stuRegistration);
            }
        }

        return null;
    }

}
