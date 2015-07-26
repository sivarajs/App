package i2par.campus.placement.schedule;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_placement_schedule")
public class PlacementSchedule extends AppEntity {


    @Column(name="document_id", nullable=false)
    private java.lang.String documentId;

    @OneToOne
    @JoinColumn(name="campus_id", nullable=false)
    private i2par.campus.Campus campus;

    @OneToOne
    @JoinColumn(name="rfp_id")
    private i2par.campus.placement.Rfp rfp;

    @OneToOne
    @JoinColumn(name="placement_registration_id", nullable=false)
    private i2par.campus.placement.PlacementRegistration placementRegistration;

    @Column(name="mode", nullable=false)
    private java.lang.String mode;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.PropertyGroup state;

    @Column(name="due_date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dueDate;

    public java.lang.String getDocumentId() {
        
        return documentId;
    }

    public void setDocumentId(java.lang.String documentId) {

        this.documentId = documentId;
    }

    public i2par.campus.Campus getCampus() {
        
        return campus;
    }

    public void setCampus(i2par.campus.Campus campus) {

        this.campus = campus;
    }

    public i2par.campus.placement.Rfp getRfp() {
        
        return rfp;
    }

    public void setRfp(i2par.campus.placement.Rfp rfp) {

        this.rfp = rfp;
    }

    public i2par.campus.placement.PlacementRegistration getPlacementRegistration() {
        
        return placementRegistration;
    }

    public void setPlacementRegistration(i2par.campus.placement.PlacementRegistration placementRegistration) {

        this.placementRegistration = placementRegistration;
    }

    public java.lang.String getMode() {
        
        return mode;
    }

    public void setMode(java.lang.String mode) {

        this.mode = mode;
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }

    public java.util.Date getDueDate() {
        
        return dueDate;
    }

    public void setDueDate(java.util.Date dueDate) {

        this.dueDate = dueDate;
    }
}
