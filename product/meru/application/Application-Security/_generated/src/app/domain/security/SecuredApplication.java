package app.domain.security;

import app.domain.BusinessApplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sec_secured_application")
public class SecuredApplication extends BusinessApplication {


    @Column(name="access_type")
    private java.lang.String accessType;

    public java.lang.String getAccessType() {
        
        return accessType;
    }

    public void setAccessType(java.lang.String accessType) {

        this.accessType = accessType;
    }
}
