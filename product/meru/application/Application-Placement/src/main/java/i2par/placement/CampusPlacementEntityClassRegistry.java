package i2par.placement;

public class CampusPlacementEntityClassRegistry extends app.Application_PlacementEntityClassRegistry {
    protected void populateClassMap() {
        super.populateClassMap();
        addResourceClass("StudentImage",
                         i2par.placement.student.StudentImage.class);
        addResourceClass("RfpDispatch",
                         i2par.placement.campus.RfpDispatch.class);
        addResourceClass("InterviewRoundRunStatus",
                         i2par.placement.campus.InterviewRoundRunStatus.class);
        
    }
}
