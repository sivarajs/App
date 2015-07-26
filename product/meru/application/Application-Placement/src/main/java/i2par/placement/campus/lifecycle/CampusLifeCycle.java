package i2par.placement.campus.lifecycle;

import i2par.campus.Campus;
import i2par.campus.CampusContact;
import i2par.placement.ApplicationEntityLifeCycle;
import app.domain.Property;
import app.domain.PropertyGroup;

public class CampusLifeCycle extends ApplicationEntityLifeCycle<Campus> {

    @Override
    public Campus postCreate(Campus campus) {

        Long campusId = campus.getId();
        String code = campus.getCode();

        PropertyGroup staffType = getPropertyGroup("campus-staff-type",
                                                   "Faculty");

        createCampusUsers(campusId,
                          111L,
                          staffType);
        createCampusUsers(campusId,
                          112L,
                          staffType);
        createCampusUsers(campusId,
                          112L,
                          staffType);

        createProperty(campus.getId(),
                       "registration-clause");
        createProperty(campus.getId(),
                       "registration-notification");

        createUser(code + "_PO",
                   111L,
                   String.valueOf(campusId));
        createUser(code + "_PA1",
                   112L,
                   String.valueOf(campusId));
        createUser(code + "_PA2",
                   112L,
                   String.valueOf(campusId));

        return null;
    }

    private void createCampusUsers(Long campusId, Long roleId, PropertyGroup staffType) {

        CampusContact campusContact = new CampusContact();
        campusContact.setCampusId(campusId);
        campusContact.setEmail("Change it");
        campusContact.setPhone("Change it");
        campusContact.setName("Change it");
        campusContact.setRoleId(roleId);
        campusContact.setStaffType(staffType);
        appEngine.save(campusContact);
    }

    private void createProperty(Long campusId, String name) {

        Property property = new Property();
        Property adminProp = getProperty(null,
                                         "admin",
                                         "campus-placement",
                                         name);

        property.setOwnerId(campusId);
        property.setCategory("campus");
        property.setName(adminProp.getName());
        property.setValue(adminProp.getValue());
        property.setPrefix(adminProp.getPrefix());
        property.setType(adminProp.getType());

        appEngine.save(property);

    }
}
