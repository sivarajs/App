package i2par.campus.placement.interview;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_student_interview_result")
public class StudentInterviewResult extends AppEntity {


    @OneToOne
    @JoinColumn(name="student_interview_id", nullable=false)
    private i2par.campus.placement.interview.StudentInterview studentInterview;

    @OneToOne
    @JoinColumn(name="employer_interview_id", nullable=false)
    private i2par.campus.placement.interview.EmployerInterview employerInterview;

    @OneToOne
    @JoinColumn(name="selection_state_id")
    private app.domain.PropertyGroup selectionState;

    @Column(name="evaluated_by")
    private java.lang.String evaluatedBy;

    @Column(name="remarks")
    private java.lang.String remarks;

    public i2par.campus.placement.interview.StudentInterview getStudentInterview() {
        
        return studentInterview;
    }

    public void setStudentInterview(i2par.campus.placement.interview.StudentInterview studentInterview) {

        this.studentInterview = studentInterview;
    }

    public i2par.campus.placement.interview.EmployerInterview getEmployerInterview() {
        
        return employerInterview;
    }

    public void setEmployerInterview(i2par.campus.placement.interview.EmployerInterview employerInterview) {

        this.employerInterview = employerInterview;
    }

    public app.domain.PropertyGroup getSelectionState() {
        
        return selectionState;
    }

    public void setSelectionState(app.domain.PropertyGroup selectionState) {

        this.selectionState = selectionState;
    }

    public java.lang.String getEvaluatedBy() {
        
        return evaluatedBy;
    }

    public void setEvaluatedBy(java.lang.String evaluatedBy) {

        this.evaluatedBy = evaluatedBy;
    }

    public java.lang.String getRemarks() {
        
        return remarks;
    }

    public void setRemarks(java.lang.String remarks) {

        this.remarks = remarks;
    }
}
