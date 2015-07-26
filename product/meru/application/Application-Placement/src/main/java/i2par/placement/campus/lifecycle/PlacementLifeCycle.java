package i2par.placement.campus.lifecycle;

import i2par.campus.placement.Placement;
import i2par.placement.ApplicationEntityLifeCycle;

import java.util.Date;

import meru.exception.AppException;
import meru.persistence.AttributeOperator;
import meru.persistence.EntityQuery;
import meru.sys.SystemDate;
import app.domain.PropertyGroup;

public class PlacementLifeCycle extends ApplicationEntityLifeCycle<Placement> {

    @Override
    public boolean preCreate(Placement placement) {

        validate(placement);

        if (!setState(placement)) {
            placement.setState(getPropertyGroup("placement-hierarchy-state",
                                                "New"));
        }
        return true;
    }

    @Override
    public boolean preModify(Placement placement) {
        setState(placement);
        validate(placement);
        return true;
    }
    
    public boolean setState(Placement placement) {

        Date date = SystemDate.getInstance()
                              .currentDate();

        PropertyGroup newState = null;

        Date rolloutStart = placement.getRolloutStart();
        Date rolloutEnd = placement.getRolloutEnd();

        String currState = null;
        
        if (placement.getState() == null) {
            currState = (placement.getState() == null) ? "New" : placement.getState().getValue();
        }
        
        if (rolloutEnd.before(date)) {

            if (!"Expired".equals(currState)) {
                newState = getPropertyGroup("placement-hierarchy-state",
                                            "Expired");
            }

        }
        else {
            if (!rolloutStart.after(date)) {
                if (!"Active".equals(currState)) {
                    newState = getPropertyGroup("placement-hierarchy-state",
                                                "Active");
                }
            }
        }

        if (newState != null) {
            placement.setState(newState);
            return true;
        }

        return false;
    }

    private void validate(Placement placement) {

        // | S E |
        validate(placement,
                 AttributeOperator.LESSER_THAN_OR_EQUALS,
                 AttributeOperator.GREATER_THAN_OR_EQUALS,
                 AttributeOperator.LESSER_THAN_OR_EQUALS,
                 AttributeOperator.GREATER_THAN_OR_EQUALS);
        // S | | E
        validate(placement,
                 AttributeOperator.GREATER_THAN_OR_EQUALS,
                 AttributeOperator.GREATER_THAN_OR_EQUALS,
                 AttributeOperator.LESSER_THAN_OR_EQUALS,
                 AttributeOperator.LESSER_THAN_OR_EQUALS);
        // | S | E
        validate(placement,
                 AttributeOperator.LESSER_THAN_OR_EQUALS,
                 AttributeOperator.GREATER_THAN_OR_EQUALS,
                 AttributeOperator.LESSER_THAN_OR_EQUALS,
                 AttributeOperator.LESSER_THAN_OR_EQUALS);
        // S | E |
        validate(placement,
                 AttributeOperator.GREATER_THAN_OR_EQUALS,
                 AttributeOperator.GREATER_THAN_OR_EQUALS,
                 AttributeOperator.LESSER_THAN_OR_EQUALS,
                 AttributeOperator.GREATER_THAN_OR_EQUALS);
    }

    private void validate(Placement placement,
                          AttributeOperator rolloutStartOperator,
                          AttributeOperator rolloutStart1Operator,
                          AttributeOperator rolloutEndOperator,
                          AttributeOperator rolloutEnd1Operator) {

        EntityQuery<Placement> entityQuery = appEngine.createQuery(Placement.class);
        
        if (placement.getId() != null) {
            entityQuery.addQueryParameter("id",
                                          AttributeOperator.NOT_EQUALS,
                                          placement.getId());
            
        }
        
        entityQuery.addQueryParameter("campusId",
                                      placement.getCampusId());
        entityQuery.addQueryParameter("rolloutStart",
                                      rolloutStartOperator,
                                      placement.getRolloutStart());
        entityQuery.addQueryParameter("rolloutEnd",
                                      rolloutStart1Operator,
                                      placement.getRolloutStart());
        entityQuery.addQueryParameter("rolloutStart",
                                      rolloutEndOperator,
                                      placement.getRolloutEnd());
        entityQuery.addQueryParameter("rolloutEnd",
                                      rolloutEnd1Operator,
                                      placement.getRolloutEnd());

        Placement exPlacement = appEngine.getFirst(entityQuery);

        if (exPlacement != null) {
            throw new AppException("The Placement Hierarchy conflicts with the existing one");
        }

    }
}
