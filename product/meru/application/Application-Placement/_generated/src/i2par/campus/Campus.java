package i2par.campus;

import app.domain.AppEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_campus")
public class Campus extends AppEntity {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @Column(name="alias")
    private java.lang.String alias;

    @Column(name="code", nullable=false)
    private java.lang.String code;

    @Column(name="website")
    private java.lang.String website;

    @OneToOne
    @JoinColumn(name="university_id", nullable=false)
    private i2par.campus.University university;

    @Column(name="estabilished_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date estabilishedOn;

    @Column(name="affiliated_from")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date affiliatedFrom;

    @Column(name="student_count")
    private Long studentCount;

    @OneToOne
    @JoinColumn(name="selection_mode_id")
    private app.domain.PropertyGroup selectionMode;

    @OneToOne
    @JoinColumn(name="institute_type_id")
    private app.domain.PropertyGroup instituteType;

    @OneToOne
    @JoinColumn(name="evaluation_type_id")
    private app.domain.PropertyGroup evaluationType;

    @OneToOne
    @JoinColumn(name="education_pattern_id")
    private app.domain.PropertyGroup educationPattern;

    @OneToOne
    @JoinColumn(name="placement_orientation_id")
    private app.domain.PropertyGroup placementOrientation;

    @Column(name="internship_duration")
    private Integer internshipDuration;

    @OneToOne
    @JoinColumn(name="internship_unit_id")
    private app.domain.PropertyGroup internshipUnit;

    @OneToOne
    @JoinColumn(name="internship_type_id")
    private app.domain.PropertyGroup internshipType;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="address_id")
    private app.domain.location.Address address;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public java.lang.String getAlias() {
        
        return alias;
    }

    public void setAlias(java.lang.String alias) {

        this.alias = alias;
    }

    public java.lang.String getCode() {
        
        return code;
    }

    public void setCode(java.lang.String code) {

        this.code = code;
    }

    public java.lang.String getWebsite() {
        
        return website;
    }

    public void setWebsite(java.lang.String website) {

        this.website = website;
    }

    public i2par.campus.University getUniversity() {
        
        return university;
    }

    public void setUniversity(i2par.campus.University university) {

        this.university = university;
    }

    public java.util.Date getEstabilishedOn() {
        
        return estabilishedOn;
    }

    public void setEstabilishedOn(java.util.Date estabilishedOn) {

        this.estabilishedOn = estabilishedOn;
    }

    public java.util.Date getAffiliatedFrom() {
        
        return affiliatedFrom;
    }

    public void setAffiliatedFrom(java.util.Date affiliatedFrom) {

        this.affiliatedFrom = affiliatedFrom;
    }

    public Long getStudentCount() {
        
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {

        this.studentCount = studentCount;
    }

    public app.domain.PropertyGroup getSelectionMode() {
        
        return selectionMode;
    }

    public void setSelectionMode(app.domain.PropertyGroup selectionMode) {

        this.selectionMode = selectionMode;
    }

    public app.domain.PropertyGroup getInstituteType() {
        
        return instituteType;
    }

    public void setInstituteType(app.domain.PropertyGroup instituteType) {

        this.instituteType = instituteType;
    }

    public app.domain.PropertyGroup getEvaluationType() {
        
        return evaluationType;
    }

    public void setEvaluationType(app.domain.PropertyGroup evaluationType) {

        this.evaluationType = evaluationType;
    }

    public app.domain.PropertyGroup getEducationPattern() {
        
        return educationPattern;
    }

    public void setEducationPattern(app.domain.PropertyGroup educationPattern) {

        this.educationPattern = educationPattern;
    }

    public app.domain.PropertyGroup getPlacementOrientation() {
        
        return placementOrientation;
    }

    public void setPlacementOrientation(app.domain.PropertyGroup placementOrientation) {

        this.placementOrientation = placementOrientation;
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

    public app.domain.PropertyGroup getInternshipType() {
        
        return internshipType;
    }

    public void setInternshipType(app.domain.PropertyGroup internshipType) {

        this.internshipType = internshipType;
    }

    public app.domain.location.Address getAddress() {

        if (address == null) {
        }
        
        return address;
    }

    public void setAddress(app.domain.location.Address address) {

        this.address = address;
    }

    public String toString() {
        return name;
    }
}
