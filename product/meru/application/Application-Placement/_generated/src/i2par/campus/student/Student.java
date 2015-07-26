package i2par.campus.student;

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
@Table(name="st_student")
public class Student extends AppEntity {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @Column(name="code", nullable=false)
    private java.lang.String code;

    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @Column(name="email", nullable=false)
    private java.lang.String email;

    @Column(name="ipar_id")
    private java.lang.String iparId;

    @OneToOne
    @JoinColumn(name="course1_id")
    private i2par.campus.Course course1;

    @OneToOne
    @JoinColumn(name="course2_id")
    private i2par.campus.Course course2;

    @Column(name="score")
    private Float score;

    @Column(name="birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date birthDate;

    @OneToOne
    @JoinColumn(name="sex_id")
    private app.domain.PropertyGroup sex;

    @Column(name="weight")
    private Integer weight;

    @Column(name="height")
    private Integer height;

    @Column(name="internship_duration")
    private Integer internshipDuration;

    @OneToOne
    @JoinColumn(name="internship_unit_id")
    private app.domain.PropertyGroup internshipUnit;

    @Column(name="internship_company")
    private java.lang.String internshipCompany;

    @Column(name="internship_details")
    private java.lang.String internshipDetails;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="student_registration_id")
    private i2par.campus.student.StudentRegistration studentRegistration;

    @OneToMany(mappedBy="studentId", cascade={CascadeType.ALL})
    private java.util.List<i2par.campus.student.Education> educations;

    @OneToMany(mappedBy="studentId", cascade={CascadeType.ALL})
    private java.util.List<i2par.campus.student.Award> awards;

    @OneToMany(mappedBy="studentId", cascade={CascadeType.ALL})
    private java.util.List<i2par.campus.student.Elective> electives;

    @OneToMany(mappedBy="studentId", cascade={CascadeType.ALL})
    private java.util.List<i2par.campus.student.Offer> offers;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public java.lang.String getCode() {
        
        return code;
    }

    public void setCode(java.lang.String code) {

        this.code = code;
    }

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public java.lang.String getEmail() {
        
        return email;
    }

    public void setEmail(java.lang.String email) {

        this.email = email;
    }

    public java.lang.String getIparId() {
        
        return iparId;
    }

    public void setIparId(java.lang.String iparId) {

        this.iparId = iparId;
    }

    public i2par.campus.Course getCourse1() {
        
        return course1;
    }

    public void setCourse1(i2par.campus.Course course1) {

        this.course1 = course1;
    }

    public i2par.campus.Course getCourse2() {
        
        return course2;
    }

    public void setCourse2(i2par.campus.Course course2) {

        this.course2 = course2;
    }

    public Float getScore() {
        
        return score;
    }

    public void setScore(Float score) {

        this.score = score;
    }

    public java.util.Date getBirthDate() {
        
        return birthDate;
    }

    public void setBirthDate(java.util.Date birthDate) {

        this.birthDate = birthDate;
    }

    public app.domain.PropertyGroup getSex() {
        
        return sex;
    }

    public void setSex(app.domain.PropertyGroup sex) {

        this.sex = sex;
    }

    public Integer getWeight() {
        
        return weight;
    }

    public void setWeight(Integer weight) {

        this.weight = weight;
    }

    public Integer getHeight() {
        
        return height;
    }

    public void setHeight(Integer height) {

        this.height = height;
    }

    public Integer getInternshipDuration() {
        
        return internshipDuration;
    }

    public void setInternshipDuration(Integer internshipDuration) {

        this.internshipDuration = internshipDuration;
    }

    public app.domain.PropertyGroup getInternshipUnit() {
        
        return internshipUnit;
    }

    public void setInternshipUnit(app.domain.PropertyGroup internshipUnit) {

        this.internshipUnit = internshipUnit;
    }

    public java.lang.String getInternshipCompany() {
        
        return internshipCompany;
    }

    public void setInternshipCompany(java.lang.String internshipCompany) {

        this.internshipCompany = internshipCompany;
    }

    public java.lang.String getInternshipDetails() {
        
        return internshipDetails;
    }

    public void setInternshipDetails(java.lang.String internshipDetails) {

        this.internshipDetails = internshipDetails;
    }

    public i2par.campus.student.StudentRegistration getStudentRegistration() {

        if (studentRegistration == null) {
        }
        
        return studentRegistration;
    }

    public void setStudentRegistration(i2par.campus.student.StudentRegistration studentRegistration) {

        this.studentRegistration = studentRegistration;
    }

    public java.util.List<i2par.campus.student.Education> getEducations() {

        if (educations == null) {
            this.educations = new java.util.ArrayList<i2par.campus.student.Education>();
        }
        
        return educations;
    }

    public void setEducations(java.util.List<i2par.campus.student.Education> educations) {

        this.educations = educations;
    }

    public java.util.List<i2par.campus.student.Award> getAwards() {

        if (awards == null) {
            this.awards = new java.util.ArrayList<i2par.campus.student.Award>();
        }
        
        return awards;
    }

    public void setAwards(java.util.List<i2par.campus.student.Award> awards) {

        this.awards = awards;
    }

    public java.util.List<i2par.campus.student.Elective> getElectives() {

        if (electives == null) {
            this.electives = new java.util.ArrayList<i2par.campus.student.Elective>();
        }
        
        return electives;
    }

    public void setElectives(java.util.List<i2par.campus.student.Elective> electives) {

        this.electives = electives;
    }

    public java.util.List<i2par.campus.student.Offer> getOffers() {

        if (offers == null) {
            this.offers = new java.util.ArrayList<i2par.campus.student.Offer>();
        }
        
        return offers;
    }

    public void setOffers(java.util.List<i2par.campus.student.Offer> offers) {

        this.offers = offers;
    }

    public String toString() {
        return name;
    }
}
