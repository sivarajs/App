package i2par.employer;

import app.domain.AppEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_employer")
public class Employer extends AppEntity {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @Column(name="code", nullable=false)
    private java.lang.String code;

    @Column(name="domain")
    private java.lang.String domain;

    @Column(name="industry_type")
    private java.lang.String industryType;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="address_id")
    private app.domain.location.Address address;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.PropertyGroup state;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public java.lang.String getCode() {
        
        return code;
    }

    public void setCode(java.lang.String code) {

        this.code = code;
    }

    public java.lang.String getDomain() {
        
        return domain;
    }

    public void setDomain(java.lang.String domain) {

        this.domain = domain;
    }

    public java.lang.String getIndustryType() {
        
        return industryType;
    }

    public void setIndustryType(java.lang.String industryType) {

        this.industryType = industryType;
    }

    public app.domain.location.Address getAddress() {

        if (address == null) {
        }
        
        return address;
    }

    public void setAddress(app.domain.location.Address address) {

        this.address = address;
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }

    public String toString() {
        return name;
    }
}
