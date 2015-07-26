package i2par.campus.employer;

import app.domain.AppEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_campus_employer")
public class CampusEmployer extends AppEntity {


    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @Column(name="name", nullable=false)
    private java.lang.String name;

    @Column(name="search_term", nullable=false)
    private java.lang.String searchTerm;

    @Column(name="is_domain_specific")
    private String isDomainSpecific = "N";
    private transient boolean isDomainSpecificBoolean;

    @Column(name="industry_type")
    private java.lang.String industryType;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="address_id", nullable=false)
    private app.domain.location.Address address;

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public java.lang.String getSearchTerm() {
        
        return searchTerm;
    }

    public void setSearchTerm(java.lang.String searchTerm) {

        this.searchTerm = searchTerm;
    }

    public java.lang.String getIsDomainSpecific() {
        
        return isDomainSpecific;
    }

    public void setIsDomainSpecific(java.lang.String isDomainSpecific) {

        this.isDomainSpecific = isDomainSpecific;
    }

    public boolean isDomainSpecific() {

        return "Y".equals(isDomainSpecific);
    }

    public Boolean getIsDomainSpecificBoolean() {
        
        return isDomainSpecific != null && isDomainSpecific.equals("Y");
    }

    public void setIsDomainSpecificBoolean(Boolean isDomainSpecificBoolean) {

        isDomainSpecific = isDomainSpecificBoolean ? "Y" : "N";
    }

    public boolean isDomainSpecificBoolean() {

        return "Y".equals(isDomainSpecificBoolean);
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

    public String toString() {
        return name;
    }
}
