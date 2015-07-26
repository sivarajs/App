package i2par.campus.placement.interview;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_employer_interview")
public class EmployerInterview extends AppEntity {


    @Column(name="employer_schedule_id", nullable=false)
    private Long employerScheduleId;

    @OneToOne
    @JoinColumn(name="interview_type_id", nullable=false)
    private app.domain.PropertyGroup interviewType;

    @Column(name="description", nullable=false)
    private java.lang.String description;

    @Column(name="date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    @Column(name="time", nullable=false)
    private java.lang.String time;

    @Column(name="duration", nullable=false)
    private Integer duration;

    @Column(name="buffer_time")
    private Integer bufferTime;

    @OneToOne
    @JoinColumn(name="campus_venue_id")
    private i2par.campus.CampusVenue campusVenue;

    @Column(name="temp_venue")
    private java.lang.String tempVenue;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.PropertyGroup state;

    public Long getEmployerScheduleId() {
        
        return employerScheduleId;
    }

    public void setEmployerScheduleId(Long employerScheduleId) {

        this.employerScheduleId = employerScheduleId;
    }

    public app.domain.PropertyGroup getInterviewType() {
        
        return interviewType;
    }

    public void setInterviewType(app.domain.PropertyGroup interviewType) {

        this.interviewType = interviewType;
    }

    public java.lang.String getDescription() {
        
        return description;
    }

    public void setDescription(java.lang.String description) {

        this.description = description;
    }

    public java.util.Date getDate() {
        
        return date;
    }

    public void setDate(java.util.Date date) {

        this.date = date;
    }

    public java.lang.String getTime() {
        
        return time;
    }

    public void setTime(java.lang.String time) {

        this.time = time;
    }

    public Integer getDuration() {
        
        return duration;
    }

    public void setDuration(Integer duration) {

        this.duration = duration;
    }

    public Integer getBufferTime() {
        
        return bufferTime;
    }

    public void setBufferTime(Integer bufferTime) {

        this.bufferTime = bufferTime;
    }

    public i2par.campus.CampusVenue getCampusVenue() {
        
        return campusVenue;
    }

    public void setCampusVenue(i2par.campus.CampusVenue campusVenue) {

        this.campusVenue = campusVenue;
    }

    public java.lang.String getTempVenue() {
        
        return tempVenue;
    }

    public void setTempVenue(java.lang.String tempVenue) {

        this.tempVenue = tempVenue;
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }
}
