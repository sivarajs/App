package i2par.placement.campus.lifecycle;

import i2par.campus.Campus;
import i2par.campus.Course;
import i2par.campus.placement.CoursePlacementItem;
import i2par.campus.placement.PlacementRegistration;
import i2par.campus.placement.Rfp;
import i2par.campus.placement.RfpResponse;
import i2par.campus.placement.RfpResponseItem;
import i2par.campus.student.Student;
import i2par.employer.Employer;
import i2par.placement.ApplicationEntityLifeCycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import meru.app.AppRequest;
import meru.persistence.EntityQuery;
import app.domain.PropertyGroup;

public class RfpLifeCycle extends ApplicationEntityLifeCycle<Rfp> {

    private static final String DOCUMENT_ID = "rfp-id";

    private static final String DOCUMENT_ID_FORMAT = "${Campus.documentPrefix}RF${Number}";

    @Override
    public boolean preCreate(Rfp rfp) {
        long campusId = rfp.getCampus()
                           .getId();
        Campus campus = appEngine.get(Campus.class,
                                      campusId);

        long sequenceId = getSequenceId(campusId,
                                        DOCUMENT_ID);

        String documentId = getDocumentId(DOCUMENT_ID_FORMAT,
                                          campus,
                                          sequenceId);

        rfp.setDocumentId(documentId);

        if (rfp.getPlacementRegistration() != null) {

            PlacementRegistration placementRegistration = appEngine.get(PlacementRegistration.class,
                                                                        rfp.getPlacementRegistration()
                                                                           .getId());
            rfp.setPlacement(placementRegistration.getPlacement());

        }

        return true;
    }

    @Override
    public Rfp postCreate(Rfp rfp) {

        if (rfp.getPlacementRegistration() != null) {

            List<CoursePlacementItem> coursePlacementItems = getCoursePlacementItems(rfp,
                                                                                     "R");

            Float expectedPay = rfp.getExpectedPay();
            int studentCount = 0;
            for (CoursePlacementItem item : coursePlacementItems) {
                item.setExpectedPay(expectedPay);
                studentCount += item.getStudentCount();
                appEngine.save(item);
            }

            rfp.setParticipatedStudents(studentCount);
            appEngine.save(rfp);

        }

        return rfp;
    }

    @Override
    public Rfp postGet(Rfp rfp) {

        AppRequest appRequest = AppRequest.currentRequest();
        PropertyGroup state = null;
        if (appRequest.existsParameter("dispatch")) {

            if (rfp.getState()
                   .getValue()
                   .equals("New")) {

                state = getPropertyGroup("rfp-state",
                                         "Dispatched");
                rfp.setState(state);
                appEngine.save(rfp);
                createRfpResponses(rfp);
            }
        }

        return rfp;
    }

    private List<CoursePlacementItem> getCoursePlacementItems(Rfp rfp,
                                                              String type) {

        long placementRegistrationId = rfp.getPlacementRegistration()
                                          .getId();
        long ownerId = rfp.getId();

        EntityQuery<Student> entityQuery = appEngine.createQuery(Student.class);
        entityQuery.addQueryParameter("studentRegistration.placementRegistrationId",
                                      placementRegistrationId);
        entityQuery.addQueryParameter("studentRegistration.state.value","Registered");
        
        List<Student> students = appEngine.get(entityQuery);

        if (students == null || students.isEmpty()) {
            return new ArrayList<CoursePlacementItem>(0);
        }

        List<CoursePlacementItem> coursePlacementItems = new ArrayList<CoursePlacementItem>(10);

        Map<String, Map<String, CoursePlacementItem>> rfpStudentMap = new HashMap<String, Map<String, CoursePlacementItem>>(10);

        for (Student student : students) {

            // if (!"Registered".equals(student.getStudentRegistration()
            // .getState()
            // .getValue())) {
            // continue;
            // }

            Course course = student.getCourse1();

            Map<String, CoursePlacementItem> studentMap = rfpStudentMap.get(course.getDegree()
                                                                                  .getName());

            if (studentMap == null) {
                studentMap = new HashMap<String, CoursePlacementItem>(10);
                rfpStudentMap.put(course.getDegree()
                                        .getName(),
                                  studentMap);
            }

            CoursePlacementItem parItem = (CoursePlacementItem) studentMap.get(course.getDiscipline()
                                                                                     .getName());

            if (parItem == null) {
                parItem = new CoursePlacementItem();
                parItem.setOwnerId(ownerId);
                parItem.setType(type);
                parItem.setCourse(course);
                studentMap.put(course.getDiscipline()
                                     .getName(),
                               parItem);

                coursePlacementItems.add(parItem);
            }

            Integer count = parItem.getStudentCount();
            parItem.setStudentCount((count == null) ? 1 : count + 1);
        }

        return coursePlacementItems;
    }

    private void createRfpResponses(Rfp rfp) {

        PropertyGroup state = getPropertyGroup("rfp-response-state",
                                               "New");

        EntityQuery<Employer> entityQuery = appEngine.createQuery(Employer.class);
        List<Employer> employers = appEngine.get(entityQuery);

        for (Employer employer : employers) {
            createRfpResponse(rfp,
                              employer,
                              state);
        }
    }

    private void createRfpResponse(Rfp rfp,
                                   Employer employer,
                                   PropertyGroup state) {

        RfpResponse rfpResponse = new RfpResponse();
        rfpResponse.setCampusId(rfp.getCampus()
                                   .getId());
        rfpResponse.setRfp(rfp);
        rfpResponse.setEmployer(employer);
        rfpResponse.setState(state);

        appEngine.save(rfpResponse);

        if (rfp.getCoursePlacementItems() != null) {

            for (CoursePlacementItem rfpItem : rfp.getCoursePlacementItems()) {
                RfpResponseItem rfpItemRes = new RfpResponseItem();
                rfpItemRes.setRfpResponseId(rfpResponse.getId());
                rfpItemRes.setCoursePlacementItem(rfpItem);
                appEngine.save(rfpItemRes);
            }
        }

    }

}
