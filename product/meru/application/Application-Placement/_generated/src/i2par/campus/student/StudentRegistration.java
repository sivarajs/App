package i2par.campus.student;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="st_student_registration")
public class StudentRegistration extends AppEntity {


    @Column(name="placement_registration_id", nullable=false)
    private Long placementRegistrationId;

    @OneToOne
    @JoinColumn(name="placement_degree_id")
    private app.domain.PropertyGroup placementDegree;

    @Column(name="max_offers")
    private Integer maxOffers;

    @Column(name="actual_offers")
    private Integer actualOffers;

    @Column(name="joining_period")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date joiningPeriod;

    @Column(name="evaluation_history")
    private java.lang.String evaluationHistory;

    @Column(name="review")
    private java.lang.String review;

    @OneToOne
    @JoinColumn(name="state_id")
    private app.domain.PropertyGroup state;

    @OneToOne
    @JoinColumn(name="prev_state_id")
    private app.domain.PropertyGroup prevState;

    @Column(name="state_info")
    private java.lang.String stateInfo;

    public Long getPlacementRegistrationId() {
        
        return placementRegistrationId;
    }

    public void setPlacementRegistrationId(Long placementRegistrationId) {

        this.placementRegistrationId = placementRegistrationId;
    }

    public app.domain.PropertyGroup getPlacementDegree() {
        
        return placementDegree;
    }

    public void setPlacementDegree(app.domain.PropertyGroup placementDegree) {

        this.placementDegree = placementDegree;
    }

    public Integer getMaxOffers() {
        
        return maxOffers;
    }

    public void setMaxOffers(Integer maxOffers) {

        this.maxOffers = maxOffers;
    }

    public Integer getActualOffers() {
        
        return actualOffers;
    }

    public void setActualOffers(Integer actualOffers) {

        this.actualOffers = actualOffers;
    }

    public java.util.Date getJoiningPeriod() {
        
        return joiningPeriod;
    }

    public void setJoiningPeriod(java.util.Date joiningPeriod) {

        this.joiningPeriod = joiningPeriod;
    }

    public java.lang.String getEvaluationHistory() {
        
        return evaluationHistory;
    }

    public void setEvaluationHistory(java.lang.String evaluationHistory) {

        this.evaluationHistory = evaluationHistory;
    }

    public java.lang.String getReview() {
        
        return review;
    }

    public void setReview(java.lang.String review) {

        this.review = review;
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }

    public app.domain.PropertyGroup getPrevState() {
        
        return prevState;
    }

    public void setPrevState(app.domain.PropertyGroup prevState) {

        this.prevState = prevState;
    }

    public java.lang.String getStateInfo() {
        
        return stateInfo;
    }

    public void setStateInfo(java.lang.String stateInfo) {

        this.stateInfo = stateInfo;
    }
}
