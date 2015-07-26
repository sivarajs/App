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
@Table(name="cp_student_interview")
public class StudentInterview extends AppEntity {


    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @Column(name="employer_id", nullable=false)
    private Long employerId;

    @Column(name="employer_schedule_id", nullable=false)
    private Long employerScheduleId;

    @OneToOne
    @JoinColumn(name="student_id", nullable=false)
    private i2par.campus.student.Student student;

    @Column(name="course_id", nullable=false)
    private Long courseId;

    @OneToOne
    @JoinColumn(name="selection_state_id")
    private app.domain.PropertyGroup selectionState;

    @OneToOne
    @JoinColumn(name="acceptance_state_id")
    private app.domain.PropertyGroup acceptanceState;

    @Column(name="acceptance_due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date acceptanceDueDate;

    @Column(name="remarks")
    private java.lang.String remarks;

    @Column(name="pay")
    private Float pay;

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public Long getEmployerId() {
        
        return employerId;
    }

    public void setEmployerId(Long employerId) {

        this.employerId = employerId;
    }

    public Long getEmployerScheduleId() {
        
        return employerScheduleId;
    }

    public void setEmployerScheduleId(Long employerScheduleId) {

        this.employerScheduleId = employerScheduleId;
    }

    public i2par.campus.student.Student getStudent() {
        
        return student;
    }

    public void setStudent(i2par.campus.student.Student student) {

        this.student = student;
    }

    public Long getCourseId() {
        
        return courseId;
    }

    public void setCourseId(Long courseId) {

        this.courseId = courseId;
    }

    public app.domain.PropertyGroup getSelectionState() {
        
        return selectionState;
    }

    public void setSelectionState(app.domain.PropertyGroup selectionState) {

        this.selectionState = selectionState;
    }

    public app.domain.PropertyGroup getAcceptanceState() {
        
        return acceptanceState;
    }

    public void setAcceptanceState(app.domain.PropertyGroup acceptanceState) {

        this.acceptanceState = acceptanceState;
    }

    public java.util.Date getAcceptanceDueDate() {
        
        return acceptanceDueDate;
    }

    public void setAcceptanceDueDate(java.util.Date acceptanceDueDate) {

        this.acceptanceDueDate = acceptanceDueDate;
    }

    public java.lang.String getRemarks() {
        
        return remarks;
    }

    public void setRemarks(java.lang.String remarks) {

        this.remarks = remarks;
    }

    public Float getPay() {
        
        return pay;
    }

    public void setPay(Float pay) {

        this.pay = pay;
    }
}
