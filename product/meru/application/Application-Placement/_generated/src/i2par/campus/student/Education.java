package i2par.campus.student;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="st_education")
public class Education extends AppEntity {


    @Column(name="student_id", nullable=false)
    private Long studentId;

    @OneToOne
    @JoinColumn(name="degree_id", nullable=false)
    private app.domain.PropertyGroup degree;

    @Column(name="year", nullable=false)
    private Integer year;

    @Column(name="board", nullable=false)
    private java.lang.String board;

    @Column(name="institute", nullable=false)
    private java.lang.String institute;

    @Column(name="percentage", nullable=false)
    private Integer percentage;

    public Long getStudentId() {
        
        return studentId;
    }

    public void setStudentId(Long studentId) {

        this.studentId = studentId;
    }

    public app.domain.PropertyGroup getDegree() {
        
        return degree;
    }

    public void setDegree(app.domain.PropertyGroup degree) {

        this.degree = degree;
    }

    public Integer getYear() {
        
        return year;
    }

    public void setYear(Integer year) {

        this.year = year;
    }

    public java.lang.String getBoard() {
        
        return board;
    }

    public void setBoard(java.lang.String board) {

        this.board = board;
    }

    public java.lang.String getInstitute() {
        
        return institute;
    }

    public void setInstitute(java.lang.String institute) {

        this.institute = institute;
    }

    public Integer getPercentage() {
        
        return percentage;
    }

    public void setPercentage(Integer percentage) {

        this.percentage = percentage;
    }
}
