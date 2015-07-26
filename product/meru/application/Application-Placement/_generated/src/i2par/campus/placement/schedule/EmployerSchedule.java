package i2par.campus.placement.schedule;

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
@Table(name="cp_employer_schedule")
public class EmployerSchedule extends AppEntity {


    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @OneToOne
    @JoinColumn(name="employer_id", nullable=false)
    private i2par.employer.Employer employer;

    @OneToOne
    @JoinColumn(name="placement_schedule_id", nullable=false)
    private i2par.campus.placement.schedule.PlacementSchedule placementSchedule;

    @OneToOne
    @JoinColumn(name="rfp_response_id", nullable=false)
    private i2par.campus.placement.RfpResponse rfpResponse;

    @OneToMany(mappedBy="employerScheduleId", cascade={CascadeType.ALL})
    private java.util.List<i2par.campus.placement.interview.EmployerInterview> employerInterview;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.PropertyGroup state;

    @OneToOne
    @JoinColumn(name="dispatch_state_id")
    private app.domain.PropertyGroup dispatchState;

    @Column(name="accept_due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date acceptDueDate;

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

    public i2par.campus.placement.schedule.PlacementSchedule getPlacementSchedule() {
        
        return placementSchedule;
    }

    public void setPlacementSchedule(i2par.campus.placement.schedule.PlacementSchedule placementSchedule) {

        this.placementSchedule = placementSchedule;
    }

    public i2par.campus.placement.RfpResponse getRfpResponse() {
        
        return rfpResponse;
    }

    public void setRfpResponse(i2par.campus.placement.RfpResponse rfpResponse) {

        this.rfpResponse = rfpResponse;
    }

    public java.util.List<i2par.campus.placement.interview.EmployerInterview> getEmployerInterview() {

        if (employerInterview == null) {
            this.employerInterview = new java.util.ArrayList<i2par.campus.placement.interview.EmployerInterview>();
        }
        
        return employerInterview;
    }

    public void setEmployerInterview(java.util.List<i2par.campus.placement.interview.EmployerInterview> employerInterview) {

        this.employerInterview = employerInterview;
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }

    public app.domain.PropertyGroup getDispatchState() {
        
        return dispatchState;
    }

    public void setDispatchState(app.domain.PropertyGroup dispatchState) {

        this.dispatchState = dispatchState;
    }

    public java.util.Date getAcceptDueDate() {
        
        return acceptDueDate;
    }

    public void setAcceptDueDate(java.util.Date acceptDueDate) {

        this.acceptDueDate = acceptDueDate;
    }
}
