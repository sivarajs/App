package i2par.placement.campus.lifecycle;

import i2par.campus.placement.RfpResponse;
import i2par.placement.ApplicationEntityLifeCycle;
import app.domain.PropertyGroup;

public class RfpResponseLifeCycle extends ApplicationEntityLifeCycle<RfpResponse> {

    @Override
    public boolean preModify(RfpResponse rfpResponse) {

        if (rfpResponse.getPreferredPlacement() != null) {
            PropertyGroup state = getPropertyGroup("rfp-response-state",
                                                   "Accepted");
            rfpResponse.setState(state);
        }

        return true;
    }

    
   /* private void updateEmployerMasterData(RfpResponse rfpResonse) {
        
        Employer empoyer = rfpResonse.getEmployer();
        
        
    }*/
}
