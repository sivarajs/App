package i2par.campus.placement;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_course_placement_item")
public class CoursePlacementItem extends AppEntity {


    @Column(name="owner_id", nullable=false)
    private Long ownerId;

    @Column(name="type", nullable=false)
    private java.lang.String type;

    @OneToOne
    @JoinColumn(name="course_id", nullable=false)
    private i2par.campus.Course course;

    @Column(name="student_count", nullable=false)
    private Integer studentCount;

    @Column(name="expected_pay", nullable=false)
    private Float expectedPay;

    @Column(name="offers_made")
    private Integer offersMade;

    public Long getOwnerId() {
        
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {

        this.ownerId = ownerId;
    }

    public java.lang.String getType() {
        
        return type;
    }

    public void setType(java.lang.String type) {

        this.type = type;
    }

    public i2par.campus.Course getCourse() {
        
        return course;
    }

    public void setCourse(i2par.campus.Course course) {

        this.course = course;
    }

    public Integer getStudentCount() {
        
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {

        this.studentCount = studentCount;
    }

    public Float getExpectedPay() {
        
        return expectedPay;
    }

    public void setExpectedPay(Float expectedPay) {

        this.expectedPay = expectedPay;
    }

    public Integer getOffersMade() {
        
        return offersMade;
    }

    public void setOffersMade(Integer offersMade) {

        this.offersMade = offersMade;
    }
}
