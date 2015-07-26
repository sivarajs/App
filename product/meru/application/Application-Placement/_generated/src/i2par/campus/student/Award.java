package i2par.campus.student;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="st_award")
public class Award extends AppEntity {


    @Column(name="student_id", nullable=false)
    private Long studentId;

    @Column(name="year", nullable=false)
    private Integer year;

    @Column(name="detail", nullable=false)
    private java.lang.String detail;

    public Long getStudentId() {
        
        return studentId;
    }

    public void setStudentId(Long studentId) {

        this.studentId = studentId;
    }

    public Integer getYear() {
        
        return year;
    }

    public void setYear(Integer year) {

        this.year = year;
    }

    public java.lang.String getDetail() {
        
        return detail;
    }

    public void setDetail(java.lang.String detail) {

        this.detail = detail;
    }
}
