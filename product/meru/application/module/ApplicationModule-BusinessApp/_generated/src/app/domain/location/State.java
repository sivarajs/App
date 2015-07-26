package app.domain.location;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="core_state")
public class State extends AppEntity implements app.entity.Nameable {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @Column(name="country_id", nullable=false)
    private Long countryId;

    @Column(name="code", nullable=false)
    private java.lang.String code;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public Long getCountryId() {
        
        return countryId;
    }

    public void setCountryId(Long countryId) {

        this.countryId = countryId;
    }

    public java.lang.String getCode() {
        
        return code;
    }

    public void setCode(java.lang.String code) {

        this.code = code;
    }

    public String toString() {
        return name;
    }
}
