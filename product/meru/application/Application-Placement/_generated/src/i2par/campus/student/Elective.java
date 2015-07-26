package i2par.campus.student;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="st_elective")
public class Elective extends AppEntity {


    @Column(name="student_id", nullable=false)
    private Long studentId;

    @OneToOne
    @JoinColumn(name="course_id")
    private i2par.campus.Course course;

    @Column(name="subject", nullable=false)
    private java.lang.String subject;

    public Long getStudentId() {
        
        return studentId;
    }

    public void setStudentId(Long studentId) {

        this.studentId = studentId;
    }

    public i2par.campus.Course getCourse() {
        
        return course;
    }

    public void setCourse(i2par.campus.Course course) {

        this.course = course;
    }

    public java.lang.String getSubject() {
        
        return subject;
    }

    public void setSubject(java.lang.String subject) {

        this.subject = subject;
    }
}
