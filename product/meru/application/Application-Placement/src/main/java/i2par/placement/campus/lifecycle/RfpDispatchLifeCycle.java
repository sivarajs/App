package i2par.placement.campus.lifecycle;

import i2par.campus.CampusContact;
import i2par.campus.placement.CoursePlacementItem;
import i2par.campus.placement.Rfp;
import i2par.campus.placement.RfpResponse;
import i2par.campus.placement.RfpResponseItem;
import i2par.employer.Employer;
import i2par.employer.EmployerContact;
import i2par.placement.ApplicationEntityLifeCycle;
import i2par.placement.PlacementConfig;
import i2par.placement.campus.RfpDispatch;

import java.util.List;

import meru.exception.AppException;
import meru.persistence.AttributeOperator;
import meru.persistence.EntityQuery;
import meru.sys.IOSystem;
import meru.template.TemplateEngine;
import meru.template.TemplateMultiData;
import app.domain.PropertyGroup;

public class RfpDispatchLifeCycle extends ApplicationEntityLifeCycle<RfpDispatch> {

    @Override
    public boolean preCreate(RfpDispatch rfpDispatch) {

        Rfp rfp = appEngine.get(Rfp.class,
                                rfpDispatch.getRfpId());

//        if (rfp.getState()
//               .getValue()
//               .equals("New")) {

            PropertyGroup state = getPropertyGroup("rfp-state",
                                                   "Dispatched");
            rfp.setState(state);
            appEngine.save(rfp);
            createRfpResponses(rfp,
                               rfpDispatch);
//        }

        return false;
    }

    private void createRfpResponses(Rfp rfp,
                                    RfpDispatch rfpDispatch) {

        PropertyGroup state = getPropertyGroup("rfp-response-state",
                                               "New");

        EntityQuery<Employer> entityQuery = appEngine.createQuery(Employer.class);
        entityQuery.addQueryParameter("id",
                                      AttributeOperator.IN,
                                      rfpDispatch.getEmployerIds());

        List<Employer> employers = appEngine.get(entityQuery);

        for (Employer employer : employers) {
            createRfpResponse(rfp,
                              employer,
                              null,
                              state);
        }
        
/*        EntityQuery<CampusEmployer> campusEmpQuery = appEngine.createQuery(CampusEmployer.class);
        entityQuery.addQueryParameter("id",
                                      AttributeOperator.IN,
                                      rfpDispatch.getEmployerIds());

        List<CampusEmployer> campEmployers = appEngine.get(campusEmpQuery);

        for (CampusEmployer campEmployer : campEmployers) {
            createRfpResponse(rfp,
                              employer,
                              null,
                              state);
        }
*/
        
        if (rfpDispatch.getEmails() != null) {
            String[] emails = rfpDispatch.getEmails().split(",");
            for (String email : emails) {
                email = email.trim();
                if (email.length() != 0) {
                    
                    Employer emailEmployer = createEmployer(email);
                    createRfpResponse(rfp,
                                      emailEmployer,
                                      email,
                                      state);
                    
                }
            }
        }
        
        
    }

    private void createRfpResponse(Rfp rfp,
                                   Employer employer,
                                   String email,
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
        
        
        EntityQuery<CampusContact> entityQuery = appEngine.createQuery(CampusContact.class);
        entityQuery.addQueryParameter("roleId", 111L);
        CampusContact campusContact = appEngine.getFirst(entityQuery);

        if (campusContact == null) {
            throw new AppException("Placement Officer is not available");
        }


        EntityQuery<EmployerContact> ecQuery = appEngine.createQuery(EmployerContact.class);
        ecQuery.addQueryParameter("employerId", employer.getId());
        ecQuery.addQueryParameter("type.value", "HR");
        EmployerContact employerContact = appEngine.getFirst(ecQuery);

        if (employerContact != null) {
            email =  employerContact.getEmail();
            //throw new AppException("HR Constact is not available");
        }

        
        TemplateMultiData templateData = new TemplateMultiData();
        templateData.addObject("rfp", rfp);
        templateData.addObject("employer", employer);
        templateData.addObject("campusContact", campusContact);
        
        
        String template = IOSystem.read(appContext.getInputStream(PlacementConfig.MAIL_TEMPLATE_DIR+"RFPMailTemplate.html"));
        
        String message = TemplateEngine.getText(template,templateData);
        //appEngine.save(createSendEmail(email, "Request for Placement", message));
    }

    
    private Employer createEmployer(String email) {
        
        int index = email.indexOf('@');
        if (index == -1) {
            throw new AppException("Invalid email : "+email);
        }
        
        String name = email.substring(index+1);
        
        index = name.indexOf('.');
        if (index == -1) {
            throw new AppException("Invalid email : "+email);
        }
        
        String code = name.substring(0, index);
        
        Employer employer = new Employer();
        
        employer.setName(name);
        employer.setCode(code);
        
        employer.setState(getPropertyGroup("employer-state", "Temporary"));
        
        appEngine.save(employer);
        return employer;
    }
}
