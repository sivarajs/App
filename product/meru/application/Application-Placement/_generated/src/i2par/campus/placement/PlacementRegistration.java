package i2par.campus.placement;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_placement_registration")
public class PlacementRegistration extends AppEntity {


    @Column(name="document_id", nullable=false)
    private java.lang.String documentId;

    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @OneToOne
    @JoinColumn(name="placement_id", nullable=false)
    private i2par.campus.placement.Placement placement;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.PropertyGroup state;

    @Column(name="due_date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dueDate;

    @Column(name="max_offers", nullable=false)
    private Integer maxOffers;

    @Column(name="student_cgpa")
    private String studentCgpa = "N";
    private transient boolean studentCgpaBoolean;

    @OneToOne
    @JoinColumn(name="placement_category_id", nullable=false)
    private app.domain.PropertyGroup placementCategory;

    @Column(name="clause_template", nullable=false)
    private java.lang.String clauseTemplate;

    @Column(name="clause", nullable=false)
    private java.lang.String clause;

    @Column(name="notification_template", nullable=false)
    private java.lang.String notificationTemplate;

    @Column(name="notification", nullable=false)
    private java.lang.String notification;

    public java.lang.String getDocumentId() {
        
        return documentId;
    }

    public void setDocumentId(java.lang.String documentId) {

        this.documentId = documentId;
    }

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public i2par.campus.placement.Placement getPlacement() {
        
        return placement;
    }

    public void setPlacement(i2par.campus.placement.Placement placement) {

        this.placement = placement;
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }

    public java.util.Date getDueDate() {
        
        return dueDate;
    }

    public void setDueDate(java.util.Date dueDate) {

        this.dueDate = dueDate;
    }

    public Integer getMaxOffers() {
        
        return maxOffers;
    }

    public void setMaxOffers(Integer maxOffers) {

        this.maxOffers = maxOffers;
    }

    public java.lang.String getStudentCgpa() {
        
        return studentCgpa;
    }

    public void setStudentCgpa(java.lang.String studentCgpa) {

        this.studentCgpa = studentCgpa;
    }

    public boolean studentCgpa() {

        return "Y".equals(studentCgpa);
    }

    public Boolean getStudentCgpaBoolean() {
        
        return studentCgpa != null && studentCgpa.equals("Y");
    }

    public void setStudentCgpaBoolean(Boolean studentCgpaBoolean) {

        studentCgpa = studentCgpaBoolean ? "Y" : "N";
    }

    public boolean studentCgpaBoolean() {

        return "Y".equals(studentCgpaBoolean);
    }

    public app.domain.PropertyGroup getPlacementCategory() {
        
        return placementCategory;
    }

    public void setPlacementCategory(app.domain.PropertyGroup placementCategory) {

        this.placementCategory = placementCategory;
    }

    public java.lang.String getClauseTemplate() {
        
        return clauseTemplate;
    }

    public void setClauseTemplate(java.lang.String clauseTemplate) {

        this.clauseTemplate = clauseTemplate;
    }

    public java.lang.String getClause() {
        
        return clause;
    }

    public void setClause(java.lang.String clause) {

        this.clause = clause;
    }

    public java.lang.String getNotificationTemplate() {
        
        return notificationTemplate;
    }

    public void setNotificationTemplate(java.lang.String notificationTemplate) {

        this.notificationTemplate = notificationTemplate;
    }

    public java.lang.String getNotification() {
        
        return notification;
    }

    public void setNotification(java.lang.String notification) {

        this.notification = notification;
    }
}
