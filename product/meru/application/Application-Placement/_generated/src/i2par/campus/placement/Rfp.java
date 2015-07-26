package i2par.campus.placement;

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
@Table(name="cp_rfp")
public class Rfp extends AppEntity {


    @Column(name="document_id", nullable=false)
    private java.lang.String documentId;

    @OneToOne
    @JoinColumn(name="campus_id", nullable=false)
    private i2par.campus.Campus campus;

    @OneToOne
    @JoinColumn(name="placement_registration_id")
    private i2par.campus.placement.PlacementRegistration placementRegistration;

    @OneToOne
    @JoinColumn(name="placement_id", nullable=false)
    private i2par.campus.placement.Placement placement;

    @Column(name="mode", nullable=false)
    private java.lang.String mode;

    @Column(name="due_date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dueDate;

    @Column(name="expected_pay", nullable=false)
    private Float expectedPay;

    @Column(name="total_students")
    private Integer totalStudents;

    @Column(name="participated_students")
    private Integer participatedStudents;

    @OneToOne
    @JoinColumn(name="preferred_placement_id")
    private app.domain.PropertyGroup preferredPlacement;

    @OneToOne
    @JoinColumn(name="placement_category_id", nullable=false)
    private app.domain.PropertyGroup placementCategory;

    @Column(name="resume_visible")
    private String resumeVisible = "N";
    private transient boolean resumeVisibleBoolean;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.PropertyGroup state;

    @Column(name="response_seq")
    private Integer responseSeq;

    @OneToMany(mappedBy="ownerId", cascade={CascadeType.ALL})
    private java.util.List<i2par.campus.placement.CoursePlacementItem> coursePlacementItems;

    public java.lang.String getDocumentId() {
        
        return documentId;
    }

    public void setDocumentId(java.lang.String documentId) {

        this.documentId = documentId;
    }

    public i2par.campus.Campus getCampus() {
        
        return campus;
    }

    public void setCampus(i2par.campus.Campus campus) {

        this.campus = campus;
    }

    public i2par.campus.placement.PlacementRegistration getPlacementRegistration() {
        
        return placementRegistration;
    }

    public void setPlacementRegistration(i2par.campus.placement.PlacementRegistration placementRegistration) {

        this.placementRegistration = placementRegistration;
    }

    public i2par.campus.placement.Placement getPlacement() {
        
        return placement;
    }

    public void setPlacement(i2par.campus.placement.Placement placement) {

        this.placement = placement;
    }

    public java.lang.String getMode() {
        
        return mode;
    }

    public void setMode(java.lang.String mode) {

        this.mode = mode;
    }

    public java.util.Date getDueDate() {
        
        return dueDate;
    }

    public void setDueDate(java.util.Date dueDate) {

        this.dueDate = dueDate;
    }

    public Float getExpectedPay() {
        
        return expectedPay;
    }

    public void setExpectedPay(Float expectedPay) {

        this.expectedPay = expectedPay;
    }

    public Integer getTotalStudents() {
        
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {

        this.totalStudents = totalStudents;
    }

    public Integer getParticipatedStudents() {
        
        return participatedStudents;
    }

    public void setParticipatedStudents(Integer participatedStudents) {

        this.participatedStudents = participatedStudents;
    }

    public app.domain.PropertyGroup getPreferredPlacement() {
        
        return preferredPlacement;
    }

    public void setPreferredPlacement(app.domain.PropertyGroup preferredPlacement) {

        this.preferredPlacement = preferredPlacement;
    }

    public app.domain.PropertyGroup getPlacementCategory() {
        
        return placementCategory;
    }

    public void setPlacementCategory(app.domain.PropertyGroup placementCategory) {

        this.placementCategory = placementCategory;
    }

    public java.lang.String getResumeVisible() {
        
        return resumeVisible;
    }

    public void setResumeVisible(java.lang.String resumeVisible) {

        this.resumeVisible = resumeVisible;
    }

    public boolean resumeVisible() {

        return "Y".equals(resumeVisible);
    }

    public Boolean getResumeVisibleBoolean() {
        
        return resumeVisible != null && resumeVisible.equals("Y");
    }

    public void setResumeVisibleBoolean(Boolean resumeVisibleBoolean) {

        resumeVisible = resumeVisibleBoolean ? "Y" : "N";
    }

    public boolean resumeVisibleBoolean() {

        return "Y".equals(resumeVisibleBoolean);
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }

    public Integer getResponseSeq() {
        
        return responseSeq;
    }

    public void setResponseSeq(Integer responseSeq) {

        this.responseSeq = responseSeq;
    }

    public java.util.List<i2par.campus.placement.CoursePlacementItem> getCoursePlacementItems() {

        if (coursePlacementItems == null) {
            this.coursePlacementItems = new java.util.ArrayList<i2par.campus.placement.CoursePlacementItem>();
        }
        
        return coursePlacementItems;
    }

    public void setCoursePlacementItems(java.util.List<i2par.campus.placement.CoursePlacementItem> coursePlacementItems) {

        this.coursePlacementItems = coursePlacementItems;
    }
}
