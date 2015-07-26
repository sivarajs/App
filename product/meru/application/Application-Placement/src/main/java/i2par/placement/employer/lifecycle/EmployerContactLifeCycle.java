package i2par.placement.employer.lifecycle;

import i2par.employer.Employer;
import i2par.employer.EmployerContact;
import i2par.placement.ApplicationEntityLifeCycle;

public class EmployerContactLifeCycle extends ApplicationEntityLifeCycle<EmployerContact> {


    @Override
    public EmployerContact postCreate(EmployerContact employerContact) {
        
        Employer employer = appEngine.get(Employer.class, employerContact.getEmployerId());

        Long roleId = null;
        String id = null;
        if (employerContact.getType().getValue().equals("HR")) {
            id = "_HR";
            roleId = 122L;
            
        }
        else {
            id = "_PC";
            roleId = 123L;
        }
        
        id = employer.getCode().toUpperCase()+id;
        createUser(id,
                   roleId,
                   String.valueOf(employer.getId()));
        
        return null;
    }

 
}
