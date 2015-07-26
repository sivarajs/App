package i2par.campus.placement;

import app.domain.AppEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_rfp_response")
public class RfpResponse extends AppEntity {


    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @OneToOne
    @JoinColumn(name="employer_id", nullable=false)
    private i2par.employer.Employer employer;

    @OneToOne
    @JoinColumn(name="rfp_id", nullable=false)
    private i2par.campus.placement.Rfp rfp;

    @OneToOne
    @JoinColumn(name="preferred_placement_id")
    private app.domain.PropertyGroup preferredPlacement;

    @OneToOne
    @JoinColumn(name="selection_type_id")
    private app.domain.PropertyGroup selectionType;

    @Column(name="acceptable_pay")
    private Float acceptablePay;

    @Column(name="responded_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date respondedOn;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.PropertyGroup state;

    @OneToMany(mappedBy="rfpResponseId", cascade={CascadeType.ALL})
    private java.util.List<i2par.campus.placement.RfpResponseItem> rfpResponseItems;

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public i2par.employer.Employer getEmployer() {
        
        return employer;
    }

    public void setEmployer(i2par.employer.Employer employer) {

        this.employer = employer;
    }

    public i2par.campus.placement.Rfp getRfp() {
        
        return rfp;
    }

    public void setRfp(i2par.campus.placement.Rfp rfp) {

        this.rfp = rfp;
    }

    public app.domain.PropertyGroup getPreferredPlacement() {
        
        return preferredPlacement;
    }

    public void setPreferredPlacement(app.domain.PropertyGroup preferredPlacement) {

        this.preferredPlacement = preferredPlacement;
    }

    public app.domain.PropertyGroup getSelectionType() {
        
        return selectionType;
    }

    public void setSelectionType(app.domain.PropertyGroup selectionType) {

        this.selectionType = selectionType;
    }

    public Float getAcceptablePay() {
        
        return acceptablePay;
    }

    public void setAcceptablePay(Float acceptablePay) {

        this.acceptablePay = acceptablePay;
    }

    public java.util.Date getRespondedOn() {
        
        return respondedOn;
    }

    public void setRespondedOn(java.util.Date respondedOn) {

        this.respondedOn = respondedOn;
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }

    public java.util.List<i2par.campus.placement.RfpResponseItem> getRfpResponseItems() {

        if (rfpResponseItems == null) {
            this.rfpResponseItems = new java.util.ArrayList<i2par.campus.placement.RfpResponseItem>();
        }
        
        return rfpResponseItems;
    }

    public void setRfpResponseItems(java.util.List<i2par.campus.placement.RfpResponseItem> rfpResponseItems) {

        this.rfpResponseItems = rfpResponseItems;
    }
}
