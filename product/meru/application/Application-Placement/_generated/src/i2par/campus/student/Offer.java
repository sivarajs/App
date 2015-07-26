package i2par.campus.student;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="st_offer")
public class Offer extends AppEntity {


    @Column(name="student_id", nullable=false)
    private Long studentId;

    @Column(name="employer", nullable=false)
    private java.lang.String employer;

    @Column(name="date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    @Column(name="pay", nullable=false)
    private Float pay;

    public Long getStudentId() {
        
        return studentId;
    }

    public void setStudentId(Long studentId) {

        this.studentId = studentId;
    }

    public java.lang.String getEmployer() {
        
        return employer;
    }

    public void setEmployer(java.lang.String employer) {

        this.employer = employer;
    }

    public java.util.Date getDate() {
        
        return date;
    }

    public void setDate(java.util.Date date) {

        this.date = date;
    }

    public Float getPay() {
        
        return pay;
    }

    public void setPay(Float pay) {

        this.pay = pay;
    }
}
